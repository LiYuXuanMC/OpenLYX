package al.logger.client.event;
public interface Cancelable {
    void cancel();
    boolean isCanceled();
}
