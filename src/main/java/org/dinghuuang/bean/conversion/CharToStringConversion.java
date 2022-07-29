/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.dinghuuang.bean.conversion;

import java.util.Set;

/**
 * Conversion between {@code char} and {@link String}.
 *
 * @author Gunnar Morling
 */
public class CharToStringConversion extends SimpleConversion {

    @Override
    public String getToExpression(ConversionContext conversionContext) {
        return "String.valueOf( <SOURCE> )";
    }

    @Override
    public String getFromExpression(ConversionContext conversionContext) {
        return "<SOURCE>.charAt( 0 )";
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
