package cn.liaocp.amireux.core.util;

import java.time.Instant;

/**
 * Date and Time Util
 *
 * @author Chunping.Liao
 */
public class DateTimeUtil {

    /**
     * Now milliseconds plus the specified milliseconds
     *
     * @param millisToAdd the milliseconds to add, positive or negative
     * @return specified milliseconds added
     */
    public static Long nowMillisPlusMillis(Long millisToAdd) {
        Instant now = now().plusMillis(millisToAdd);
        return now.toEpochMilli();
    }

    public static Instant now() {
        return Instant.now();
    }

    public static Long nowMillis() {
        return now().toEpochMilli();
    }

}
