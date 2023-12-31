package com.dig.blog.app.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenHelper {

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
	private String secret = "jwtTokenKet";
	
	//retrieving username from jwt token
	
	public String getUsernameFromToken(String token) {
		
		return getClaimFromToken(token,Claims::getSubject);
	}
	
   //retrieving expiration date from jwt token
	private <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver) {
		// TODO Auto-generated method stub
	final Claims claims =	getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	//for retrieving any information from token we need secret key
	private Claims getAllClaimsFromToken(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	//check if the token has exppiry
	private Boolean isTokenExpired(String token) {
	final Date expiration =	getExpirationDateFromToken(token);
		return expiration.before(new Date());
		
	}

	//retrieve expiration date from token
	private Date getExpirationDateFromToken(String token) {
		// TODO Auto-generated method stub
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	//generate token
	
	public String generateToken(UserDetails userdetails) {
		Map<String,Object> claims = new HashMap<>();
		return doGenerateToken(claims,userdetails.getUsername());
	}

	 //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		// TODO Auto-generated method stub
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512,secret).compact();
	}
	
	//validation token
	public boolean validateToken(String token,UserDetails userdetails) {
	final String username = getUsernameFromToken(token);
	return (username.equals(userdetails.getUsername()) && !isTokenExpired(token));
	}
}
