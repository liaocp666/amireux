package cn.liaocp.amireux.core.util;

import java.time.Instant;

/**
 * Date and Time Util
 *
 * @author Chunping.Liao
 * @date 2021/5/16
 */
public class DateTimeUtil {

    /**
     * current time adds the specified duration in milliseconds
     *
     * @param millisToAdd the milliseconds to add, positive or negative
     * @return specified milliseconds added
     */
    public static Long currentMillisPlusMillis(Long millisToAdd) {
        Instant now = Instant.now();
        now = now.plusMillis(millisToAdd);
        return now.toEpochMilli();
    }

}
