/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.dinghuuang.bean.conversion;

import java.util.Set;

/**
 * Implementations create inline {@link TypeConversion}s such as
 *
 * <ul>
 * <li>{@code (long)source},</li>
 * <li>{@code Integer.valueOf(source)} or</li>
 * <li>{@code new SimpleDateFormat().format( source )}.</li>
 * </ul>
 *
 * @author Gunnar Morling
 */
public interface ConversionProvider {

    /**
     * Returns the conversion string from source to target. The placeholder {@code <SOURCE>} can be
     * used to represent a reference to the source value.
     *
     * @param conversionContext A context providing optional information required for creating the
     *                          conversion.
     * @return The conversion string from source to target
     */
    String getToExpression(ConversionContext conversionContext);

    String getToCheckExpression(ConversionContext conversionContext);

    /**
     * Returns the conversion string from target to source. The placeholder {@code <SOURCE>} can be
     * used to represent a reference to the target value.
     *
     * @param conversionContext ConversionContext providing optional information required for
     *                          creating the conversion.
     * @return The conversion string from target to source
     */
    String getFromExpression(ConversionContext conversionContext);

    String getFromCheckExpression(ConversionContext conversionContext);

    Set<String> getConversionImports();

    Set<Exception> getConversionExceptions();

}
