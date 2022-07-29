package org.dinghuuang.bean.util;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author: edisionding
 * @Description:
 * @Date: since 2022/7/28 19:18
 * @Modify By: edisionding
 */
public class MemoryClassLoader extends URLClassLoader {

    Map<String, byte[]> classBytes = new HashMap<>();

    public MemoryClassLoader(Map<String, byte[]> classBytes) {
        super(new URL[0], MemoryClassLoader.class.getClassLoader());
        this.classBytes.putAll(classBytes);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] buf = classBytes.get(name);
        if (buf == null) {
            return super.findClass(name);
        }
        classBytes.remove(name);
        return defineClass(name, buf, 0, buf.length);
    }
}