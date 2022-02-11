package com.example.lims.common.util;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.lims.modules.user.entity.UserEntity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    public final static String SECRET="secret";
    public final static String ISSUER="CatClimb";
    public final static String SUBJECT= "LIMS Web Point";
    public final static String CLAIM1="loginName";
    public final static String CLAIM2="Auth";
    public final static String AUDIENCE="Client";

    public static String createJWTToken(UserEntity userEntity){
//        服务器端密钥

        //签名算法
        Algorithm algorithm=Algorithm.HMAC256(SECRET);
        Map<String,Object> map=new HashMap<>();
        map.put("alg", "HMAC256");
        map.put("typ", "JWS");
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis() + 60 * 60 * 24 * 1000;
        String token= JWT.create()
                .withHeader(map)
                .withIssuer(ISSUER)
                .withClaim(CLAIM1, userEntity.getUserName())
                .withClaim(CLAIM2, userEntity.getAuth())
                .withSubject(SUBJECT)
                .withAudience(AUDIENCE)
                .withIssuedAt(new Date(startTime))
                .withExpiresAt(new Date(endTime))
                .sign(algorithm);
        return token;
    }

    public static String getToken(HttpServletRequest httpServletRequest) {

        String token=null;
        //1头部获取
        token = httpServletRequest.getHeader("Authorization");
//        //2url连接
        if (token==null){
            token = httpServletRequest.getParameter("token");
        }
        //3cookie获取
        if(token==null){
            Cookie[] cookies = httpServletRequest.getCookies();
            if(cookies!=null){
                for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("token")){
                        token=cookie.getValue();
                        break;
                    }
                }
            }

        }
        return token;
    }
//    返回的是用户名
    public static String getAuthByToken(String token){
        Algorithm algorithm=Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .withSubject(SUBJECT)
                .withAudience(AUDIENCE)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        Claim loginName = jwt.getClaim("loginName");
        Claim Auth=jwt.getClaim("Auth");
        return Auth.asString();

    }
    public static String getUserNameByToken(String token){
        Algorithm algorithm=Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .withSubject(SUBJECT)
                .withAudience(AUDIENCE)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        Claim loginName = jwt.getClaim("loginName");
        Claim Auth=jwt.getClaim("Auth");
        return loginName.asString();

    }
    //是否有效 自己处理自己异常

    /**
     * 由于存在除了过期还有篡改token等异常，不方便解决，如下代码统一用 用户名或密码错误来解决
     *
     * @param token
     * @return
     */
//    public static boolean vertifyTokenByExp(String token) {
//        Algorithm algorithm=Algorithm.HMAC256(SECRET);
//        JWTVerifier verifier = JWT.require(algorithm)
//                .withIssuer(ISSUER)
//                .withSubject(SUBJECT)
//                .withAudience(AUDIENCE)
//                .build();
//        try {
//            DecodedJWT jwt = verifier.verify(token);
//            System.out.println(jwt.getExpiresAt());
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }


//抛出异常让全局异常拦截器捕获
    public static boolean vertifyTokenByExp(String token) {
        Algorithm algorithm=Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .withSubject(SUBJECT)
                .withAudience(AUDIENCE)
                .build();
        //无效时会抛出异常
        DecodedJWT jwt = verifier.verify(token);
        System.out.println(jwt.getExpiresAt());
        return true;
    }
}
