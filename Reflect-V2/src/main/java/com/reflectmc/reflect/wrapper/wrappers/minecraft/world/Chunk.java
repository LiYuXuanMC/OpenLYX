package com.reflectmc.reflect.wrapper.wrappers.minecraft.world;

import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.Entity;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.AxisAlignedBB;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@WrapperClass(deobfName = "net.minecraft.world.chunk.Chunk",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.world.chunk.Chunk",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class Chunk extends WrapperBase {
    @WrapMethod(deobfName = "getEntitiesWithinAABBForEntity",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getEntitiesWithinAABBForEntity",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getEntitiesWithinAABBForEntity;
    public Chunk(Object obj) {
        super(obj);
    }
    public void getEntitiesWithinAABBForEntity(Entity entityIn, AxisAlignedBB aabb, List<Entity> listToFill, Predicate<?> p_177414_4_)
    {
        List<Object> listToFillObj = new ArrayList<Object>();
        invokeMethod(getEntitiesWithinAABBForEntity,entityIn.getWrappedObject(),aabb.getWrappedObject(),listToFillObj,p_177414_4_);
        for (Object o : listToFillObj) {
            listToFill.add(new Entity(o));
        }
    }
}
