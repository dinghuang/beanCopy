/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.dinghuuang.bean.conversion;

import org.dinghuuang.bean.model.NativeTypes;

import java.util.Set;

/**
 * Conversion between wrapper types such as {@link Integer} or {@link Long}.
 *
 * @author Gunnar Morling
 */
public class WrapperToWrapperConversion extends SimpleConversion {

    private final Class<?> sourceType;
    private final Class<?> targetType;

    public WrapperToWrapperConversion(Class<?> sourceType, Class<?> targetType) {
        this.sourceType = NativeTypes.getPrimitiveType(sourceType);
        this.targetType = NativeTypes.getPrimitiveType(targetType);
    }

    @Override
    public String getToExpression(ConversionContext conversionContext) {
        if (sourceType == targetType) {
            return "<SOURCE>";
        } else {
            return "<SOURCE>." + targetType.getName() + "Value()";
        }
    }

    @Override
    public String getFromExpression(ConversionContext conversionContext) {
        if (sourceType == targetType) {
            return "<SOURCE>";
        } else {
            return "<SOURCE>." + sourceType.getName() + "Value()";
        }
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
