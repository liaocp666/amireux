package cn.liaocp.amireux.user.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
public class SecurityUtilTest {

    @Test
    public void encryptPassword() {
        assertThrows(IllegalArgumentException.class, () -> SecurityUtil.encryptPassword(""));

        assertThrows(IllegalArgumentException.class, () -> SecurityUtil.encryptPassword(null));
    }

    @Test
    public void generateTokenTest() {
        String token = SecurityUtil.generateToken("admin", "amireux", new Date(), "123456");
        assertTrue(StringUtils.isNotBlank(token));

        assertThrows(IllegalArgumentException.class, () -> SecurityUtil.generateToken("admin", "", new Date(), "123456"));
    }

    @Test
    public void verifyTokenTest() throws InterruptedException {
        String token = SecurityUtil.generateToken("admin", "amireux", new Date(), "123456");
        TimeUnit.SECONDS.sleep(1L);
        assertFalse(SecurityUtil.verifyToken(token, "amireux", "123456"));

        token = "asdfgqwertzxcvb";
        assertFalse(SecurityUtil.verifyToken(token, "amireux", "123456"));

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        Date time = calendar.getTime();
        token = SecurityUtil.generateToken("admin", "amireux", time, "123456");
        assertTrue(SecurityUtil.verifyToken(token, "amireux", "123456"));

        token = SecurityUtil.generateToken("admin", "666", time, "123456");
        assertFalse(SecurityUtil.verifyToken(token, "amireux", "123456"));

        token = SecurityUtil.generateToken("admin", "666", time, "654321");
        assertFalse(SecurityUtil.verifyToken(token, "amireux", "123456"));
    }

    @Test
    public void getAlgorithmTest() {
        assertThrows(IllegalArgumentException.class, () -> SecurityUtil.getAlgorithm(""));
        assertThrows(IllegalArgumentException.class, () -> SecurityUtil.getAlgorithm(null));
    }

    @Test
    public void getSubjectTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        Date time = calendar.getTime();
        String token = SecurityUtil.generateToken("admin", "amireux", time, "123456");

        assertEquals("admin", SecurityUtil.getSubject(token, "amireux", "123456"));

    }

}
