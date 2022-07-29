///*
// * Copyright MapStruct Authors.
// *
// * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
// */
//package cn.webank.weup.bean.conversion;
//
//import static ConversionUtils.bigDecimal;
//import static org.mapstruct.ap.internal.util.Collections.asSet;
//
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.util.Set;
//import org.mapstruct.ap.internal.model.common.ConversionContext;
//import org.mapstruct.ap.internal.model.common.Type;
//
///**
// * Conversion between {@link BigDecimal} and {@link BigInteger}.
// *
// * @author Gunnar Morling
// */
//public class BigDecimalToBigIntegerConversion extends
//    SimpleConversion {
//
//    @Override
//    public String getToExpression(ConversionContext conversionContext) {
//        return "<SOURCE>.toBigInteger()";
//    }
//
//    @Override
//    public String getFromExpression(ConversionContext conversionContext) {
//        return "new "
//            + bigDecimal( conversionContext )
//            + "( <SOURCE> )";
//    }
//
//    @Override
//    protected Set<Type> getFromConversionImportTypes(ConversionContext conversionContext) {
//        return asSet( conversionContext.getTypeFactory().getType( BigDecimal.class ) );
//    }
//
//}
