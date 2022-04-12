package com.example.householderback.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.householderback.entity.AdminUser;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author: lhz
 * @date: 2020/10/26
 **/
public class JwtUtil {

    public static String createToken(AdminUser adminUser) {
        String token = "";
        token = JWT.create().withExpiresAt(addDays(1)) //生存时间为1天
                .withAudience(adminUser.getId()+"")
                .sign(Algorithm.HMAC256(adminUser.getPassword()));
        return token;
    }

    /**
     * 获取token里的用户id
     * @param token
     * @return
     */
    public static Integer parseUserId(String token) {
        String userId = "";
        try {
            userId= JWT.decode(token).getAudience().get(0);
        } catch (Exception e) {
            return 0;
        }
        return Integer.valueOf(userId);
    }

    /**
     * 获取过期时间
     * @param token
     * @return
     */
    public static Date etExpires(String token) {
        return JWT.decode(token)
                .getExpiresAt();
    }

    /**
     * 验证是否过期
     * @param token
     * @return
     */
    public static Boolean validToken(String token) {
        try {
            return new Date().before(etExpires(token));
        } catch (Exception e) {
            return false;
        }
    }


    public static void ClearToken(String token, HttpSession session) {


    }


    private static Date localDateToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private static Date addDays(int days) {
        LocalDateTime now = LocalDateTime.now();
        return localDateToDate(now.plusDays(1));
    }


}
