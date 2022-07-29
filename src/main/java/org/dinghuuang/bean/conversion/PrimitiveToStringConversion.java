/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.dinghuuang.bean.conversion;

import org.dinghuuang.bean.model.NativeTypes;

/**
 * Conversion between primitive types such as {@code byte} or {@code long} and {@link String}.
 *
 * @author Gunnar Morling
 */
public class PrimitiveToStringConversion extends
    AbstractNumberToStringConversion {

    public PrimitiveToStringConversion(Class<?> sourceType) {
        super(NativeTypes.isNumber(sourceType));
        if (!sourceType.isPrimitive()) {
            throw new IllegalArgumentException(sourceType + " is no primitive type.");
        }
    }

    @Override
    public String getToExpression(ConversionContext conversionContext) {
        if (requiresDecimalFormat(conversionContext)) {
            StringBuilder sb = new StringBuilder();
            appendDecimalFormatter(sb, conversionContext);
            sb.append(".format( <SOURCE> )");
            return sb.toString();
        } else {
            return "String.valueOf( <SOURCE> )";
        }
    }

    private void appendDecimalFormatter(StringBuilder sb, ConversionContext conversionContext) {
        sb.append("new ");
        sb.append(ConversionUtils.decimalFormat(conversionContext));
        sb.append("( ");

        if (conversionContext.getNumberFormat() != null) {
            sb.append("\"");
            sb.append(conversionContext.getNumberFormat());
            sb.append("\"");
        }

        sb.append(" )");
    }
}
