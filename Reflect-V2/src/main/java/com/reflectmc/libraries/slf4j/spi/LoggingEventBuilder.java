/**
 * Copyright (c) 2004-2021 QOS.ch
 * All rights reserved.
 *
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package com.reflectmc.libraries.slf4j.spi;

import java.util.function.Supplier;

import com.reflectmc.libraries.slf4j.Marker;
import com.reflectmc.libraries.slf4j.helpers.CheckReturnValue;

public interface LoggingEventBuilder {

    /**
     * Set the cause for the logging event being built.
     * @param cause a throwable
     * @return a LoggingEventBuilder, usually <b>this</b>.
     */
    @CheckReturnValue
    LoggingEventBuilder setCause(Throwable cause);

    /**
     * A {@link Marker marker} to the event being built.
     *
     * @param marker a Marker instance to add.
     * @return a LoggingEventBuilder, usually <b>this</b>.
     */
    @CheckReturnValue
    LoggingEventBuilder addMarker(Marker marker);

    /**
     * Add an argument to the event being built.
     *
     * @param p an Object to add.
     * @return a LoggingEventBuilder, usually <b>this</b>.
     */
    @CheckReturnValue
    LoggingEventBuilder addArgument(Object p);

    /**
     * Add an argument supplier to the event being built.
     *
     * @param objectSupplier an Object supplier to add.
     * @return a LoggingEventBuilder, usually <b>this</b>.
     */
    @CheckReturnValue
    LoggingEventBuilder addArgument(Supplier<?> objectSupplier);

    @CheckReturnValue
    LoggingEventBuilder addKeyValue(String key, Object value);

    @CheckReturnValue
    LoggingEventBuilder addKeyValue(String key, Supplier<Object> valueSupplier);

    /**
     *  Sets the message of the logging event.
     *
     *  @since 2.0.0-beta0
     */
    @CheckReturnValue
    LoggingEventBuilder setMessage(String message);

    /**
     * Sets the message of the event via a message supplier.
     *
     * @param messageSupplier supplies a String to be used as the message for the event
     * @since 2.0.0-beta0
     */
    @CheckReturnValue
    LoggingEventBuilder setMessage(Supplier<String> messageSupplier);

    void log();

    /**
     * Equivalent to calling {@link #setMessage(String)} followed by {@link #log()};
     *
     * @param message the message to log
     */
    void log(String message);

    /**
     * Equivalent to calling {@link #setMessage(String)} followed by {@link #addArgument(Object)}}
     * and then {@link #log()}
     *
     * @param message the message to log
     * @param arg an argument to be used with the message to log
     */
    void log(String message, Object arg);

    /**
     * Equivalent to calling {@link #setMessage(String)} followed by two calls to
     * {@link #addArgument(Object)} and then {@link #log()}
     *
     * @param message the message to log
     * @param arg0 first argument to be used with the message to log
     * @param arg1 second argument to be used with the message to log
     */
    void log(String message, Object arg0, Object arg1);


    /**
     * Equivalent to calling {@link #setMessage(String)} followed by zero or more calls to
     * {@link #addArgument(Object)} (depending on the size of args array) and then {@link #log()}
     *
     * @param message the message to log
     * @param args a list (actually an array) of arguments to be used with the message to log
     */
    void log(String message, Object... args);

    /**
     * Equivalent to calling {@link #setMessage(Supplier)} followed by {@link #log()}
     *
     * @param messageSupplier a Supplier returning a message of type String
     */
    void log(Supplier<String> messageSupplier);

}
