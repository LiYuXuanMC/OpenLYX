package al.logger.client.ui.templates;

import al.logger.client.Logger;
import al.logger.client.features.modules.impls.World.Scaffold;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.animation.Type;
import al.logger.client.utils.animation.descricle.Animation;
import al.logger.client.wrapper.LoggerMC.item.ItemBlock;
import al.logger.client.wrapper.LoggerMC.item.ItemStack;

public class ScaffoldCount extends ComponentBase {

    private final Animation countAnimation = new Animation();

    public ScaffoldCount(String elementName) {
        super(elementName);
    }

    @Override
    protected void _initElements() {
        this.getPosition().setHeight(32);
        this.getPosition().setWidth(32);
    }

    @Override
    protected void _drawElement() {
        boolean isScaffold = Logger.getInstance().getModuleManager().getModule(Scaffold.class).isEnable();
        if (isScaffold) {
            countAnimation.start(countAnimation.getValue(), getBlocksAmount(), 0.5f, Type.LINEAR);
            countAnimation.update();
        }
    }

    @Override
    protected boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isMouseHover) {
            this.isMouseDown = true;
            return true;
        }
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

    public int getBlocksAmount() {
        int amount = 0;
        for (int i = 36; i < 45; ++i) {
            ItemStack itemStack = mc.getThePlayer().getInventoryContainer().getSlot(i).getStack();
            if (itemStack != null && ItemBlock.isItemBlock(itemStack.getItem())) {
                amount += itemStack.getStackSize();
            }
        }
        return amount;
    }
}
