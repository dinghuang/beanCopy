///*
// * Copyright MapStruct Authors.
// *
// * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
// */
//package cn.webank.weup.bean.conversion;
//
//import cn.webank.weup.bean.conversion.ConversionContext;
//import java.util.Set;
//
///**
// * Conversion between {@link Character} and {@link String}.
// *
// * @author Gunnar Morling
// */
//public class CharWrapperToStringConversion extends SimpleConversion {
//
//    @Override
//    public String getToExpression(ConversionContext conversionContext) {
//        return "<SOURCE>.toString()";
//    }
//
//    @Override
//    public String getFromExpression(ConversionContext conversionContext) {
//        return "<SOURCE>.charAt( 0 )";
//    }
//
//    @Override
//    public String to(ConversionContext conversionContext) {
//        return null;
//    }
//
//    @Override
//    public String from(ConversionContext conversionContext) {
//        return null;
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
//}
