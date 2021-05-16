package cn.liaocp.amireux.user.util;

import cn.liaocp.amireux.core.enums.RestResultEnum;
import cn.liaocp.amireux.core.exception.AmireuxException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@Slf4j
public class SecurityUtil {

    /**
     * encrypted password
     *
     * @param password password
     * @return encrypted password
     */
    public static String encryptPassword(@NotNull String password) {
        Assert.hasText(password, "password must not be blank");
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Generate jwt token
     *
     * @param subject   subject
     * @param issUser   specific Issuer
     * @param expiresAt specific Expires
     * @param secret    the secret to use in the signing instance
     * @return jwt token
     */
    public static String generateToken(@NotNull String subject, @NotNull String issUser, @NotNull Date expiresAt, @NotNull String secret) {
        Assert.hasText(subject, "subject must not be blank");
        Assert.hasText(issUser, "issUser must not be blank");
        Assert.hasText(secret, "secret must not be blank");
        Assert.notNull(expiresAt, "expiresAt must not be blank");
        String token = StringUtils.EMPTY;
        try {
            token = JWT.create()
                    .withIssuer(issUser)
                    .withSubject(subject)
                    .withIssuedAt(new Date())
                    .withExpiresAt(expiresAt)
                    .sign(getAlgorithm(secret));
        } catch (JWTCreationException e) {
            log.warn("Generate token failed, token: [{}], issUser: [{}], expiresAt: [{}], secret：[{}]", subject, issUser, expiresAt, secret, e);
        }
        return token;
    }

    /**
     * Verify Token
     * verify the token expiration date, the issuer
     *
     * @param token   The token to be verified
     * @param issUser specific Issuer
     * @param secret  the secret to use in the verify
     * @return true validation passes, false validation does not pass
     */
    public static Boolean verifyToken(@NotNull String token, @NotNull String issUser, @NotNull String secret) {
        Assert.hasText(token, "token must not be blank");
        Assert.hasText(issUser, "issUser must not be blank");
        Assert.hasText(secret, "secret must not be blank");
        try {
            JWTVerifier verifier = JWT.require(getAlgorithm(secret))
                    .withIssuer(issUser)
                    .build();
            verifier.verify(token);
            return Boolean.TRUE;
        } catch (JWTVerificationException e) {
            log.warn("Verify token failed, token: [{}], issUser: [{}], secret: [{}]", token, issUser, secret, e);
        }
        return Boolean.FALSE;
    }

    /**
     * Get subject by token
     * verify token first, decode token late,if the validation does not pass, {@link AmireuxException} will be thrown
     *
     * @param token   Token
     * @param issUser specific Issuer
     * @param secret  the secret to use in the verify
     * @return subject
     */
    public static String getSubject(String token, String issUser, String secret) {
        if (!verifyToken(token, issUser, secret)) {
            throw new AmireuxException(RestResultEnum.INVALID_TOKEN);
        }
        DecodedJWT decode = JWT.decode(token);
        return decode.getSubject();
    }

    /**
     * get HmacSHA256 Algorithm
     *
     * @param secret the secret to use in the verify or signing instance
     * @return Algorithm
     */
    public static Algorithm getAlgorithm(@NotNull String secret) {
        Assert.hasText(secret, "secret must not be blank");
        return Algorithm.HMAC256(secret);
    }

}
