package cn.liaocp.amireux.core.util.cache;

import cn.liaocp.amireux.core.cache.AmireuxMemoryCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
public class AmireuxMemoryCacheTest {

    AmireuxMemoryCache cacheStore;

    @BeforeEach
    void setUp() {
        cacheStore = new AmireuxMemoryCache();
    }

    @Test
    void putNullTest() {
        assertThrows(IllegalArgumentException.class,
                () -> cacheStore.put(null, "{\"name\": \"admin\"}", 60 * 1000L));

        assertThrows(IllegalArgumentException.class,
                () -> cacheStore.put("", "{\"name\": \"admin\"}", 60 * 1000L));
    }

    @Test
    void getTest() {
        String key = "user";
        String value = "{\"name\": \"admin\"}";
        cacheStore.put(key, value, 60 * 1000L);
        assertEquals(value, cacheStore.get("user"));
    }

    @Test
    void getExpiryTest() throws InterruptedException {
        String key = "user";
        String value = "{\"name\": \"admin\"}";
        cacheStore.put(key, value, 500L);

        assertEquals(value, cacheStore.get("user"));

        TimeUnit.SECONDS.sleep(1L);

        assertNull(cacheStore.get(key));
    }

    @Test
    void clearTest() {
        String key = "user";
        String value = "{\"name\": \"admin\"}";

        cacheStore.put(key, value, 10000L);
        assertEquals(value, cacheStore.get("user"));
        cacheStore.clear(key);
        assertNull(cacheStore.get(key));

        cacheStore.put(key, value, 10000L);
        assertEquals(value, cacheStore.get("user"));
        cacheStore.clear();
        assertNull(cacheStore.get(key));
    }

}
