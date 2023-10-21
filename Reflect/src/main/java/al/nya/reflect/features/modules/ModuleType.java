package al.nya.reflect.features.modules;

import al.nya.reflect.resource.ResourceManager;
import al.nya.reflect.utils.ByteImageLocation;

public enum ModuleType {
    Visual(ResourceManager.clickGuiVisual),
    Combat(ResourceManager.clickGuiSword),
    Movement(ResourceManager.clickGuiMovement),
    World(ResourceManager.clickGuiWorld),
    Player(ResourceManager.clickGuiPerson),
    MiniGame(ResourceManager.clickGuiMinigame),
    Ghost(ResourceManager.clickGuiGhost),
    Favorite(ResourceManager.clickGuiStar),
    Information(ResourceManager.clickGuiInfo);

    private final ByteImageLocation byteImageLocation;

    ModuleType(ByteImageLocation byteImageLocation) {
        this.byteImageLocation = byteImageLocation;
    }

    public ByteImageLocation getByteImageLocation() {
        return byteImageLocation;
    }
}
