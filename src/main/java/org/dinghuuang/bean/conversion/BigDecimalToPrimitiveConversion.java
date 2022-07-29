/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.dinghuuang.bean.conversion;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Conversion between {@link BigDecimal} and native number types.
 *
 * @author Gunnar Morling
 */
public class BigDecimalToPrimitiveConversion extends
    SimpleConversion {

    private final Class<?> targetType;

    public BigDecimalToPrimitiveConversion(Class<?> targetType) {
        this.targetType = targetType;
    }

    @Override
    public String getToExpression(ConversionContext conversionContext) {
        return "<SOURCE>." + targetType.getName() + "Value()";
    }

    @Override
    public String getFromExpression(ConversionContext conversionContext) {
        return ConversionUtils.bigDecimal( conversionContext ) + ".valueOf( <SOURCE> )";
    }

    @Override
    public Set<String> getConversionImports() {
        return null;
    }

    @Override
    public Set<Exception> getConversionExceptions() {
        return null;
    }
}
