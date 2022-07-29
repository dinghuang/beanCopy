/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.dinghuuang.bean.conversion;

import java.util.Set;

/**
 * Abstract base class for {@link PrimitiveToStringConversion}, {@link WrapperToStringConversion},
 * {@link BigDecimalToStringConversion} and {@link BigIntegerToStringConversion}
 * <p>
 * Contains shared utility methods.
 *
 * @author Ciaran Liedeman
 */
public abstract class AbstractNumberToStringConversion extends
    SimpleConversion {

    private final boolean sourceTypeNumberSubclass;

    public AbstractNumberToStringConversion(boolean sourceTypeNumberSubclass) {
        this.sourceTypeNumberSubclass = sourceTypeNumberSubclass;
    }

    protected boolean requiresDecimalFormat(ConversionContext conversionContext) {
        return sourceTypeNumberSubclass && conversionContext.getNumberFormat() != null;
    }


    @Override
    public String getFromExpression(ConversionContext conversionContext) {
        return null;
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
