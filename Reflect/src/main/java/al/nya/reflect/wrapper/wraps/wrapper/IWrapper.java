package al.nya.reflect.wrapper.wraps.wrapper;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

public class IWrapper {
    private final Object object;
    public IWrapper(Object obj){
        this.object = obj;
    }

    public Object getWrapObject() {
        return object;
    }
    public Object getField(Field f){
        try {
            return f.get(getWrapObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void setField(Field f,Object v){
        try {
            f.set(getWrapObject(),v);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Object invoke(Method m, Object... args){
        try {
            return m.invoke(getWrapObject(),args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean isNull(){
        return Objects.isNull(object);
    }
    public boolean equals(IWrapper wrapper){
        return Objects.equals(object,wrapper.object);
    }
}
