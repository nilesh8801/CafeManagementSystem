package CafeMangementSystem.com.JWT;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

//write here all utility method required in order to work JWT.-
//whatever method write here this method are going to use in order to extract data from JSON to create JSON

@Service
public class  JwtUtil {


    // secret  key for JOSN token generated
    private  String  secret = "btechdays";

    public String extractUsername(String token){
        return extractClamis(token,Claims::getSubject );
    }

    public Date extractExpiration(String token){
        return extractClamis(token,Claims::getExpiration);
    }

    public <T> T extractClamis(String token, Function<Claims, T> claimsResolver ){
        final Claims claims= extractAllClaims(token);
        return claimsResolver.apply(claims);

    }
    public Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean iSTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username, String role){
        Map<String,Object> claims = new HashMap<>();
        claims.put("role", role);
        return createToken(claims, username);
    }


//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        return createToken(claims, userDetails.getUsername());
//    }




    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +1000 *60 *60 * 10))
                .signWith(SignatureAlgorithm.HS256,secret).compact();
    }



    public Boolean validateToken(String token, UserDetails userDetails){
        final String username= extractUsername(token);
        return(username.equals(userDetails.getUsername()) && !iSTokenExpired(token));
    }
}