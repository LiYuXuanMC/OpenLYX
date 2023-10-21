package com.reflectmc.reflect.utils.rotation;

import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.Vec3;
import lombok.Getter;
import lombok.Setter;

public class VecRotation {
    @Getter
    @Setter
    private Vec3 vec3;
    @Getter
    @Setter
    private Rotation rotation;
    public VecRotation(Vec3 vec3, Rotation rotation){
        this.vec3 = vec3;
        this.rotation = rotation;
    }
}
