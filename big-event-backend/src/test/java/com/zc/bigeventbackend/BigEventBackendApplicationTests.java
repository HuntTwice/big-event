package com.zc.bigeventbackend;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class BigEventBackendApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testGen() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "张三");
        String token = JWT.create()
                .withClaim("user", claims) //添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) //添加过期时间
                .sign(Algorithm.HMAC256("zc"));//指定算法，配置密钥
        System.out.println(token);
    }

    /**
     * eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9
     * .eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3MTU3MzI5OTN9
     * .yOJISehR8CPM_e8l1b73Vmp5UQXTfnpqYLp1QjAte4A
     *
     */
    @Test
    public void parseToken(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3MTU3MzI5OTN9" +
                ".yOJISehR8CPM_e8l1b73Vmp5UQXTfnpqYLp1QjAte4A";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("zc")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));
    }

    @Test
    public void parseMD5(){
        String password = "40ee0f14dfeb26f4b7d0d8a203624618";

    }

}
