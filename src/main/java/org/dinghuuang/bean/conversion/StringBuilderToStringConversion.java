/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.dinghuuang.bean.conversion;

import java.util.Set;

/**
 * Handles conversion between a target type {@link StringBuilder} and {@link String}.
 */
public class StringBuilderToStringConversion extends
    SimpleConversion {

    @Override
    public String getToExpression(ConversionContext conversionContext) {
        return "<SOURCE>.toString()";
    }

    @Override
    public String getFromExpression(ConversionContext conversionContext) {
        return "new " + ConversionUtils.stringBuilder(conversionContext) + "( <SOURCE> )";
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
