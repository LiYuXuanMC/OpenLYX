
package al.logger.client.features.modules.impls.Visual.MotionBlur;

import al.logger.client.Logger;
import al.logger.client.bridge.bridges.IResourceBridge;
import al.logger.client.utils.IOUtils;
import al.logger.client.wrapper.LoggerMC.resource.IMetadataSection;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;

import java.io.InputStream;
import java.util.Locale;

public class MotionBlurResource extends IResourceBridge {

	public InputStream getInputStream() {
		MotionBlur module = (MotionBlur) Logger.getInstance().getModuleManager().getModule(MotionBlur.class);
		double amount = module.multiplier.getValue();
		return IOUtils.toInputStream(String.format(Locale.ENGLISH,
				"{\"targets\":[\"swap\",\"previous\"],\"passes\":[{\"name\":\"phosphor\",\"intarget\":\"minecraft:main\",\"outtarget\":\"swap\",\"auxtargets\":[{\"name\":\"PrevSampler\",\"id\":\"previous\"}],\"uniforms\":[{\"name\":\"Phosphor\",\"values\":[%.2f, %.2f, %.2f]}]},{\"name\":\"blit\",\"intarget\":\"swap\",\"outtarget\":\"previous\"},{\"name\":\"blit\",\"intarget\":\"swap\",\"outtarget\":\"previous\"},{\"name\":\"blit\",\"intarget\":\"swap\",\"outtarget\":\"previous\"},{\"name\":\"blit\",\"intarget\":\"swap\",\"outtarget\":\"minecraft:main\"}]}",
				amount, amount, amount));
	}

	public boolean hasMetadata() {
		return false;
	}

	public IMetadataSection getMetadata(String metadata) {
		return null;
	}

	public ResourceLocation getResourceLocation() {
		return null;
	}

	public String getResourcePackName() {
		return null;
	}
}
