package al.logger.client.features.modules.impls.Visual;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.render.EventText;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.FakeIDManager;
import al.logger.client.utils.UnsafeUtils;
import al.logger.client.value.impls.ModeValue;
import est.builder.annotations.Clear;

import java.util.ArrayList;
import java.util.List;

public class NameProtect extends Module {
    private ModeValue name = null;
    private Enum LoggerMode = null;

    private Enum FakeIDMode = null;
    public NameProtect() {
        super("NameProtect","Hide your real name", Category.Visual);
        List<Enum> modes = buildModes();
        name = new ModeValue("Name",modes.get(0),modes.toArray(new Enum<?>[0]));
        addValues(name);
    }
    private List<Enum> buildModes(){
        List<Enum> enums = new ArrayList<>();
        enums.add(UnsafeUtils.buildEnum("Logger",0));
        LoggerMode = enums.get(0);
        extraMode(enums);
        return enums;
    }
    @Clear(when = "Release")
    private void extraMode(List<Enum> enums){
        enums.add(UnsafeUtils.buildEnum("FakeID",20));
        FakeIDMode = enums.get(1);
    }

    @Listener
    public void onText(EventText text){
        if (name.getValue() == LoggerMode){
            text.setText(text.getText().replace(mc.getSession().getUsername(),Logger.getInstance().getLoggerUser().getUsername()));
            return;
        }
        extra(text);
    }
    private String replace(String text,String old,String new_){
        return text.replace(old,new_);
    }
    @Clear(when = "Release")
    public void extra(EventText text){
        if (name.getValue() == FakeIDMode) {
            FakeIDManager.getFakeIDs().forEach((realId,fakeId) -> {
                text.setText(replace(text.getText(),realId,fakeId));
            });
        }
    }
}
