package al.logger.client.features.modules.impls.Movement;

import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.ModuleCarrier;
import al.logger.client.features.modules.impls.Movement.fly.other.DomcerFly;
import al.logger.client.features.modules.impls.Movement.fly.other.Test;
import al.logger.client.features.modules.impls.Movement.fly.other.VanillaFly;

public class Flight extends ModuleCarrier {
    public Flight(){
        super("Flight" , "" , Category.Movement);
        addSubModule(new VanillaFly());
        addSubModule(new DomcerFly());
        addSubModule(new Test());
    }





}
