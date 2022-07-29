/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.dinghuuang.bean.conversion;

import org.dinghuuang.bean.model.NativeTypes;

import java.util.Set;

/**
 * Conversion between primitive types such as {@code byte} and wrapper types such as {@link
 * Integer}.
 *
 * @author Gunnar Morling
 */
public class PrimitiveToWrapperConversion extends SimpleConversion {

    private final Class<?> sourceType;
    private final Class<?> targetType;

    public PrimitiveToWrapperConversion(Class<?> sourceType, Class<?> targetType) {
        this.sourceType = sourceType;
        this.targetType = NativeTypes.getPrimitiveType(targetType);
    }

    @Override
    public String getToExpression(ConversionContext conversionContext) {
        if (sourceType == targetType) {
            return "<SOURCE>";
        } else {
            return "(" + targetType.getName() + ") <SOURCE>";
        }
    }

    @Override
    public String getFromExpression(ConversionContext conversionContext) {
        return "<SOURCE>." + sourceType.getName() + "Value()";
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
