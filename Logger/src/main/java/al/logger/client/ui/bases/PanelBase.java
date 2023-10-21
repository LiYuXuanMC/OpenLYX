package al.logger.client.ui.bases;

import al.logger.client.AccessInstance;
import al.logger.client.bridge.bridges.GuiScreenBridge;

import java.util.ArrayList;
import java.util.HashMap;

public class PanelBase extends GuiScreenBridge implements AccessInstance {

    public Position position = new Position(0, 0, 0, 0);
    public HashMap<String, ComponentBase> componentBases = new HashMap<>();
    public float titleHeight = 32;

    public void addComponent(ComponentBase componentBase) {
        componentBases.put(componentBase.elementName, componentBase);
    }

    public void addComponent(ComponentBase... componentBase) {
        for (ComponentBase component : componentBase) {
            componentBases.put(component.elementName, component);
        }
    }

    public ComponentBase getComponent(String elementName) {
        return componentBases.get(elementName);
    }

    public PanelBase() {
        initVariables();
    }

    public void initVariables() {
    }

}
