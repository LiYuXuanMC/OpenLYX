package cc.systemv.rave.feature.module;

import cc.systemv.rave.utils.values.Value;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SubModule {
    @Getter
    private final Enum key;
    @Getter
    private final List<Value<?>> values = new ArrayList<>();
    public SubModule(Enum key){
        this.key = key;
    }
    protected void addValues(Value<?>... vls) {
        values.addAll(Arrays.stream(vls).collect(Collectors.toList()));
    }
}
