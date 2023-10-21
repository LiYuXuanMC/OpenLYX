package cc.systemv.rave.event;

public class CancelableEvent extends Event{
    private boolean canceled = false;

    public void cancel(){
        this.canceled = true;
    }

    public boolean isCanceled(){
        return this.canceled;
    }
}
