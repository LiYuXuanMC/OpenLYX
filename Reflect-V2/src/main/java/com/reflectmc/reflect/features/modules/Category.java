package com.reflectmc.reflect.features.modules;

import com.reflectmc.reflect.utils.render.font.ReflectUIFontReference;
import lombok.Getter;

public enum Category {
    Combat(ReflectUIFontReference.Combat),
    Visual(ReflectUIFontReference.Visual),
    Movement(ReflectUIFontReference.Movement),
    World(ReflectUIFontReference.World),
    Player(ReflectUIFontReference.Player),
    Ghost(ReflectUIFontReference.Ghost),
    MiniGame(ReflectUIFontReference.Minigames),
    Favorite(ReflectUIFontReference.StarFill),
    ;
    @Getter
    String icon;
    Category(String icon){
        this.icon = icon;
    }
}
