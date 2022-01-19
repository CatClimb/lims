package com.example.lims.modules;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.lims.common.enums.ResultEnum;
import com.example.lims.common.result.Result;
import com.example.lims.modules.user.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

@RestController
public class TestController {
    public final static String SECRET="secret";
    public final static String ISSUER="CatClimb";
    public final static String SUBJECT= "LIMS Web Point";
    public final static String CLAIM1="loginName";
    public final static String CLAIM2="Auth";
    public final static String AUDIENCE="Client";
    @RequestMapping("/login")
    public Result<Object> login(@Valid @RequestBody UserEntity userEntity){
        return Result.success(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), null);
    }
    @RequestMapping("/a")
    public String user(){
        return "123";
    }
    @RequestMapping("/user/user")
    public String s(){
        return "3";
    }

    public static void main(String[] args) {
        Algorithm algorithm=Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .withSubject(SUBJECT)
                .withAudience(AUDIENCE)
                .build();
        DecodedJWT jwt = verifier.verify("eyJ0eXAiOiJKV11MiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJMSU1TIFdlYiBQb2ludCIsImF1ZCI6IkNsaWVudCIsImxvZ2luTmFtZSI6IueUqOaIt-WQjTQiLCJBdXRoIjoi5pmu6YCa55So5oi3IiwiaXNzIjoiQ2F0Q2xpbWIiLCJleHAiOjE2NDI1NjYxMzEsImlhdCI6MTY0MjQ3OTczMX0.J8PtSW-Gum7d2SFPcdP2hodTNqgGNrJtVYVNCADLfQI");
        System.out.println(jwt);
//        Claim loginName = jwt.getClaim("loginName");
//        Claim Auth=jwt.getClaim("Auth");
//        return Auth.asString();
    }


}
