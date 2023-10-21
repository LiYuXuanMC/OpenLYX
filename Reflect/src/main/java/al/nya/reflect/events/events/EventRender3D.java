package al.nya.reflect.events.events;

//EntityRenderer.renderWorldPass
public class EventRender3D extends EventRender {
    private float partialTicks;
    public EventRender3D(float partialTicks){
        this.partialTicks = partialTicks;
    }
    public float getPartialTicks(){
        return partialTicks;
    }
}
