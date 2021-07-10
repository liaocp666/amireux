package cn.liaocp.amireux.base;

/**
 * @author Chunping.Liao
 */
public class SecurityConstant {

    public final static String AUTHORIZATION = "Authorization";

    public final static String TOKEN_PREFIX = "Bearer ";

    public final static String ANONYMOUS = "Anonymous";

    public final static String FORBIDDEN = "Forbidden";

    public static class SecurityCacheConstant {
        public final static String JWT_SECRET = "jwtSecret";
    }

}
