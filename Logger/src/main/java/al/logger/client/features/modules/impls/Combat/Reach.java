package al.logger.client.features.modules.impls.Combat;

import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Hazard;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;
import al.logger.client.value.impls.DoubleValue;

public class Reach extends Module {
    private DoubleValue buildRange = new DoubleValue("BuildRange",7.0,3.0,5,0.1);
    private DoubleValue attackRange = new DoubleValue("AttackRange",7.0,3.0,3.5,0.1);
    public Reach() {
        super("Reach",Category.Combat);
        addValues(buildRange,attackRange);
        setDescription("Increase your attack range");
        setHazard(Hazard.HIGH);
    }
    @ExportObfuscate(name = "getBuildRange")
    public double getBuildRange() {
        return buildRange.getValue();
    }

    @ExportObfuscate(name = "getAttackRange")
    public double getAttackRange() {
        return attackRange.getValue();
    }
    @ExportObfuscate(name = "getMaxRange")
    public double getMaxRange(){
        return Math.max(buildRange.getValue(),attackRange.getValue());
    }
}
