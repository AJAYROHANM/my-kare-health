package health.mykare.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@RequiredArgsConstructor
public class AccessTokenUtils {

    public static final String USER_ID = "user_id";

    public static final String USER_TYPE = "user_type";

    public static final String PASSWORD = "mykare";


    public Long fetchUserId(String accessToken){
        try{
            byte[] secretKey = decodeSecret(PASSWORD);
            Integer userId = (Integer) Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(accessToken).getBody().get(USER_ID);
            return Long.parseLong(String.valueOf(userId));
        }catch (Exception e){
            return null;
        }
    }

    public String fetchUserType(String accessToken){
        try{
            byte[] secretKey = decodeSecret(PASSWORD);
            String userType = (String) Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(accessToken).getBody().get(USER_TYPE);
            return userType;
        }catch (Exception e){
            return null;
        }
    }

    public String generateAccessToken(Long userId){
        try {
            byte[] secretKey = decodeSecret(PASSWORD);
            String jwtToken = Jwts.builder()
                    .claim(USER_ID, userId)
                    .signWith(SignatureAlgorithm.HS256, secretKey).compact();
            return jwtToken;

        } catch (Exception e) {
            return null;
        }
    }

    private byte[] decodeSecret(String key){
        return Base64.getDecoder().decode(key);
    }

}
