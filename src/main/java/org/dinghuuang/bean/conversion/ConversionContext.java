/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.dinghuuang.bean.conversion;

import java.util.Date;

/**
 * Context object passed to conversion providers and built-in methods.
 *
 * @author Gunnar Morling
 */
public interface ConversionContext {

    /**
     * Returns the date format if this conversion or built-in method is from String to a date type
     * (e.g. {@link Date}) or vice versa.
     *
     * @return The date format if this conversion or built-in method is from String to a date type.
     * {@code null} is returned for other types or if not given.
     */
    String getDateFormat();

    String getNumberFormat();


}
