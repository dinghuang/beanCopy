///*
// * Copyright MapStruct Authors.
// *
// * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
// */
//package cn.webank.weup.bean.conversion;
//
//import static ConversionUtils.sqlDate;
//
//import java.sql.Date;
//import java.util.Collections;
//import java.util.Set;
//import org.mapstruct.ap.internal.model.common.ConversionContext;
//import org.mapstruct.ap.internal.model.common.Type;
//
///**
// * Conversion between {@link java.util.Date} and {@link Date}.
// *
// * @author Filip Hrisafov
// */
//public class DateToSqlDateConversion extends SimpleConversion {
//
//    @Override
//    protected String getToExpression(ConversionContext conversionContext) {
//        return "new " + sqlDate( conversionContext ) + "( <SOURCE>.getTime() )";
//    }
//
//    @Override
//    protected Set<Type> getToConversionImportTypes(ConversionContext conversionContext) {
//        return Collections.singleton( conversionContext.getTypeFactory().getType( Date.class ) );
//    }
//
//    @Override
//    protected String getFromExpression(ConversionContext conversionContext) {
//        return "<SOURCE>";
//    }
//}
