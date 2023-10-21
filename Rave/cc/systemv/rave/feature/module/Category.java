package cc.systemv.rave.feature.module;

public enum Category {
    Combat("combat"),
    Movement("movement"),
    World("world"),
    Player("player"),
    Visual("visual"),
    Minigames("minigames"),
    Misc("misc"),
    Global("global"),
    Config("config"),
    ;

    private final String icon;
    Category(String icon) {
        this.icon = icon;
    }
}


