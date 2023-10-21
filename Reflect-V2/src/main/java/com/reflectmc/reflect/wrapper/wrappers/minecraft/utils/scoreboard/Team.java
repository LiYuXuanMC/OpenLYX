package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.scoreboard;

import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

@WrapperClass(deobfName = "net.minecraft.scoreboard.Team", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.scoreboard.Team", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class Team extends WrapperBase {
    public Team(Object obj) {
        super(obj);
    }
}
