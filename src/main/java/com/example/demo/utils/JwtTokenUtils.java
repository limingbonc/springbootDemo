package com.example.demo.utils;

import cn.hutool.crypto.asymmetric.RSA;
import com.example.demo.examples.token.domain.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author: liming522
 * @description:
 * @date: 2022/1/21 3:38 下午
 * @hope: The newly created file will not have a bug
 */
@Slf4j
@Component
public class JwtTokenUtils {

    public   static final  String subJectStr = "springboot-user";

    // token时效：5分钟
    public static final long EXPIRE = 1000 * 60 * 5;

    // 签名哈希的密钥，对于不同的加密算法来说含义不同(这个可以隔一段时间变更)
    public static final String APP_SECRET = "liming522thisATestSecretjZWLPHOsdadasdasfdssfeweee";

    // 存在token位置
    public static  final String header = "Authorization";

    public static final String AUTHORITIES_KEY = "auth";

    /**
     * 根据用户昵称和密码生成token
     * @param userName 用户昵称
     * @param passWord 用户密码
     * @return JWT规则生成的token
     */
    public static String getJwtToken(String userName, String passWord){
        Map<String,Object> claims = new HashMap<>();
        claims.put("userName", userName);
        claims.put("passWord", passWord);

        String JwtToken = Jwts.builder()
                .setSubject(subJectStr)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim(AUTHORITIES_KEY, claims)
                // 传入Key对象
                .signWith(Keys.hmacShaKeyFor(APP_SECRET.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
        return JwtToken;
    }

    /**
     * 解析JWT字符串
     * @param jwtToken token字符串
     */
    public static Jws<Claims> decode(String jwtToken) {
        // 传入Key对象
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(APP_SECRET.getBytes(StandardCharsets.UTF_8))).build().parseClaimsJws(jwtToken);
        return claimsJws;
    }

    public Authentication getAuthentication(String jwtToken) {
        // 1.解析jwtToken
        Jws<Claims> decodedJWT = decode(jwtToken);

        // 2.取出JWT字符串载荷中的随机token，从Redis中获取用户信息
        Map<String,Object> body_AUTHORITIES_KEY =  (Map<String,Object>)decodedJWT.getBody().get(AUTHORITIES_KEY);
        String userName = (String)body_AUTHORITIES_KEY.get("userName");
        String userId = (String)body_AUTHORITIES_KEY.get("userId");
        String password = (String)body_AUTHORITIES_KEY.get("password");
        User user = new User(userId, userName,password);

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(decodedJWT.getBody().get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(user, jwtToken, authorities);
    }

    /**
     * 校验token是否存在与有效
     * @param jwtToken token字符串
     * @return 如果token正确返回true，否则返回false
     */
    public static boolean validateToken(String jwtToken) {
        try {
            // 1.校验JWT字符串
            decode(jwtToken);
            return  true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature.");
            e.printStackTrace();
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            e.printStackTrace();
        }
        return  false;
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
