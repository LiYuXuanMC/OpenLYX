package al.logger.client.features.modules;

public enum Hazard {
    NONE("无危险"),
    HACK("黑客模块"),
    HIGH("危险分类");

    public final String value;

    Hazard(String value) {
        this.value = value;
    }
}
