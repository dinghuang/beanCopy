///*
// * Copyright MapStruct Authors.
// *
// * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
// */
//package cn.webank.weup.bean.conversion;
//
//import static ConversionUtils.time;
//
//import java.sql.Time;
//import java.util.Set;
//import org.mapstruct.ap.internal.model.common.ConversionContext;
//import org.mapstruct.ap.internal.model.common.Type;
//import org.mapstruct.ap.internal.util.Collections;
//
///**
// * Conversion between {@link java.util.Date} and {@link Time}.
// *
// * @author Filip Hrisafov
// */
//public class DateToSqlTimeConversion extends SimpleConversion {
//
//    @Override
//    protected String getToExpression(ConversionContext conversionContext) {
//        return "new " + time( conversionContext ) + "( <SOURCE>.getTime() )";
//    }
//
//    @Override
//    protected Set<Type> getToConversionImportTypes(ConversionContext conversionContext) {
//        return Collections.asSet( conversionContext.getTypeFactory().getType( Time.class ) );
//    }
//
//    @Override
//    protected String getFromExpression(ConversionContext conversionContext) {
//        return "<SOURCE>";
//    }
//}
