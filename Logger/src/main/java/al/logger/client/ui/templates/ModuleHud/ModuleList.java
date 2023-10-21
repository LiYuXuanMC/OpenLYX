package al.logger.client.ui.templates.ModuleHud;

import al.logger.client.Logger;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.impls.Visual.Hud;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.animation.scalefactor.TAnimation;
import al.logger.client.utils.animation.scalefactor.animations.Linear;

import java.util.List;
import java.util.stream.Collectors;

public class ModuleList extends ComponentBase {

    public ModuleList() {
        super("ModuleList");
    }

    @Override
    protected void _initElements() {

    }

    @Override
    protected void _drawElement() {
        List<ModuleChanger> moduleChangers = Logger.getInstance().moduleManager.getModules().stream().filter(Module::getDisplay).map(ModuleChanger::new).collect(Collectors.toList());
        if (Hud.sort.getValue()) {
            moduleChangers.sort((o1, o2) -> Float.compare(o2.getWidthBase(), o1.getWidthBase()));
        }
        float startX = this.getPosition().getX();
        float startY = this.getPosition().getY();
        int count = 0;
        for (ModuleChanger moduleChanger : moduleChangers) {
            int index = (int) (count * Hud.colorIndex.getValue());
            moduleChanger.update();
            if ((int) moduleChanger.getPosition().getWidth() != 0) {
                moduleChanger.setProgress(index);
                moduleChanger.drawComponent(startX - moduleChanger.getPosition().getWidth(), startY, mouseX, mouseY, false);
                startY += moduleChanger.getPosition().getHeight() + 3;
            }
            count++;
        }
    }

    @Override
    protected boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        return false;
    }

    @Override
    protected boolean _mouseReleased(int mouseX, int mouseY, int mouseStatus) {
        return false;
    }

    @Override
    public void _mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {

    }

    @Override
    public void update() {

    }

    @Override
    public void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) {

    }
}
