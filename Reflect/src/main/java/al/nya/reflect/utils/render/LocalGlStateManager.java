package al.nya.reflect.utils.render;

import al.nya.reflect.wrapper.wraps.wrapper.render.OpenGlHelper;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;

/* 在用这玩意你妈就死了 */
public class LocalGlStateManager {
    public static void color(float f1,float f2,float f3,float f4){
        GL11.glColor4f(f1, f2, f3, f4);
    }
    public static void disableLighting(){

    }
    public static void enableAlpha(){
        GL11.glEnable(GL11.GL_ALPHA);
    }
    public static void disableAlpha(){
        GL11.glEnable(GL11.GL_ALPHA);
    }
    public static void disableBlend()
    {
        GL11.glDisable(GL11.GL_BLEND);
    }

    public static void enableBlend()
    {
        GL11.glEnable(GL11.GL_BLEND);
    }
    public static void bindTexture(int texture)
    {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
    }
    public static void blendFunc(int srcFactor, int dstFactor)
    {
        GL11.glBlendFunc(srcFactor, dstFactor);
    }
    public static void clear(int mask)
    {
        GL11.glClear(mask);
    }

    public static void matrixMode(int mode)
    {
        GL11.glMatrixMode(mode);
    }

    public static void loadIdentity()
    {
        GL11.glLoadIdentity();
    }

    public static void pushMatrix()
    {
        GL11.glPushMatrix();
    }

    public static void popMatrix()
    {
        GL11.glPopMatrix();
    }

    public static void ortho(double left, double right, double bottom, double top, double zNear, double zFar)
    {
        GL11.glOrtho(left, right, bottom, top, zNear, zFar);
    }

    public static void rotate(float angle, float x, float y, float z)
    {
        GL11.glRotatef(angle, x, y, z);
    }

    public static void scale(float x, float y, float z)
    {
        GL11.glScalef(x, y, z);
    }

    public static void scale(double x, double y, double z)
    {
        GL11.glScaled(x, y, z);
    }

    public static void translate(float x, float y, float z)
    {
        GL11.glTranslatef(x, y, z);
    }

    public static void translate(double x, double y, double z)
    {
        GL11.glTranslated(x, y, z);
    }

    public static void multMatrix(FloatBuffer matrix)
    {
        GL11.glMultMatrix(matrix);
    }
    public static void enableTexture2D()
    {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public static void disableTexture2D()
    {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }

    public static int generateTexture()
    {
        return GL11.glGenTextures();
    }
    public static void depthMask(boolean flagIn)
    {
        GL11.glDepthMask(flagIn);
    }
    public static void disableDepth()
    {
        GL11.glDisable(GL11.GL_DEPTH);
    }

    public static void enableDepth()
    {
        GL11.glEnable(GL11.GL_DEPTH);
    }
    public static void disableCull()
    {
        GL11.glDisable(GL11.GL_CULL_FACE);
    }
    public static void enableLighting()
    {
        GL11.glEnable(GL11.GL_LIGHTING);
    }
    public static void resetColor(){
        GL11.glColor4f(-1.0F,-1.0F,-1.0F,-1.0F);
    }
    public static void tryBlendFuncSeparate(int srcFactor, int dstFactor, int srcFactorAlpha, int dstFactorAlpha)
    {
        OpenGlHelper.glBlendFunc(srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha);
    }
    public static void shadeModel(int mode)
    {
        GL11.glShadeModel(mode);
    }
    public static void enableLight(int light)
    {
        if (light == 0){
            GL11.glEnable(GL11.GL_LIGHT0);
        }else if(light == 1){
            GL11.glEnable(GL11.GL_LIGHT1);
        }else if(light == 2){
            GL11.glEnable(GL11.GL_LIGHT2);
        }else if(light == 3){
            GL11.glEnable(GL11.GL_LIGHT3);
        }else if(light == 4){
            GL11.glEnable(GL11.GL_LIGHT4);
        }else if(light == 5){
            GL11.glEnable(GL11.GL_LIGHT5);
        }else if(light == 6){
            GL11.glEnable(GL11.GL_LIGHT6);
        }else if(light == 7){
            GL11.glEnable(GL11.GL_LIGHT7);
        }
    }
    public static void disableLight(int light)
    {
        if (light == 0){
            GL11.glDisable(GL11.GL_LIGHT0);
        }else if(light == 1){
            GL11.glDisable(GL11.GL_LIGHT1);
        }else if(light == 2){
            GL11.glDisable(GL11.GL_LIGHT2);
        }else if(light == 3){
            GL11.glDisable(GL11.GL_LIGHT3);
        }else if(light == 4){
            GL11.glDisable(GL11.GL_LIGHT4);
        }else if(light == 5){
            GL11.glDisable(GL11.GL_LIGHT5);
        }else if(light == 6){
            GL11.glDisable(GL11.GL_LIGHT6);
        }else if(light == 7){
            GL11.glDisable(GL11.GL_LIGHT7);
        }
    }
    public static void disableColorMaterial(){
        GL11.glDisable(GL11.GL_COLOR_MATERIAL);
    }
    public static void enableColorMaterial(){
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
    }
    public static void alphaFunc(int func, float ref)
    {
        GL11.glAlphaFunc(func, ref);
    }
}
