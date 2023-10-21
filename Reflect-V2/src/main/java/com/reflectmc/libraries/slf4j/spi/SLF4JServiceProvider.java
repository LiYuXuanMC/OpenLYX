package com.reflectmc.libraries.slf4j.spi;

import com.reflectmc.libraries.slf4j.ILoggerFactory;
import com.reflectmc.libraries.slf4j.IMarkerFactory;

/**
 * This interface based on {@link java.util.ServiceLoader} paradigm. 
 * 
 * <p>It replaces the old static-binding mechanism used in SLF4J versions 1.0.x to 1.7.x.
 *
 * @author Ceki G&uml;lc&uml;
 * @since 1.8
 */
public interface SLF4JServiceProvider {

    public ILoggerFactory getLoggerFactory();

    public IMarkerFactory getMarkerFactory();

    public MDCAdapter getMDCAdapter();

    /**
     * Return the maximum API version for SLF4J that the logging
     * implementation supports.
     *
     * <p>For example: {@code "2.0.1"}.
     *
     * @return the string API version.
     */
    public String getRequestedApiVersion();

    public void initialize();
}
