package al.logger.client.wrapper.cactus;

import lombok.Getter;

import java.util.List;

public class DeobfuscationResult {
    @Getter
    private final Status status;
    @Getter
    private final String reason;
    @Getter
    private final List<DeobfuscationItem> results;
    public DeobfuscationResult(Status status,String failReason,List<DeobfuscationItem> items){
        this.status = status;
        this.reason = failReason;
        this.results = items;
    }
    public static class DeobfuscationItem {
        @Getter
        private Type type;
        @Getter
        private Object origin;
        @Getter
        private Object deobfuscation;
        public DeobfuscationItem(Type type,Object origin,Object deobfuscation){
            this.type = type;
            this.origin = origin;
            this.deobfuscation = deobfuscation;
        }
        public enum Type {
            Field,
            Method
        }
    }
    public enum Status {
        Success,
        Fail
    }
}
