package cn.liaocp.amireux.core.cache;

import cn.liaocp.amireux.core.util.DateTimeUtil;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.annotation.PreDestroy;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Amireux built-in MemoryCacheStore
 * Used to store content that is not visible to the user
 *
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@Slf4j
public class AmireuxMemoryCache implements AmireuxCache {

    /**
     * Cache container.
     */
    private static final ConcurrentHashMap<String, MemoryCacheWrapper> CACHE_CONTAINER = new ConcurrentHashMap<>();

    /**
     * Lock.
     */
    private final Lock lock;

    private final Timer timer;

    private final long delay = 0L;

    /**
     * 1 minute
     */
    private final long period = 60 * 1000;

    public AmireuxMemoryCache() {
        lock = new ReentrantLock();
        timer = new Timer();
        timer.scheduleAtFixedRate(new AmireuxMemoryCacheTask(), delay, period);
    }

    @Override
    public Boolean put(String key, String value, Long expiryTime) {
        Assert.hasText(key, "Cache key must not be blank");
        lock.lock();
        try {
            MemoryCacheWrapper memoryCacheWrapper = new MemoryCacheWrapper(value, DateTimeUtil.nowMillisPlusMillis(expiryTime));
            CACHE_CONTAINER.put(key, memoryCacheWrapper);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("Access failure", e);
            }
        } finally {
            lock.unlock();
        }
        return Boolean.TRUE;
    }

    @Override
    public String get(@NotNull String key) {
        Assert.hasText(key, "Cache key must not be blank");
        MemoryCacheWrapper memoryCacheWrapper = CACHE_CONTAINER.get(key);
        if (!ObjectUtils.isEmpty(memoryCacheWrapper)
                && DateTimeUtil.nowMillis().compareTo(memoryCacheWrapper.getExpiryTime()) < 0) {
            return memoryCacheWrapper.getValue();
        }
        return null;
    }

    @Override
    public void clear() {
        CACHE_CONTAINER.clear();
    }

    @Override
    public void clear(String key) {
        Assert.hasText(key, "Cache key must not be blank");
        CACHE_CONTAINER.remove(key);
    }

    @PreDestroy
    public void preDestroy() {
        log.debug("Destroy amireuxMemoryCache");
        clear();
    }

    /**
     * MemoryCacheWrapper
     *
     * @author Chunping.Liao
     * @date 2021/5/16
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class MemoryCacheWrapper {
        /**
         * cache value
         */
        private String value;

        /**
         * cache expiryTime
         */
        private Long expiryTime;
    }

    /**
     * Clear expired cache task
     *
     * @author Chunping.Liao
     * @date 2021/5/16
     */
    private class AmireuxMemoryCacheTask extends TimerTask {

        /**
         * Remove cache keys whose current time is greater than their expiration time
         */
        @Override
        public void run() {
            Long currentMilli = DateTimeUtil.nowMillis();
            CACHE_CONTAINER.entrySet().removeIf(e -> currentMilli.compareTo(e.getValue().getExpiryTime()) > 0);
        }
    }

}
