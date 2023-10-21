package al.nya.reflect.features.modules.Visual;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPostRenderLiving;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.resource.ResourceManager;
import al.nya.reflect.utils.ByteImageLocation;
import al.nya.reflect.utils.render.ColorUtils;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.model.ModelBiped;
import al.nya.reflect.wrapper.wraps.wrapper.model.ModelRenderer;
import al.nya.reflect.wrapper.wraps.wrapper.render.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class BigGod extends Module {
    public ByteImageLocation FengZhiYuan;
    public ByteImageLocation GanGa;
    public ByteImageLocation GaoChongPo;
    public ByteImageLocation TaiJun;
    public ByteImageLocation YaoEr;
    private final ArrayList<ByteImageLocation> bigHeadList = new ArrayList<>();
    public ByteImageLocation WangHang;
    public ByteImageLocation ShiShengKai;
    public ByteImageLocation TianHuang;
    public ModeValue mode = new ModeValue("HeadType", BigHeadType.FengZhiYuan, BigHeadType.values());
    public OptionValue rainbow = new OptionValue("RainBow", false);
    public OptionValue only = new OptionValue("OnlyPlayer", true);

    public BigGod() {
        super("BigGod", ModuleType.Visual);
        FengZhiYuan = new ByteImageLocation(ResourceManager.getRes("matrix.png"));
        GanGa = new ByteImageLocation(ResourceManager.getRes("ganga.png"));
        GaoChongPo = new ByteImageLocation(ResourceManager.getRes("4399.png"));
        TaiJun = new ByteImageLocation(ResourceManager.getRes("taijun.png"));
        YaoEr = new ByteImageLocation(ResourceManager.getRes("yaoer.png"));
        WangHang = new ByteImageLocation(ResourceManager.getRes("wanghang.png"));
        ShiShengKai = new ByteImageLocation(ResourceManager.getRes("shishengkai.png"));
        TianHuang = new ByteImageLocation(ResourceManager.getRes("tianhuang.png"));
        bigHeadList.add(FengZhiYuan);
        bigHeadList.add(GanGa);
        bigHeadList.add(GaoChongPo);
        bigHeadList.add(TaiJun);
        bigHeadList.add(YaoEr);
        bigHeadList.add(WangHang);
        bigHeadList.add(ShiShengKai);
        bigHeadList.add(TianHuang);
        addValues(mode, only, rainbow);
        //setEnableNoNotification(true);
    }

    @EventTarget
    public void onRenderEntity(EventPostRenderLiving e) {
        if (only.getValue() && !EntityPlayer.isEntityPlayer(e.getEntity()))
            return;
        if(!(ModelBiped.isModelBiped(e.getRenderer().getMainModel().getWrapObject())))
            return;
        if (mode.getValue() == BigHeadType.Random) {
            // 根据实体UUID 随机选择
            Random random = new Random(e.getEntity().getUniqueID().getMostSignificantBits());
            int i = random.nextInt(bigHeadList.size());
            mc.getTextureManager().bindTexture(bigHeadList.get(i).getResourceLocation());
        } else if (mode.getValue() == BigHeadType.FengZhiYuan) {
            mc.getTextureManager().bindTexture(FengZhiYuan.getResourceLocation());
        } else if (mode.getValue() == BigHeadType.GanGa) {
            mc.getTextureManager().bindTexture(GanGa.getResourceLocation());
        } else if (mode.getValue() == BigHeadType.GaoChongPo) {
            mc.getTextureManager().bindTexture(GaoChongPo.getResourceLocation());
        } else if (mode.getValue() == BigHeadType.TaiJun) {
            mc.getTextureManager().bindTexture(TaiJun.getResourceLocation());
        } else if (mode.getValue() == BigHeadType.YaoEr) {
            mc.getTextureManager().bindTexture(YaoEr.getResourceLocation());
        } else if (mode.getValue() == BigHeadType.WangHang) {
            mc.getTextureManager().bindTexture(WangHang.getResourceLocation());
        } else if (mode.getValue() == BigHeadType.ShiShengKai) {
            mc.getTextureManager().bindTexture(ShiShengKai.getResourceLocation());
        } else if (mode.getValue() == BigHeadType.TianHuang) {
            mc.getTextureManager().bindTexture(TianHuang.getResourceLocation());
        }
        GlStateManager.disableCull(); // 关闭CullFace，使正方形的两面都可见
        GlStateManager.disableDepth(); // 关闭深度缓冲，实现ESP的效果
        GL11.glPushMatrix();
        GL11.glTranslated(e.getX(), e.getY(), e.getZ());
        ModelRenderer head = new ModelBiped(e.getRenderer().getMainModel().getWrapObject()).getBipedHead();
        GL11.glTranslatef(0F, 1.5078125F, 0F);
        if(EntityPlayer.isEntityPlayer(e.getEntity()))
            GL11.glTranslated(0, -0.125, 0);
        if(e.getEntity().isSneaking())
            GL11.glTranslated(0, -0.18, 0);
        GL11.glTranslatef(head.getOffsetX(), head.getOffsetY(), head.getOffsetZ());
        float scaleFactor = -0.0625F;
        GL11.glTranslatef(head.getRotationPointX() * scaleFactor, head.getRotationPointY() * scaleFactor, head.getRotationPointZ() * scaleFactor);
        double size = 0.5;
        GL11.glRotatef(e.getEntity().getRotationYaw(), 0F, -1F, 0F);
        GL11.glRotatef(head.getRotateAngleX() * (180F / (float) Math.PI) - 90F, 1F, 0F, 0F);
        GL11.glTranslated(-size / 2D, 0D, 0D);
        double offset = 0.20;
        GL11.glTranslated(0D, -offset, 0D);
        Color color = rainbow.getValue() ? new Color(ColorUtils.rainbow(10,0,1F)) : Color.WHITE;
        GlStateManager.color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, 1F);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(1F, 0F);
        GL11.glVertex3d(size, 0, 0);
        GL11.glTexCoord2f(0F, 0F);
        GL11.glVertex3d(0, 0, 0);
        GL11.glTexCoord2f(0F, -1F);
        GL11.glVertex3d(0, 0, size);
        GL11.glTexCoord2f(1F, -1F);
        GL11.glVertex3d(size, 0, size);
        GL11.glEnd();
        GL11.glPopMatrix();
//		GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull(); // 重新开启CullFace，节省性能
    }

    public enum BigHeadType {
        Random,
        FengZhiYuan,
        GaoChongPo,
        GanGa,
        TaiJun,
        YaoEr,
        WangHang,
        ShiShengKai,
        TianHuang,
    }

}
