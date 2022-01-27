package com.example.demo.utils;

import cn.hutool.crypto.asymmetric.RSA;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;


/**
 * @author: liming522
 * @description:
 * @date: 2022/1/21 3:38 下午
 * @hope: The newly created file will not have a bug
 */
public class JwtTokenUtils {

    private  static final  String subJectStr = "springboot-user";

    // token时效：5分钟
    public static final long EXPIRE = 1000 * 60 * 5;

    // 签名哈希的密钥，对于不同的加密算法来说含义不同(这个可以隔一段时间变更)
    public static final String APP_SECRET = "liming522thisATestSecretjZWLPHOsdadasdasfdssfeweee";

    /**
     * 根据用户id和昵称生成token
     * @param userId  用户id
     * @param userName 用户昵称
     * @return JWT规则生成的token
     */
    public static String getJwtToken(String userId, String userName){
        String JwtToken = Jwts.builder()
                .setSubject(subJectStr)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("userId", userId)
                .claim("userName", userName)
                // 传入Key对象
                .signWith(Keys.hmacShaKeyFor(APP_SECRET.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
        return JwtToken;
    }

    /**
     * 判断token是否存在与有效
     * @param jwtToken token字符串
     * @return 如果token有效返回true，否则返回false
     */
    public static Jws<Claims> decode(String jwtToken) {
        // 传入Key对象
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(APP_SECRET.getBytes(StandardCharsets.UTF_8))).build().parseClaimsJws(jwtToken);
        return claimsJws;
    }


    /*------------------以下为非对称加密算法---------------------*/

    private static final String RSA_PRIVATE_KEY = "..."; //私钥
    private static final String RSA_PUBLIC_KEY = "...";  //公钥

    /**
     * 根据用户id和昵称生成token
     * @param id  用户id
     * @param nickname 用户昵称
     * @return JWT规则生成的token
     */
    public static String getJwtTokenRsa(String id, String nickname){
        // 利用hutool创建RSA
        RSA rsa = new RSA(RSA_PRIVATE_KEY, null);
        RSAPrivateKey privateKey = (RSAPrivateKey) rsa.getPrivateKey();
        String JwtToken = Jwts.builder()
                .setSubject(subJectStr)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("id", id)
                .claim("nickname", nickname)
                // 签名指定私钥
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
        return JwtToken;
    }

    /**
     * 判断token是否存在与有效
     * @param jwtToken token字符串
     * @return 如果token有效返回true，否则返回false
     */
    public static Jws<Claims> decodeRsa(String jwtToken) {
        RSA rsa = new RSA(null, RSA_PUBLIC_KEY);
        RSAPublicKey publicKey = (RSAPublicKey) rsa.getPublicKey();
        // 验签指定公钥
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(jwtToken);
        return claimsJws;
    }

}
