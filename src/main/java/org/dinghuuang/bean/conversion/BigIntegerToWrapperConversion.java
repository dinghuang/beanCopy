/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.dinghuuang.bean.conversion;

import static org.dinghuuang.bean.conversion.ConversionUtils.bigInteger;

import org.dinghuuang.bean.model.NativeTypes;
import java.math.BigInteger;
import java.util.Set;

/**
 * Conversion between {@link BigInteger} and wrappers of native number types.
 *
 * @author Gunnar Morling
 */
public class BigIntegerToWrapperConversion extends
    SimpleConversion {

    private final Class<?> targetType;

    public BigIntegerToWrapperConversion(Class<?> targetType) {
        this.targetType = NativeTypes.getPrimitiveType(targetType);
    }

    @Override
    public String getToExpression(ConversionContext conversionContext) {
        return "<SOURCE>." + targetType.getName() + "Value()";
    }

    @Override
    public String getFromExpression(ConversionContext conversionContext) {
        String toLongValueStr = "";
        if (targetType == float.class || targetType == double.class) {
            toLongValueStr = ".longValue()";
        }

        return bigInteger(conversionContext) + ".valueOf( <SOURCE>" + toLongValueStr + " )";
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
