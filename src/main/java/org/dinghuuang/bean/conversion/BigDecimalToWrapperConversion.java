/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.dinghuuang.bean.conversion;

import static org.dinghuuang.bean.conversion.ConversionUtils.bigDecimal;

import org.dinghuuang.bean.model.NativeTypes;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Conversion between {@link BigDecimal} and wrappers of native number types.
 *
 * @author Gunnar Morling
 */
public class BigDecimalToWrapperConversion extends
    SimpleConversion {

    private final Class<?> targetType;

    public BigDecimalToWrapperConversion(Class<?> targetType) {
        this.targetType = NativeTypes.getPrimitiveType(targetType);
    }

    @Override
    public String getToExpression(ConversionContext conversionContext) {
        return "<SOURCE>." + targetType.getName() + "Value()";
    }

    @Override
    public String getFromExpression(ConversionContext conversionContext) {
        return bigDecimal(conversionContext) + ".valueOf( <SOURCE> )";
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
