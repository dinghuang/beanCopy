/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.dinghuuang.bean.conversion;

import static org.dinghuuang.bean.conversion.ConversionUtils.bigInteger;

import java.math.BigInteger;
import java.util.Set;

/**
 * Conversion between {@link BigInteger} and native number types.
 *
 * @author Gunnar Morling
 */
public class BigIntegerToPrimitiveConversion extends
    SimpleConversion {

    private final Class<?> targetType;

    public BigIntegerToPrimitiveConversion(Class<?> targetType) {
        this.targetType = targetType;
    }

    @Override
    public String getToExpression(ConversionContext conversionContext) {
        return "<SOURCE>." + targetType.getName() + "Value()";
    }

    @Override
    public String getFromExpression(ConversionContext conversionContext) {
        String castString = "";
        if (targetType == float.class || targetType == double.class) {
            castString = "(long) ";
        }
        return bigInteger(conversionContext) + ".valueOf( " + castString + "<SOURCE> )";
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
