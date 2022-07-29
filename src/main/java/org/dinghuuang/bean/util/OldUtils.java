package org.dinghuuang.bean.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author damonzhou
 * @Title: OldUtils
 * @ProjectName dwms-fpts
 * @Description: TODO
 * @date 2019/7/1011:28
 */
public class OldUtils extends BeanUtils {

    public static void copyPropertiesIgnoreCase(Object source, Object target,
        String... ignoreProperties) {
        PropertyDescriptor[] targetPds = PropertyUtils.getPropertyDescriptors(target.getClass());
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties)
            : null);
        PropertyDescriptor[] sourcePds = PropertyUtils.getPropertyDescriptors(source.getClass());
        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(
                targetPd.getName()))) {
                PropertyDescriptor sourcePd = getPropertyIgnoreCase(sourcePds, targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);
                            if (value != null) {
                                if (!Modifier.isPublic(
                                    writeMethod.getDeclaringClass().getModifiers())) {
                                    writeMethod.setAccessible(true);
                                }
                                writeMethod.invoke(target, value);
                            }
                        } catch (Throwable ex) {
                        }
                    }
                }
            }
        }
    }

    private static PropertyDescriptor getPropertyIgnoreCase(PropertyDescriptor[] sourcePds,
        String targetPd) {
        for (PropertyDescriptor propertyDescriptor : sourcePds) {
            if (propertyDescriptor.getName().equals(targetPd)) {
                return propertyDescriptor;
            }
        }
        return null;
    }

    private static PropertyDescriptor getProperty(PropertyDescriptor[] sourcePds, String targetPd) {
        for (PropertyDescriptor propertyDescriptor : sourcePds) {
            if (propertyDescriptor.getName().equals(targetPd)) {
                return propertyDescriptor;
            }
        }
        return null;
    }

    /**
     * bean 属性为String，值为的""，设置为null
     *
     * @param bean
     */
    public static void setNullObject(Object bean) {
        Class beanClazz = bean.getClass();
        Field[] fs = beanClazz.getDeclaredFields();
        for (int i = 0; fs != null && i < fs.length; i++) {
            Field field = fs[i];
            if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(
                field.getModifiers())) {
                if (String.class.equals(field.getType())) {
                    field.setAccessible(true);
                    try {
                        Object value = field.get(bean);
                        if (value != null && "".equals(value)) {
                            setValueByMethod(field, bean, null);
                        }
                    } catch (IllegalAccessException e) {
                    }
                }
            }
        }
        Class superClass = beanClazz.getSuperclass();
        while (superClass != null && !superClass.equals(Object.class)) {
            Class superClassOrg = superClass;
            superClass = superClassOrg.getSuperclass();
            Field[] sfs = superClass.getDeclaredFields();
            for (int i = 0; sfs != null && i < sfs.length; i++) {
                Field field = sfs[i];
                if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(
                    field.getModifiers())) {
                    if (String.class.equals(field.getType())) {
                        field.setAccessible(true);
                        try {
                            Object value = field.get(bean);
                            if (value != null && "".equals(value)) {
                                setValueByMethod(field, bean, null);
                            }
                        } catch (IllegalAccessException e) {
                        }
                    }
                }
            }
        }

    }

    public static void setValueByMethod(Field field, Object object, Object value) {
        try {
            PropertyDescriptor objectPro = new PropertyDescriptor(field.getName(),
                object.getClass());
            Method destSetter = objectPro.getWriteMethod();
            if (destSetter == null) {
                return;
            }
            destSetter.invoke(object, value);
        } catch (Exception e) {
        }
    }


    /**
     * 格式化string为Date
     *
     * @param datestr
     * @return date
     */
    private static Date parseDate(String datestr) {
        if (null == datestr || datestr.isEmpty()) {
            return null;
        }
        try {
            String fmtstr = null;
            if (datestr.indexOf(':') > 0) {
                fmtstr = "yyyyMMdd HH:mm:ss";
            } else {
                fmtstr = "yyyyMMdd";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(fmtstr, Locale.UK);
            return sdf.parse(datestr);
        } catch (ParseException e) {
            return null;
        }
    }


}
