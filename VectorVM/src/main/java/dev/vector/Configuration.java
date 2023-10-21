package dev.vector;

public class Configuration {
    private boolean verbose = false;
    private boolean debug = false;
    private boolean stopOnException = false;
    private boolean useBootstrapClassPath = true;
    private boolean verify = false;
    private boolean removeStringDecryptMethod = true;
    private boolean annotation = false;
    private boolean stackVirtualize = true;
    private boolean invokeVirtualize = true;
    public Configuration(){

    }

    public void setInvokeVirtualize(boolean invokeVirtualize) {
        this.invokeVirtualize = invokeVirtualize;
    }

    public boolean isInvokeVirtualize() {
        return invokeVirtualize;
    }

    public void setStackVirtualize(boolean stackVirtualize) {
        this.stackVirtualize = stackVirtualize;
    }

    public boolean isStackVirtualize() {
        return stackVirtualize;
    }

    public void setAnnotation(boolean annotation) {
        this.annotation = annotation;
    }

    public boolean isAnnotation() {
        return annotation;
    }

    public boolean isUseBootstrapClassPath() {
        return useBootstrapClassPath;
    }

    public void setUseBootstrapClassPath(boolean useBootstrapClassPath) {
        this.useBootstrapClassPath = useBootstrapClassPath;
    }

    public boolean isRemoveStringDecryptMethod() {
        return removeStringDecryptMethod;
    }

    public void setRemoveStringDecryptMethod(boolean removeStringDecryptMethod) {
        this.removeStringDecryptMethod = removeStringDecryptMethod;
    }

    public boolean isVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }

    public boolean isDebug() {
        return debug;
    }

    public boolean isStopOnException() {
        return stopOnException;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void setStopOnException(boolean stopOnException) {
        this.stopOnException = stopOnException;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }
}
