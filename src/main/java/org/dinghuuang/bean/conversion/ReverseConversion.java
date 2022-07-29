/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.dinghuuang.bean.conversion;

import java.util.Set;

/**
 * * A {@link ConversionProvider} which creates the inversed conversions for a given conversion
 * provider.
 *
 * @author Gunnar Morling
 */
public class ReverseConversion implements ConversionProvider {

    private ConversionProvider conversionProvider;

    public static ReverseConversion inverse(ConversionProvider conversionProvider) {
        return new ReverseConversion(conversionProvider);
    }

    private ReverseConversion(ConversionProvider conversionProvider) {
        this.conversionProvider = conversionProvider;
    }

    @Override
    public String getToExpression(ConversionContext conversionContext) {
        return conversionProvider.getFromExpression(conversionContext);
    }

    @Override
    public String getToCheckExpression(ConversionContext conversionContext) {
        return conversionProvider.getFromCheckExpression(conversionContext);
    }

    @Override
    public String getFromExpression(ConversionContext conversionContext) {
        return conversionProvider.getToExpression(conversionContext);
    }

    @Override
    public String getFromCheckExpression(ConversionContext conversionContext) {
        return conversionProvider.getToCheckExpression(conversionContext);
    }

    @Override
    public Set<String> getConversionImports() {
        return conversionProvider.getConversionImports();
    }

    @Override
    public Set<Exception> getConversionExceptions() {
        return conversionProvider.getConversionExceptions();
    }
}
