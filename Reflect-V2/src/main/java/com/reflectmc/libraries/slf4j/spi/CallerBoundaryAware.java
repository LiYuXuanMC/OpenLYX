package com.reflectmc.libraries.slf4j.spi;

public interface CallerBoundaryAware {

    /**
     * Add a fqcn (fully qualified class name) to this event, presumed to be the caller boundary.
     * 
     * @param fqcn
     */
    void setCallerBoundary(String fqcn);
}
