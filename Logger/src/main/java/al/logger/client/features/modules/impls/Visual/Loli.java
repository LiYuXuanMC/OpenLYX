package al.logger.client.features.modules.impls.Visual;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EmotePostEvent;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.ChatUtils;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.model.ModelBiped;

public class Loli extends Module {
    private final OptionValue editPlayer = new OptionValue("Edit Player" , false);

    private final OptionValue riding = new OptionValue("Riding" , true);
    private final DoubleValue rax = new DoubleValue("RightArm RotateAngleX" , 5 , -5 , 0.5 , 0.1);
    private final DoubleValue ray = new DoubleValue("RightArm RotateAngleY",5 , -5 , -2.25F , 0.1);
    private final DoubleValue lax = new DoubleValue("LightArm RotateAngleX",5 , -5 , 0.5 , 0.1);
    private final DoubleValue lay = new DoubleValue("LightArm RotateAngleY",5 , -5 , -2.25F , 0.1);

    private final OptionValue AimedBow = new OptionValue("AimedBow" , true);
    private final OptionValue IsChild = new OptionValue("IsChild" , true);

    private final OptionValue editotherplayer = new OptionValue("Edit OtherPlayer", false);
    Entity entity;
    public Loli(){
        super("CustomModel" , Category.Visual);
        addValues(editPlayer, riding,rax,ray,lax,lay,AimedBow,IsChild,editotherplayer);
        rax.addCallBack(() -> editPlayer.getValue());
        ray.addCallBack(() -> editPlayer.getValue());
        lax.addCallBack(() -> editPlayer.getValue());
        lay.addCallBack(() -> editPlayer.getValue());
        AimedBow.addCallBack(() -> editPlayer.getValue());
        IsChild.addCallBack(() -> editPlayer.getValue());
        editotherplayer.addCallBack(() -> riding.getValue());
    }

    @Listener
    public void onEmote(EmotePostEvent event){
        if (editPlayer.getValue() && EntityPlayer.isEntityPlayer(event.getEntity())){
            this.entity = event.getEntity();
            setBiped(event.getBiped());
            return;
        }


        if (event.getEntity().getWrappedObject().equals(mc.getThePlayer().getWrappedObject())){
            setBiped(event.getBiped());
        }
    }

    public void setBiped(ModelBiped biped) {
        if (editotherplayer.getValue()){
            if (editPlayer.getValue()){
                biped.getBipedRightArm().setRotateAngleX(rax.getValue().floatValue());
                biped.getBipedRightArm().setRotateAngleY(ray.getValue().floatValue());
                biped.getBipedLeftArm().setRotateAngleX(lax.getValue().floatValue());
                biped.getBipedLeftArm().setRotateAngleY(lay.getValue().floatValue());
                biped.setAimedBow(AimedBow.getValue());
                biped.setIsChild(IsChild.getValue());
            }
            if (riding.getValue()){
                biped.setIsRiding(true);
                biped.getBipedLeftLeg().setRotateAngleX((float) (biped.getBipedLeftLeg().getRotateAngleX() -(Math.PI * 1f / 5f)));
                biped.getBipedLeftLeg().setRotateAngleY((float) (biped.getBipedLeftLeg().getRotateAngleY() -(Math.PI * 10f)));
                biped.getBipedRightLeg().setRotateAngleX((float) (biped.getBipedLeftLeg().getRotateAngleX() -(Math.PI * 1f / 5f)));
                biped.getBipedRightLeg().setRotateAngleY((float) (biped.getBipedLeftLeg().getRotateAngleY() -(Math.PI * 10f)));
            }
        }


        if (!editotherplayer.getValue() && mc.getGameSettings().getThirdPersonView()> 0) {
            if (editPlayer.getValue()){
                    biped.getBipedRightArm().setRotateAngleX(rax.getValue().floatValue());
                    biped.getBipedRightArm().setRotateAngleY(ray.getValue().floatValue());
                    biped.getBipedLeftArm().setRotateAngleX(lax.getValue().floatValue());
                    biped.getBipedLeftArm().setRotateAngleY(lay.getValue().floatValue());
                    biped.setAimedBow(AimedBow.getValue());
                    biped.setIsChild(IsChild.getValue());
                }
                if (riding.getValue()){
                    biped.setIsRiding(true);
                    biped.getBipedLeftLeg().setRotateAngleX((float) (biped.getBipedLeftLeg().getRotateAngleX() + -(Math.PI * 2f / 5f)));
                    biped.getBipedLeftLeg().setRotateAngleY((float) (biped.getBipedLeftLeg().getRotateAngleY() + -(Math.PI * 10f)));
                    biped.getBipedRightLeg().setRotateAngleX((float) (biped.getBipedLeftLeg().getRotateAngleX() + -(Math.PI * 2f / 5f)));
                    biped.getBipedRightLeg().setRotateAngleY((float) (biped.getBipedLeftLeg().getRotateAngleY() + -(Math.PI * 10f)));
                }
            }
    }
}
