/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.dinghuuang.bean.exception;

/**
 * Indicates a type was visited whose hierarchy was erroneous, because it has a non-existing
 * super-type.
 * <p>
 * This exception can be used to signal the MapStruct processor to postpone the generation of the
 * mappers to the next round
 *
 * @author Gunnar Morling
 */
public class ConversionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ConversionException(String message) {
        super(message);
    }

    public ConversionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConversionException(Throwable cause) {
        super(cause);
    }

}
