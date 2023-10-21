package al.logger.client.wrapper.environment;

import lombok.Getter;

public enum Environment {
    //Minecraft
    Any("","Any"),
    MINECRAFT_VERSION_1_8_9_Forge("189Srg.srg","1.8.9"),
    MINECRAFT_VERSION_1_8_9_Vanilla("189Notch.srg","1.8.9"),
    MINECRAFT_VERSION_1_12_2_Forge("1122Srg.srg","1.12.2"),
    MINECRAFT_VERSION_1_12_2_Vanilla("","1.12.2"),

    //Modification
    Forge(null,"Forge"),
    Fabric(null,"Fabric"),
    Vanilla(null,"Vanilla"),
    Lunar(null,"LunarClient"),
    Feather(null,"FeatherClient"),

    //Anti-cheats
    NPlusAntiCheat,
    MargelesAntiCheat,
    EnsembleAntiCheat,
    HyCraftScreenShot,
    DoMCerScreenShot,
    EastLandAntiCheat,

    //Mods
    OrangemarshallBlockhitAnimation,
    ;
    @Getter
    private String mapName;
    @Getter
    private String versionName;
    private Environment(){

    }
    private Environment(String mapName,String versionName){
        this.mapName = mapName;
        this.versionName = versionName;
    }
}
