///*
// * Copyright MapStruct Authors.
// *
// * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
// */
//package cn.webank.weup.bean.conversion;
//
//import static org.mapstruct.ap.internal.util.Collections.asSet;
//
//import java.util.Set;
//import org.mapstruct.ap.internal.model.common.ConversionContext;
//import org.mapstruct.ap.internal.model.common.Type;
//
///**
// * Conversion between {@link String} and {@link Enum} types.
// *
// * @author Gunnar Morling
// */
//public class EnumStringConversion extends SimpleConversion {
//
//    @Override
//    public String getToExpression(ConversionContext conversionContext) {
//        return "<SOURCE>.name()";
//    }
//
//    @Override
//    public String getFromExpression(ConversionContext conversionContext) {
//        return "Enum.valueOf( " + conversionContext.getTargetType().createReferenceName()
//            + ".class, <SOURCE> )";
//    }
//
//    @Override
//    protected Set<Type> getFromConversionImportTypes(ConversionContext conversionContext) {
//        return asSet(
//            conversionContext.getTargetType()
//        );
//    }
//}
