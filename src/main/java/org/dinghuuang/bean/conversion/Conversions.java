/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.dinghuuang.bean.conversion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Holds built-in {@link ConversionProvider}s such as from {@code int} to {@code String}.
 *
 * @author Gunnar Morling
 */
public class Conversions {

    private static final Map<String, ConversionProvider> conversions = new HashMap<>();

    public static ConversionProvider getConversion(Class<?> sourceClass, Class<?> targetClass) {
        if (sourceClass == Enum.class && targetClass == String.class) {
            return conversions.get(Enum.class.getName() + "-" + targetClass.getName());
        } else if (targetClass == Enum.class && sourceClass == String.class) {
            return conversions.get(sourceClass.getName() + "-" + Enum.class.getName());
        }
        return conversions.get(sourceClass.getName() + "-" + targetClass.getName());
    }

    private static void register(Class<?> sourceClass, Class<?> targetClass,
        ConversionProvider conversion) {
        conversions.put(sourceClass.getName() + "-" + targetClass.getName(), conversion);
        conversions.put(targetClass.getName() + "-" + sourceClass.getName(), ReverseConversion.inverse(conversion));
    }

    static {
        //native types <> native types, including wrappers
        registerNativeTypeConversion(byte.class, Byte.class);
        registerNativeTypeConversion(byte.class, short.class);
        registerNativeTypeConversion(byte.class, Short.class);
        registerNativeTypeConversion(byte.class, int.class);
        registerNativeTypeConversion(byte.class, Integer.class);
        registerNativeTypeConversion(byte.class, long.class);
        registerNativeTypeConversion(byte.class, Long.class);
        registerNativeTypeConversion(byte.class, float.class);
        registerNativeTypeConversion(byte.class, Float.class);
        registerNativeTypeConversion(byte.class, double.class);
        registerNativeTypeConversion(byte.class, Double.class);

        registerNativeTypeConversion(Byte.class, short.class);
        registerNativeTypeConversion(Byte.class, Short.class);
        registerNativeTypeConversion(Byte.class, int.class);
        registerNativeTypeConversion(Byte.class, Integer.class);
        registerNativeTypeConversion(Byte.class, long.class);
        registerNativeTypeConversion(Byte.class, Long.class);
        registerNativeTypeConversion(Byte.class, float.class);
        registerNativeTypeConversion(Byte.class, Float.class);
        registerNativeTypeConversion(Byte.class, double.class);
        registerNativeTypeConversion(Byte.class, Double.class);

        registerNativeTypeConversion(short.class, Short.class);
        registerNativeTypeConversion(short.class, int.class);
        registerNativeTypeConversion(short.class, Integer.class);
        registerNativeTypeConversion(short.class, long.class);
        registerNativeTypeConversion(short.class, Long.class);
        registerNativeTypeConversion(short.class, float.class);
        registerNativeTypeConversion(short.class, Float.class);
        registerNativeTypeConversion(short.class, double.class);
        registerNativeTypeConversion(short.class, Double.class);

        registerNativeTypeConversion(Short.class, int.class);
        registerNativeTypeConversion(Short.class, Integer.class);
        registerNativeTypeConversion(Short.class, long.class);
        registerNativeTypeConversion(Short.class, Long.class);
        registerNativeTypeConversion(Short.class, float.class);
        registerNativeTypeConversion(Short.class, Float.class);
        registerNativeTypeConversion(Short.class, double.class);
        registerNativeTypeConversion(Short.class, Double.class);

        registerNativeTypeConversion(int.class, Integer.class);
        registerNativeTypeConversion(int.class, long.class);
        registerNativeTypeConversion(int.class, Long.class);
        registerNativeTypeConversion(int.class, float.class);
        registerNativeTypeConversion(int.class, Float.class);
        registerNativeTypeConversion(int.class, double.class);
        registerNativeTypeConversion(int.class, Double.class);

        registerNativeTypeConversion(Integer.class, long.class);
        registerNativeTypeConversion(Integer.class, Long.class);
        registerNativeTypeConversion(Integer.class, float.class);
        registerNativeTypeConversion(Integer.class, Float.class);
        registerNativeTypeConversion(Integer.class, double.class);
        registerNativeTypeConversion(Integer.class, Double.class);

        registerNativeTypeConversion(long.class, Long.class);
        registerNativeTypeConversion(long.class, float.class);
        registerNativeTypeConversion(long.class, Float.class);
        registerNativeTypeConversion(long.class, double.class);
        registerNativeTypeConversion(long.class, Double.class);

        registerNativeTypeConversion(Long.class, float.class);
        registerNativeTypeConversion(Long.class, Float.class);
        registerNativeTypeConversion(Long.class, double.class);
        registerNativeTypeConversion(Long.class, Double.class);

        registerNativeTypeConversion(float.class, Float.class);
        registerNativeTypeConversion(float.class, double.class);
        registerNativeTypeConversion(float.class, Double.class);

        registerNativeTypeConversion(Float.class, double.class);
        registerNativeTypeConversion(Float.class, Double.class);

        registerNativeTypeConversion(double.class, Double.class);

        registerNativeTypeConversion(boolean.class, Boolean.class);
        registerNativeTypeConversion(char.class, Character.class);

        //BigInteger <> native types
        registerBigIntegerConversion(byte.class);
        registerBigIntegerConversion(Byte.class);
        registerBigIntegerConversion(short.class);
        registerBigIntegerConversion(Short.class);
        registerBigIntegerConversion(int.class);
        registerBigIntegerConversion(Integer.class);
        registerBigIntegerConversion(long.class);
        registerBigIntegerConversion(Long.class);
        registerBigIntegerConversion(float.class);
        registerBigIntegerConversion(Float.class);
        registerBigIntegerConversion(double.class);
        registerBigIntegerConversion(Double.class);

        //BigDecimal <> native types
        registerBigDecimalConversion(byte.class);
        registerBigDecimalConversion(Byte.class);
        registerBigDecimalConversion(short.class);
        registerBigDecimalConversion(Short.class);
        registerBigDecimalConversion(int.class);
        registerBigDecimalConversion(Integer.class);
        registerBigDecimalConversion(long.class);
        registerBigDecimalConversion(Long.class);
        registerBigDecimalConversion(float.class);
        registerBigDecimalConversion(Float.class);
        registerBigDecimalConversion(double.class);
        registerBigDecimalConversion(Double.class);

        //native types <> String
        registerToStringConversion(byte.class);
        registerToStringConversion(Byte.class);
        registerToStringConversion(short.class);
        registerToStringConversion(Short.class);
        registerToStringConversion(int.class);
        registerToStringConversion(Integer.class);
        registerToStringConversion(long.class);
        registerToStringConversion(Long.class);
        registerToStringConversion(float.class);
        registerToStringConversion(Float.class);
        registerToStringConversion(double.class);
        registerToStringConversion(Double.class);
        registerToStringConversion(boolean.class);
        registerToStringConversion(Boolean.class);
        register(char.class, String.class, new CharToStringConversion());
//        register(Character.class, String.class, new CharWrapperToStringConversion());
        register(BigInteger.class, String.class, new BigIntegerToStringConversion());
//        register(BigDecimal.class, String.class, new BigDecimalToStringConversion());
//        register(StringBuilder.class, String.class, new StringBuilderToStringConversion());
        //todo 可以做到启动的是否扫描一下加了哪些插件，这样可以吧对应的转换注册进来，支持扩展
////        registerJava8TimeConversions();
//
//        //misc.
//        register(Enum.class, String.class, new EnumStringConversion());
//        register(Date.class, String.class, new DateToStringConversion());
//        register(BigDecimal.class, BigInteger.class, new BigDecimalToBigIntegerConversion());
//
////        registerJavaTimeSqlConversions();
//
//        // java.util.Currency <~> String
//        register(Currency.class, String.class, new CurrencyToStringConversion());
//
//        register(UUID.class, String.class, new UUIDToStringConversion());
//
//        registerURLConversion();

    }

//    private void registerJava8TimeConversions() {
//
//        // Java 8 time to String
//        register(ZonedDateTime.class, String.class, new JavaZonedDateTimeToStringConversion());
//        register(LocalDate.class, String.class, new JavaLocalDateToStringConversion());
//        register(LocalDateTime.class, String.class, new JavaLocalDateTimeToStringConversion());
//        register(LocalTime.class, String.class, new JavaLocalTimeToStringConversion());
//        register(Instant.class, String.class, new StaticParseToStringConversion());
//        register(Period.class, String.class, new StaticParseToStringConversion());
//        register(Duration.class, String.class, new StaticParseToStringConversion());
//
//        // Java 8 to Date
//        register(ZonedDateTime.class, Date.class, new JavaZonedDateTimeToDateConversion());
//        register(LocalDateTime.class, Date.class, new JavaLocalDateTimeToDateConversion());
//        register(LocalDate.class, Date.class, new JavaLocalDateToDateConversion());
//        register(Instant.class, Date.class, new JavaInstantToDateConversion());
//
//    }

//    private void registerJavaTimeSqlConversions() {
//        if (isJavaSqlAvailable()) {
//            register(LocalDate.class, java.sql.Date.class, new JavaLocalDateToSqlDateConversion());
//
//            register(Date.class, Time.class, new DateToSqlTimeConversion());
//            register(Date.class, java.sql.Date.class, new DateToSqlDateConversion());
//            register(Date.class, Timestamp.class, new DateToSqlTimestampConversion());
//        }
//    }

//    private boolean isJavaSqlAvailable() {
//        return null != elementUtils.getTypeElement("java.sql.Date");
//    }

    private static void registerNativeTypeConversion(Class<?> sourceType, Class<?> targetType) {
        if (sourceType.isPrimitive() && targetType.isPrimitive()) {
            register(sourceType, targetType, new PrimitiveToPrimitiveConversion(sourceType));
        } else if (sourceType.isPrimitive() && !targetType.isPrimitive()) {
            register(sourceType, targetType,
                new PrimitiveToWrapperConversion(sourceType, targetType));
        } else if (!sourceType.isPrimitive() && targetType.isPrimitive()) {
            register(sourceType, targetType,
                ReverseConversion.inverse(new PrimitiveToWrapperConversion(targetType, sourceType)));
        } else {
            register(sourceType, targetType,
                new WrapperToWrapperConversion(sourceType, targetType));
        }
    }

    private static void registerToStringConversion(Class<?> sourceType) {
        if (sourceType.isPrimitive()) {
            register(sourceType, String.class, new PrimitiveToStringConversion(sourceType));
        } else {
            register(sourceType, String.class, new WrapperToStringConversion(sourceType));
        }
    }

    private static void registerBigIntegerConversion(Class<?> targetType) {
        if (targetType.isPrimitive()) {
            register(BigInteger.class, targetType, new BigIntegerToPrimitiveConversion(targetType));
        } else {
            register(BigInteger.class, targetType, new BigIntegerToWrapperConversion(targetType));
        }
    }

    //
    private static void registerBigDecimalConversion(Class<?> targetType) {
        if (targetType.isPrimitive()) {
            register(BigDecimal.class, targetType, new BigDecimalToPrimitiveConversion(targetType));
        } else {
            register(BigDecimal.class, targetType, new BigDecimalToWrapperConversion(targetType));
        }
    }
//
//    private void registerURLConversion() {
//        if (isJavaURLAvailable()) {
//            register(URL.class, String.class, new URLToStringConversion());
//        }
//    }
//
//    private boolean isJavaURLAvailable() {
//        return null != elementUtils.getTypeElement("java.net.URL");
//    }


}
