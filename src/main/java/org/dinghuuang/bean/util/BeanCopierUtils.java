package org.dinghuuang.bean.util;

import net.sf.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dinghuang123@gmail.com
 * @since 2022/7/29
 */
public class BeanCopierUtils {

    /**
     * BeanCopier 缓存Map
     */
    public static Map<String, BeanCopier> beanCopierCacheMap = new ConcurrentHashMap<>();

    /**
     * @param source 源对象
     * @param target 目标对象
     * @return org.springframework.cglib.beans.BeanCopier
     * 将source对象的属性拷贝到target对象中去
     * @date 2021/2/6
     */
    public static void copyProperties(Object source, Object target) throws Exception {
        /** Map 缓存Key*/
        String cacheKey = source.getClass().toString() + target.getClass().toString();
        BeanCopier beanCopier = null;

        // 双重交易锁，防止并发问题发生
        if (!beanCopierCacheMap.containsKey(cacheKey)) {
            synchronized (BeanCopierUtils.class) {
                if (!beanCopierCacheMap.containsKey(cacheKey)) {
                    beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
                } else {
                    beanCopier = beanCopierCacheMap.get(cacheKey);
                }
            }
        } else {
            beanCopier = beanCopierCacheMap.get(cacheKey);
        }

        assert beanCopier != null;
        beanCopier.copy(source, target, null);
    }
}