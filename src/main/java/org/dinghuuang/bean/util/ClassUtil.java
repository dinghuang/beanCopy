package org.dinghuuang.bean.util;

import java.util.Collections;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.dinghuuang.bean.constants.ClassTemplateConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 1.0
 * @Author: edisionding
 * @Description:
 * @Date: since 2022/7/28 19:05
 * @Modify By: edisionding
 */
public class ClassUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    public static Object getClassInstance(String classTxtStr, String className,
        String packageName) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager stdManager = compiler.getStandardFileManager(null, null, null);
        MemoryClassLoader memoryClassLoader = null;
        try (MemoryJavaFileManager manager = new MemoryJavaFileManager(stdManager)) {
            JavaFileObject javaFileObject = manager.makeStringSource(
                className + ClassTemplateConstants.JAVA_FILE, classTxtStr);
            CompilationTask task = compiler.getTask(null, manager, null, null, null,
                Collections.singletonList(javaFileObject));
            if (Boolean.TRUE.equals(task.call())) {
                memoryClassLoader = new MemoryClassLoader(manager.getClassBytes());
                return memoryClassLoader.findClass(packageName + ClassTemplateConstants.SLIP_FLAG_2 + className).newInstance();
            }
        } catch (Exception e) {
            LOGGER.error("getClassInstance {} error", className, e);
        } finally {
            if (memoryClassLoader != null) {
                try {
                    memoryClassLoader.close();
                } catch (Exception e) {
                    LOGGER.error("getClassInstance {} error", className, e);
                }
            }
        }
        return null;
    }

}
