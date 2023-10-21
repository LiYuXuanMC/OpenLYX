package al.nya.reflect.wrapper.wraps.wrapper.world;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.utils.AxisAlignedBB;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@WrapperClass(mcpName = "net.minecraft.world.chunk.Chunk",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.world.chunk.Chunk",targetMap = Maps.Srg1_12_2)
public class Chunk extends IWrapper {
    @WrapMethod(mcpName = "getEntitiesWithinAABBForEntity",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getEntitiesWithinAABBForEntity",targetMap = Maps.Srg1_12_2)
    public static Method getEntitiesWithinAABBForEntity;
    public Chunk(Object obj) {
        super(obj);
    }
    public void getEntitiesWithinAABBForEntity(Entity entityIn, AxisAlignedBB aabb, List<Entity> listToFill, Predicate<?> p_177414_4_)
    {
        List<Object> listToFillObj = new ArrayList<Object>();
        invoke(getEntitiesWithinAABBForEntity,entityIn.getWrapObject(),aabb.getWrapObject(),listToFillObj,p_177414_4_);
        for (Object o : listToFillObj) {
            listToFill.add(new Entity(o));
        }
    }
}
