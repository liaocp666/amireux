package cn.liaocp.amireux.user;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
public class SecurityConstant {

    public final static String AUTHORIZATION = "Authorization";

    public final static String TOKEN_PREFIX = "Bearer ";

    public final static String FORBIDDEN = "FORBIDDEN";

    public static class SecurityCacheConstant {
        public final static String JWT_SECRET = "jwtSecret";
    }

}
