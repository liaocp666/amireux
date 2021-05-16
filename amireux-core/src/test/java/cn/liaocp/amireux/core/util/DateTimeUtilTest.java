package cn.liaocp.amireux.core.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
public class DateTimeUtilTest {

    @Test
    void currentMillisPlusMillisTest() {
        Long plusMillis = DateTimeUtil.nowMillisPlusMillis(60 * 1000L);
        assertTrue(plusMillis.compareTo(DateTimeUtil.nowMillis()) > 0);
    }

}
