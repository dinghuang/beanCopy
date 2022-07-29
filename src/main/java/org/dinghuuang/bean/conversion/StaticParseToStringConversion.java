///*
// * Copyright MapStruct Authors.
// *
// * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
// */
//package cn.webank.weup.bean.conversion;
//
//import java.util.Set;
//
///**
// * Handles conversion between a target type {@code T} and {@link String}, where {@code
// * T#parse(String)} and {@code T#toString} are inverse operations. The {@link
// * ConversionContext#getTargetType()} is used as the from target type {@code T}.
// */
//public class StaticParseToStringConversion extends SimpleConversion {
//
//    @Override
//    public String getToExpression(ConversionContext conversionContext) {
//        return "<SOURCE>.toString()";
//    }
//
//    @Override
//    public String getFromExpression(ConversionContext conversionContext) {
//        return conversionContext.getTargetType().createReferenceName() + ".parse( <SOURCE> )";
//    }
//
//    @Override
//    public Set<String> getConversionImports() {
//        return null;
//    }
//
//    @Override
//    public Set<Exception> getConversionExceptions() {
//        return null;
//    }
//
//
//}
