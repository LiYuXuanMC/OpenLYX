package al.logger.client.event;

import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;

public class CancelableEvent implements Event,Cancelable {
    private boolean cancel = false;
    @Override
    public void cancel() {
        cancel = true;
    }

    @Override
    @ExportObfuscate(name = "isCanceled")
    public boolean isCanceled() {
        return cancel;
    }
}
