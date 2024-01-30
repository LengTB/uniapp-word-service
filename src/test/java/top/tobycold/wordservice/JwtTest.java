package top.tobycold.wordservice;

import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class JwtTest {
    @Test
    public void JwtTest() {
        String jwt = createJWT();
        validateJWT(jwt);
    }

    private static void validateJWT(String token) {
        try {
            JWTValidator.of(token).validateDate(DateTime.of(System.currentTimeMillis()));
            System.out.println("验证成功");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String createJWT() {
        String token = JWT
                .create()
                .setKey("123456".getBytes())
                .setPayload("id", "123")
                .setExpiresAt(new Date(DateTime.now().getTime()))
                .sign();
        System.out.println(token);
        return token;
    }
}
