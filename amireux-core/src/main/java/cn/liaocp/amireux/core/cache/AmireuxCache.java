package cn.liaocp.amireux.core.cache;

import com.sun.istack.NotNull;

/**
 * Amireux Cache interface
 *
 * @author Chunping.Liao
 * @date 2021/5/16
 */
public interface AmireuxCache {

    /**
     * Access to cache, the method will overwrite the old content.
     * The cache key expiration time is the current time + param expiryTime.
     *
     * @param key        cache key
     * @param value      cache value
     * @param expiryTime cache expiryTime
     * @return true if it successfully， false if it fail
     */
    Boolean put(@NotNull String key, @NotNull String value, @NotNull Long expiryTime);

    /**
     * Get the string in the cache by key,
     * return value may be null.
     *
     * @param key cache key
     * @return cache value
     */
    String get(@NotNull String key);

    /**
     * Clear all cache key in the cache
     */
    void clear();

    /**
     * Clear cache key in the cache
     *
     * @param key cache key
     */
    void clear(String key);

}
