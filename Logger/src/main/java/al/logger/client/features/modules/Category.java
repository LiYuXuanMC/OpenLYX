package al.logger.client.features.modules;

import lombok.Getter;
import lombok.Setter;

public enum Category {
    Combat("combat", Hazard.HIGH),
    Movement("movement", Hazard.NONE),
    World("world", Hazard.NONE),
    Player("player", Hazard.NONE),
    Visual("visual", Hazard.NONE),
    Minigames("minigames", Hazard.NONE),
    Misc("misc", Hazard.NONE),
    Config("config", Hazard.NONE),
    Message("message", Hazard.NONE);
    @Getter
    private final String icon;
    @Getter
    private final Hazard hazard;
    @Getter
    @Setter
    private float scrollTo = 0f;

    Category(String icon, Hazard hazard) {
        this.icon = icon;
        this.hazard = hazard;
    }
    public static Category getCategoryByStr(String name){
        if(name.equalsIgnoreCase("combat")){
            return Combat;
        }
        if(name.equalsIgnoreCase("movement")){
            return Movement;
        }
        if(name.equalsIgnoreCase("world")){
            return World;
        }
        if(name.equalsIgnoreCase("player")){
            return Player;
        }
        if(name.equalsIgnoreCase("visual")){
            return Visual;
        }
        if(name.equalsIgnoreCase("minigames")){
            return Minigames;
        }
        if(name.equalsIgnoreCase("misc")){
            return Misc;
        }

        return null;
    }
}

