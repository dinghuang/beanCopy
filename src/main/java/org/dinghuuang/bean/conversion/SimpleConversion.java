/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.dinghuuang.bean.conversion;

/**
 * Base class for {@link ConversionProvider}s creating {@link TypeConversion}s which don't declare
 * any exception types.
 *
 * @author Gunnar Morling
 */
public abstract class SimpleConversion implements ConversionProvider {

    @Override
    public String getToCheckExpression(ConversionContext conversionContext) {
        return null;
    }

    @Override
    public String getFromCheckExpression(ConversionContext conversionContext) {
        return null;
    }

}
