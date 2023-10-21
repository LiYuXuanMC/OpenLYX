package al.logger.client.transform;

import al.logger.client.Logger;
import al.logger.client.agent.Agent;
import est.builder.annotations.Export;

import java.util.ArrayList;
import java.util.List;

@Export(name = "al_logger_client_transform_TransformManager_className")
public class TransformManager {

    private final List<ClassTransformer> classTransformers = new ArrayList<>();

    public TransformManager() {

    }

    public void addTransformer(ClassTransformer transformer) {
        classTransformers.add(transformer);
    }

    /**
     * Transform classes
     *
     * @return status 0:Success 1:Some transformer failed
     */
    public int transform() {
        if (!Logger.getInstance().isMySelfObf()){
            System.out.println("transform");
        }
        int ret = 0;
        List<Class<?>> classes = new ArrayList<>();
        for (ClassTransformer classTransformer : classTransformers) {
            if (!classes.contains(classTransformer.getTargetClass()))
                classes.add(classTransformer.getTargetClass());
        }
        Agent agent = Logger.getInstance().getAgent();
        Logger.getInstance().addProgressBar("Transform", classes.size());
        int i = 0;
        for (Class<?> aClass : classes) {
            int result = agent.retransform(aClass);
            i++;
            Logger.getInstance().setProgressBar("Transform", i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!Logger.getInstance().isMySelfObf()){
                System.out.println(result);
            }
            if (result != 0) {
                ret = 1;
            }
        }
        Logger.getInstance().removeProgressBar("Transform");
        classTransformers.removeIf(classTransformer -> classTransformer instanceof OneTimeTransformer);
        return ret;
    }

    @Export(name = "al_logger_client_transform_TransformManager_onTransform_methodName")
    private static byte[] onTransform(Class<?> targetClass, byte[] classBytes) {
        if (!Logger.getInstance().isMySelfObf()){
            System.out.println(targetClass.getCanonicalName());
        }
        try {
            byte[] newBytes = classBytes;
            for (ClassTransformer classTransformer : Logger.getInstance().getTransformManager().classTransformers) {
                if (classTransformer.getTargetClass() == targetClass) {
                    newBytes = classTransformer.callTransformClass(newBytes);
                }
            }
            return newBytes;
        } catch (Exception e) {
            e.printStackTrace();
            if (!Logger.getInstance().isMySelfObf()){
                System.out.println("Exception transforming class " + targetClass.getName());
            }
        }
        return classBytes;
    }
}
