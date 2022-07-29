/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.dinghuuang.bean.conversion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Conversion between {@link BigInteger} and {@link String}.
 *
 * @author Gunnar Morling
 */
public class BigIntegerToStringConversion extends AbstractNumberToStringConversion {

    public BigIntegerToStringConversion() {
        super(true);
    }

    @Override
    public String getToExpression(ConversionContext conversionContext) {
        if (requiresDecimalFormat(conversionContext)) {
            StringBuilder sb = new StringBuilder();
            appendDecimalFormatter(sb, conversionContext);
            sb.append(".format( <SOURCE> )");
            return sb.toString();
        } else {
            return "<SOURCE>.toString()";
        }
    }

    @Override
    public String getFromExpression(ConversionContext conversionContext) {
        if (requiresDecimalFormat(conversionContext)) {
            StringBuilder sb = new StringBuilder();
            sb.append("( (" + ConversionUtils.bigDecimal(conversionContext) + ") ");
            appendDecimalFormatter(sb, conversionContext);
            sb.append(".parse( <SOURCE> )");
            sb.append(" ).toBigInteger()");
            return sb.toString();
        } else {
            return "new " + ConversionUtils.bigInteger(conversionContext) + "( <SOURCE> )";
        }
    }


    private void appendDecimalFormatter(StringBuilder sb, ConversionContext conversionContext) {
        sb.append("createDecimalFormat( ");
        if (conversionContext.getNumberFormat() != null) {
            sb.append("\"");
            sb.append(conversionContext.getNumberFormat());
            sb.append("\"");
        }
        sb.append(" )");
    }

    @Override
    public Set<String> getConversionImports() {
        return new HashSet<>(Collections.singletonList(BigDecimal.class.getName()));
    }
}
