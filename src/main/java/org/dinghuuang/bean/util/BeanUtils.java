package org.dinghuuang.bean.util;

import static org.dinghuuang.bean.constants.ClassTemplateConstants.BLANK;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.CHANGE_LINE;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.CLASS;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.CONVERSION_HANDLE;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.CONVERSION_METHOD;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.CONVERSION_SERVICE;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.END_CHAR;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.FUNCTION_LEFT;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.FUNCTION_RIGHT;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.GET;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.IF;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.IMPLEMENTS;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.IMPORT;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.IS;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.METHOD_LEFT;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.METHOD_POSITION_1;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.METHOD_POSITION_2;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.METHOD_POSITION_3;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.METHOD_RIGHT;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.OBJECT;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.OVERRIDE;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.PACKAGE;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.PUBLIC;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.SET;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.SLIP_FLAG;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.SLIP_FLAG_2;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.SOURCE;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.SOURCE_NAME;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.TARGET_NAME;
import static org.dinghuuang.bean.constants.ClassTemplateConstants.VOID;

import org.dinghuuang.bean.conversion.ConversionContext;
import org.dinghuuang.bean.conversion.ConversionProvider;
import org.dinghuuang.bean.conversion.Conversions;
import org.dinghuuang.bean.exception.ConversionException;
import org.dinghuuang.bean.service.ConversionService;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version METHOD_POSITION_1.0
 * @Author: edisionding
 * @Description:
 * @Date: since 2022/7/26 14:35
 * @Modify By: edisionding
 */
public class BeanUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtils.class);

    private static final Map<String, ConversionService> conversionServiceConcurrentHashMap = new ConcurrentHashMap<>();

    public static void copy(Object source, Object target) {
        if (source == null || target == null) {
            return;
        }
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();
        if (sourceClass.isPrimitive() && targetClass.isPrimitive()) {
            //todo 说明都是基础对象 现在暂不支持
            throw new ConversionException("bean conversion not support base bean");
        }
        String className = sourceClass.getSimpleName() + "Conversion" + targetClass.getSimpleName();
        //做一个缓存
        if (conversionServiceConcurrentHashMap.get(className) != null) {
            handleFromCache(source, target, className);
        } else {
            handleFromCreate(source, target, sourceClass, targetClass, className);
        }
    }

    private static void handleFromCreate(Object source, Object target, Class<?> sourceClass,
        Class<?> targetClass, String className) {
        Map<String, Field> sourceClassFields = getFields(sourceClass);
        Map<String, Field> targetClassFields = getFields(targetClass);
        Map<String, Method> sourceMethods = getMethods(sourceClass);
        Map<String, Method> targetMethods = getMethods(targetClass);
        if (sourceClassFields.isEmpty() || targetClassFields.isEmpty()
            || sourceMethods.isEmpty() || targetMethods.isEmpty()) {
            return;
        }
        String sourceClassNameLower = getClassNameFirstLowerCase(sourceClass);
        String targetClassNameLower = getClassNameFirstLowerCase(targetClass);
        StringBuilder head = new StringBuilder(getHead(sourceClass, targetClass));
        StringBuilder method = new StringBuilder(
            getMethodBuilder(className, sourceClass, targetClass, sourceClassNameLower,
                targetClassNameLower));
        Map<String, Field> sourceFieldMaps = getFieldMap(sourceClassFields);
        for (Field targetField : targetClassFields.values()) {
            Field sourceField = sourceFieldMaps.get(targetField.getName());
            //说明存在名称一样的字段
            if (sourceField != null) {
                if (sourceField.getGenericType().getTypeName()
                    .equals(targetField.getGenericType().getTypeName())) {
                    //如果字段是同种类型的话
                    handleSameFieldType(sourceMethods, targetMethods, sourceField, targetField,
                        targetClassNameLower, sourceClassNameLower, method);
                } else {
                    handleNotSameFieldType(sourceMethods, targetMethods, sourceField, targetField,
                        targetClassNameLower, sourceClassNameLower, method, head);
                }
            }
        }
        method.append(FUNCTION_RIGHT).append(CHANGE_LINE)
            .append(OVERRIDE).append(CHANGE_LINE).append(PUBLIC).append(BLANK).append(VOID)
            .append(BLANK).append(CONVERSION_HANDLE).append(METHOD_LEFT).append(OBJECT)
            .append(BLANK)
            .append(SOURCE_NAME).append(SLIP_FLAG).append(OBJECT).append(BLANK).append(TARGET_NAME)
            .append(METHOD_RIGHT)
            .append(FUNCTION_LEFT).append(CHANGE_LINE)
            .append(CONVERSION_METHOD).append(METHOD_LEFT).append(METHOD_LEFT)
            .append(sourceClass.getSimpleName()).append(METHOD_RIGHT).append(SOURCE_NAME)
            .append(SLIP_FLAG).append(METHOD_LEFT).append(targetClass.getSimpleName())
            .append(METHOD_RIGHT).append(TARGET_NAME).append(METHOD_RIGHT).append(END_CHAR)
            .append(FUNCTION_RIGHT);
        String classTxtStr = head.append(CHANGE_LINE).append(method).append(FUNCTION_RIGHT)
            .toString();
        doClassHandle(classTxtStr, source, target, className, sourceClass.getPackage().getName());
    }

    private static Map<String, Method> getMethods(Class<?> objectClass) {
        Map<String, Method> methods = new HashMap<>(30);
        Class<?> parent = objectClass.getSuperclass();
        if (parent != null && !parent.equals(Object.class)) {
            methods.putAll(getMethods(parent));
        }
        for (Method method : objectClass.getDeclaredMethods()) {
            //子类覆盖父类
            methods.put(method.getName(), method);
        }
        return methods;
    }

    private static Map<String, Field> getFields(Class<?> objectClass) {
        Map<String, Field> fields = new HashMap<>(30);
        Class<?> parent = objectClass.getSuperclass();
        if (parent != null && !parent.equals(Object.class)) {
            fields.putAll(getFields(parent));
        }
        for (Field field : objectClass.getDeclaredFields()) {
            //子类覆盖父类
            fields.put(field.getName(), field);
        }
        return fields;
    }

    private static void handleNotSameFieldType(Map<String, Method> sourceMethods,
        Map<String, Method> targetMethods,
        Field sourceField, Field targetField, String targetClassNameLower,
        String sourceClassNameLower,
        StringBuilder method, StringBuilder head) {
        if (sourceField.getType().equals(targetField.getType())) {
            //说明是list之类的集合里面的类型对不上，这种不处理，很难做成通用的
            LOGGER.warn("field type conversion not support.source {} target {}",
                sourceField.getGenericType().getTypeName(),
                targetField.getGenericType().getTypeName());
            return;
        }
        //说明是不同类型
        ConversionProvider conversionProvider = Conversions.getConversion(sourceField.getType(),
            targetField.getType());
        if (conversionProvider == null) {
            //todo 这边没有提供类型的自动转换
            LOGGER.warn("not register {} to {} conversion", sourceField.getType(),
                targetField.getType());
        } else {
            //根据配置的策略自动转换
            autoConversion(conversionProvider, sourceMethods, targetMethods, sourceField,
                targetField,
                targetClassNameLower, sourceClassNameLower, method);
            //把import导进去
            handleHeadImport(conversionProvider, head);
        }
    }

    private static void autoConversion(ConversionProvider conversionProvider,
        Map<String, Method> sourceMethods, Map<String, Method> targetMethods, Field sourceField,
        Field targetField,
        String targetClassNameLower,
        String sourceClassNameLower, StringBuilder method) {
        String getMethodName = getMethodNameHandle(sourceMethods, sourceField);
        String setMethodName = setMethodNameHandle(targetMethods, targetField);
        if (setMethodName != null && getMethodName != null) {
            //todo 可以扩展的上下文
            ConversionContext conversionContext = getConversionContext();
            String getSourceFieldName =
                sourceClassNameLower + SLIP_FLAG_2 + getMethodName;
            String nullCheck = conversionProvider.getToCheckExpression(
                conversionContext);
            //不为空的处理 todo 捕获异常的处理
            if (nullCheck != null) {
                method.append(IF).append(METHOD_LEFT).append(
                        nullCheck.replaceAll(SOURCE, getSourceFieldName))
                    .append(METHOD_RIGHT).append(FUNCTION_LEFT)
                    .append(CHANGE_LINE).append(targetClassNameLower)
                    .append(SLIP_FLAG_2)
                    .append(setMethodName)
                    .append(METHOD_LEFT).append(
                        conversionProvider.getToExpression(conversionContext)
                            .replaceAll(SOURCE, getSourceFieldName))
                    .append(METHOD_RIGHT).append(END_CHAR).append(CHANGE_LINE)
                    .append(FUNCTION_RIGHT).append(CHANGE_LINE);
            } else {
                method
                    .append(targetClassNameLower).append(SLIP_FLAG_2)
                    .append(setMethodName)
                    .append(METHOD_LEFT).append(
                        conversionProvider.getToExpression(conversionContext)
                            .replaceAll(SOURCE, getSourceFieldName))
                    .append(METHOD_RIGHT).append(END_CHAR).append(CHANGE_LINE);
            }
        }
    }

    private static void handleSameFieldType(Map<String, Method> sourceMethods,
        Map<String, Method> targetMethods,
        Field sourceField, Field targetField, String targetClassNameLower,
        String sourceClassNameLower,
        StringBuilder method) {
        String getMethodName = getMethodNameHandle(sourceMethods, sourceField);
        String setMethodName = setMethodNameHandle(targetMethods, targetField);
        if (setMethodName != null && getMethodName != null) {
            method.append(targetClassNameLower).append(SLIP_FLAG_2)
                .append(setMethodName)
                .append(METHOD_LEFT).append(sourceClassNameLower)
                .append(SLIP_FLAG_2)
                .append(getMethodName).append(METHOD_RIGHT).append(END_CHAR)
                .append(CHANGE_LINE);
        }
    }

    private static Map<String, Field> getFieldMap(Map<String, Field> fields) {
        Map<String, Field> map = new HashMap<>(fields.size());
        for (Field field : fields.values()) {
            map.put(field.getName(), field);
        }
        return map;
    }

    private static ConversionContext getConversionContext() {
        //todo 后续可以做成支持注解或者表达式的扩展
        return new ConversionContext() {
            @Override
            public String getDateFormat() {
                return null;
            }

            @Override
            public String getNumberFormat() {
                return null;
            }
        };
    }

    private static void handleFromCache(Object source, Object target, String className) {
        try {
            conversionServiceConcurrentHashMap.get(className).conversionHandle(source, target);
        } catch (Exception e) {
            throw new ConversionException("conversion from cache error", e);
        }
    }

    private static void doClassHandle(String classTxtStr, Object source, Object target,
        String className, String packageName) {
        ConversionService conversionService = (ConversionService) ClassUtil.getClassInstance(
            classTxtStr, className, packageName);
        if (conversionService == null) {
            throw new ConversionException("create class error " + className);
        }
        conversionService.conversionHandle(source, target);
        conversionServiceConcurrentHashMap.put(className, conversionService);
    }

    private static StringBuilder getMethodBuilder(String className, Class<?> sourceClass,
        Class<?> targetClass, String sourceClassNameLower, String targetClassNameLower) {
        StringBuilder method = new StringBuilder();
        method.append(PUBLIC).append(BLANK).append(CLASS).append(BLANK).append(className)
            .append(BLANK).append(IMPLEMENTS).append(BLANK).append(CONVERSION_SERVICE).append(BLANK)
            .append(FUNCTION_LEFT).append(CHANGE_LINE)
            .append(PUBLIC).append(BLANK).append(VOID).append(BLANK)
            .append(CONVERSION_METHOD)
            .append(METHOD_LEFT).append(sourceClass.getSimpleName()).append(BLANK)
            .append(sourceClassNameLower).append(SLIP_FLAG).append(targetClass.getSimpleName())
            .append(BLANK)
            .append(targetClassNameLower).append(METHOD_RIGHT).append(BLANK)
            .append(FUNCTION_LEFT).append(CHANGE_LINE);
        return method;
    }

    private static StringBuilder getHead(Class<?> sourceClass, Class<?> targetClass) {
        StringBuilder head = new StringBuilder();
        head.append(PACKAGE).append(BLANK).append(sourceClass.getPackage().getName())
            .append(END_CHAR)
            .append(CHANGE_LINE);
        head.append(IMPORT).append(BLANK).append(sourceClass.getName()).append(END_CHAR)
            .append(CHANGE_LINE);
        head.append(IMPORT).append(BLANK).append(targetClass.getName()).append(END_CHAR)
            .append(CHANGE_LINE);
        head.append(IMPORT).append(BLANK).append(ConversionService.class.getName()).append(END_CHAR)
            .append(CHANGE_LINE);
        return head;
    }

    private static void handleHeadImport(ConversionProvider conversionProvider,
        StringBuilder head) {
        if (conversionProvider.getConversionImports() != null
            && !conversionProvider.getConversionImports().isEmpty()) {
            for (String conversionImport : conversionProvider.getConversionImports()) {
                head.append(IMPORT).append(BLANK).append(conversionImport)
                    .append(END_CHAR).append(CHANGE_LINE);
            }
        }
    }

    private static String getMethodNameHandle(Map<String, Method> sourceMethods,
        Field sourceField) {
        //判断sourceField是否存在get方法，以及get方法返回的对象是不是相同的类型
        List<Method> sourceMethodList = sourceMethods.values().stream().filter(
            a -> a.getName().toLowerCase()
                .contains(handleBooleanFieldName(sourceField))).collect(
            Collectors.toList());
        if (sourceMethodList.isEmpty()) {
            return null;
        } else if (sourceMethodList.size() == METHOD_POSITION_1) {
            Method sourceMethod = sourceMethodList.get(0);
            return getMethodName(sourceMethod, sourceField) + METHOD_LEFT + METHOD_RIGHT;
        } else {
            for (Method sourceMethod : sourceMethodList) {
                String getMethodName = getMethodName(sourceMethod, sourceField);
                if (getMethodName != null) {
                    return getMethodName + METHOD_LEFT + METHOD_RIGHT;
                }
            }
        }
        return null;
    }

    private static String setMethodNameHandle(Map<String, Method> targetMethods,
        Field targetField) {
        //判断targetField是否存在set方法，以及set方法返回的对象是不是相同的类型
        List<Method> targetMethodList = targetMethods.values().stream().filter(
            a -> a.getName().toLowerCase()
                .contains(handleBooleanFieldName(targetField))).collect(
            Collectors.toList());
        if (targetMethodList.isEmpty()) {
            return null;
        } else if (targetMethodList.size() == METHOD_POSITION_1) {
            Method targetMethod = targetMethodList.get(0);
            return setMethodName(targetMethod, targetField);
        } else {
            for (Method targetMethod : targetMethodList) {
                String setMethodName = setMethodName(targetMethod, targetField);
                if (setMethodName != null) {
                    return setMethodName;
                }
            }
        }
        return null;
    }

    private static String handleBooleanFieldName(Field field) {
        //boolean类型特殊处理
        String fieldName = field.getName().toLowerCase();
        if (field.getType().equals(Boolean.class) && field.getName().startsWith(IS)
            && fieldName.length() > METHOD_POSITION_2) {
            fieldName = fieldName.substring(METHOD_POSITION_2);
        }
        return fieldName;
    }

    private static String setMethodName(Method targetMethod, Field targetField) {
        if (Modifier.isPublic(targetMethod.getModifiers())
            && !Modifier.isStatic(targetMethod.getModifiers())
            && targetMethod.getParameters().length == METHOD_POSITION_1
            && targetMethod.getParameters()[0].getType().equals(targetField.getType())
            && targetMethod.getReturnType().getName().equals(VOID)
            && targetMethod.getName().startsWith(SET)
            && targetMethod.getName().length() > METHOD_POSITION_3) {
            if (Boolean.class.equals(targetField.getType())) {
                return setMethodNameBoolean(targetMethod, targetField);
            } else {
                return getRealMethodName(targetMethod, targetField, METHOD_POSITION_3);
            }
        }
        return null;
    }

    private static String setMethodNameBoolean(Method targetMethod, Field targetField) {
        if (targetField.getName().startsWith(IS)) {
            //boolean类型特殊处理
            if (targetMethod.getName().substring(METHOD_POSITION_3)
                .equals(getFirstUpperCase(
                    targetField.getName().substring(METHOD_POSITION_2)))) {
                return targetMethod.getName();
            }
        } else {
            return getRealMethodName(targetMethod, targetField, METHOD_POSITION_3);
        }
        return null;
    }

    private static String getMethodName(Method sourceMethod, Field sourceField) {
        if (Modifier.isPublic(sourceMethod.getModifiers())
            && !Modifier.isStatic(sourceMethod.getModifiers())
            && sourceMethod.getParameters().length == 0
            && sourceMethod.getReturnType().equals(sourceField.getType())) {
            if (Boolean.class.equals(sourceField.getType()) || boolean.class.equals(
                sourceField.getType())) {
                return getMethodNameBoolean(sourceMethod, sourceField);
            } else {
                if (sourceMethod.getName().startsWith(GET)
                    && sourceMethod.getName().length() > METHOD_POSITION_3) {
                    return getRealMethodName(sourceMethod, sourceField, METHOD_POSITION_3);
                }
            }
        }
        return null;
    }

    private static String getMethodNameBoolean(Method sourceMethod, Field sourceField) {
        if (sourceField.getName().startsWith(IS)) {
            //boolean类型特殊处理
            if (sourceMethod.getName().substring(METHOD_POSITION_3)
                .equals(getFirstUpperCase(
                    sourceField.getName().substring(METHOD_POSITION_2)))) {
                return sourceMethod.getName();
            }
        } else {
            if (sourceMethod.getName().startsWith(GET)
                && sourceMethod.getName().length() > METHOD_POSITION_3) {
                return getRealMethodName(sourceMethod, sourceField, METHOD_POSITION_3);
            } else if (sourceMethod.getName().startsWith(IS)
                && sourceMethod.getName().length() > METHOD_POSITION_2) {
                return getRealMethodName(sourceMethod, sourceField, METHOD_POSITION_2);
            }
        }
        return null;
    }

    private static String getRealMethodName(Method method, Field sourceField, int position) {
        if ((method.getName().substring(position).equals(getFieldNameFirstUpperCase(sourceField)))
            || method.getName().substring(position).equals(sourceField.getName())) {
            return method.getName();
        }
        return null;
    }

    private static String getFieldNameFirstUpperCase(Field field) {
        return getFirstUpperCase(field.getName());
    }

    private static String getFirstUpperCase(String str) {
        return str.substring(0, METHOD_POSITION_1).toUpperCase() + str.substring(METHOD_POSITION_1);
    }

    private static String getFirstLowerCase(String str) {
        return str.substring(0, METHOD_POSITION_1).toLowerCase() + str.substring(METHOD_POSITION_1);
    }

    private static String getClassNameFirstLowerCase(Class<?> objectClass) {
        return getFirstLowerCase(objectClass.getSimpleName());
    }

}
