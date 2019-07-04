package hva.ewa.rest.model;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import hva.ewa.model.User;
import hva.ewa.service.UserRepositoryService;
import hva.ewa.service.impl.UserRepositoryServiceImpl;

import java.io.UnsupportedEncodingException;
import java.time.ZonedDateTime;
import java.util.Date;

/*
    Our simple static class that demonstrates how to create and decode JWTs.
 */
public class WebToken {

    private UserRepositoryService service;

    public WebToken() {
        service = UserRepositoryServiceImpl.getInstance();
    }

    private static final String JWT_TOKEN_KEY = "TEST";

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_TOKEN_KEY);
            Date expirationDate = Date.from(ZonedDateTime.now().plusHours(24).toInstant());
            Date issuedAt = Date.from(ZonedDateTime.now().toInstant());
            return JWT.create()
                    // Issue date.
                    .withIssuedAt(issuedAt)
                    // Expiration date.
                    .withExpiresAt(expirationDate)
                    // User id - here we can put anything we want, but for the example userId is appropriate.
                    .withClaim("userId", user.getIdUser())
                    // Issuer of the token.
                    .withIssuer("jwtauth")
                    // And the signing algorithm.
                    .sign(algorithm);
        } catch (UnsupportedEncodingException | JWTCreationException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public User validateToken(String token) {
        try {
            if(token != null) {
                Algorithm algorithm = Algorithm.HMAC256(JWT_TOKEN_KEY);
                JWTVerifier verifier = JWT.require(algorithm)
                        .withIssuer("jwtauth")
                        .build(); //Reusable verifier instance
                DecodedJWT jwt = verifier.verify(token);
                //Get the userId from token claim.
                Claim userId = jwt.getClaim("userId");
                // Find user by token subject(id).
                int intId = userId.asInt();
                return service.getUserFromId(intId);
            }
        } catch (UnsupportedEncodingException | JWTVerificationException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    //TODO: Make secure pass
//    public static String getSHA512SecurePassword(String passwordToHash) {
//        String generatedPassword = null;
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-512");
//            byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
//            StringBuilder sb = new StringBuilder();
//            for (byte aByte : bytes) {
//                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
//            }
//            generatedPassword = sb.toString();
//        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
//            System.out.println(e.getMessage());
//        }
//        return generatedPassword;
//    }
}
