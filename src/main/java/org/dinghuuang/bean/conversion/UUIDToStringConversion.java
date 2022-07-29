///*
// * Copyright MapStruct Authors.
// *
// * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
// */
//package cn.webank.weup.bean.conversion;
//
//import static ConversionUtils.uuid;
//
//import java.util.Set;
//import java.util.UUID;
//import org.mapstruct.ap.internal.model.common.ConversionContext;
//import org.mapstruct.ap.internal.model.common.Type;
//import org.mapstruct.ap.internal.util.Collections;
//
///**
// * Conversion between {@link UUID} and {@link String}.
// *
// * @author Jason Bodnar
// */
//public class UUIDToStringConversion extends SimpleConversion {
//    @Override
//    protected String getToExpression(ConversionContext conversionContext) {
//        return "<SOURCE>.toString()";
//    }
//
//    @Override
//    protected String getFromExpression(ConversionContext conversionContext) {
//        return uuid( conversionContext ) + ".fromString( <SOURCE> )";
//    }
//
//    @Override
//    protected Set<Type> getFromConversionImportTypes(final ConversionContext conversionContext) {
//        return Collections.asSet( conversionContext.getTypeFactory().getType( UUID.class ) );
//    }
//}
