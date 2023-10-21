package al.nya.reflect.features.modules.Combat;

import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class CombatManager {
    @Getter
    @Setter
    private List<Entity> targets = new ArrayList<Entity>();
    @Getter
    @Setter
    private Entity curTarget = null;
    @Getter
    @Setter
    private Entity blockTarget = null;

    public boolean isCombating() {
        return curTarget != null || blockTarget != null;
    }
}
