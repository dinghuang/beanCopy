///*
// * Copyright MapStruct Authors.
// *
// * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
// */
//package cn.webank.weup.bean.conversion;
//
//import static cn.webank.weup.bean.conversion.ConversionUtils.bigDecimal;
//import static cn.webank.weup.bean.util.Collections.asSet;
//
//import cn.webank.weup.bean.conversion.ConversionContext;
//import cn.webank.weup.bean.model.Type;
//import java.math.BigDecimal;
//import java.util.Set;
//
///**
// * Conversion between {@link BigDecimal} and {@link String}.
// *
// * @author Gunnar Morling
// */
//public class BigDecimalToStringConversion extends
//    AbstractNumberToStringConversion {
//
//    public BigDecimalToStringConversion() {
//        super(true);
//    }
//
//    @Override
//    public String getToExpression(ConversionContext conversionContext) {
//        if (requiresDecimalFormat(conversionContext)) {
//            StringBuilder sb = new StringBuilder();
//            appendDecimalFormatter(sb, conversionContext);
//            sb.append(".format( <SOURCE> )");
//            return sb.toString();
//        } else {
//            return "<SOURCE>.toString()";
//        }
//    }
//
//    @Override
//    public String getFromExpression(ConversionContext conversionContext) {
//        if (requiresDecimalFormat(conversionContext)) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("(" + bigDecimal(conversionContext) + ") ");
//            appendDecimalFormatter(sb, conversionContext);
//            sb.append(".parse( <SOURCE> )");
//            return sb.toString();
//        } else {
//            return "new " + bigDecimal(conversionContext) + "( <SOURCE> )";
//        }
//    }
//
//    @Override
//    protected Set<Type> getFromConversionImportTypes(ConversionContext conversionContext) {
//        return asSet(conversionContext.getTypeFactory().getType(BigDecimal.class));
//    }
//
//    private void appendDecimalFormatter(StringBuilder sb, ConversionContext conversionContext) {
//        sb.append("createDecimalFormat( ");
//        if (conversionContext.getNumberFormat() != null) {
//            sb.append("\"");
//            sb.append(conversionContext.getNumberFormat());
//            sb.append("\"");
//        }
//
//        sb.append(" )");
//    }
//}
