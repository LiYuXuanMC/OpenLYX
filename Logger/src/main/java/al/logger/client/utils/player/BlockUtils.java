package al.logger.client.utils.player;


import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.block.Block;
import al.logger.client.wrapper.LoggerMC.block.IBlockState;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;

public class BlockUtils
{
	private static Minecraft mc = Minecraft.getMinecraft();
	
	public static Block getBlock(BlockPos pos)
	{
		return mc.getTheWorld().getBlockState(pos).getBlock();
	}
	
	public static int getBlockMeta(BlockPos pos) {
		IBlockState blockState = mc.getTheWorld().getBlockState(pos);
		return blockState.getBlock().getMetaFromState(blockState);
	}

	public static void faceBlockClient(BlockPos blockPos)
	{
		double diffX = blockPos.getX() + 0.5 - mc.getThePlayer().getPosX();
		double diffY =      //0.5
			blockPos.getY() + 0.0 - (mc.getThePlayer().getPosY() + mc.getThePlayer().getEyeHeight());
		double diffZ = blockPos.getZ() + 0.5 - mc.getThePlayer().getPosZ();
		double dist = Math.sqrt(diffX * diffX + diffZ * diffZ);
		float yaw =
			(float)(Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
		float pitch = (float)-(Math.atan2(diffY, dist) * 180.0D / Math.PI);
		mc.getThePlayer().setRotationYaw( mc.getThePlayer().getRotationYaw()
			+ wrapDegrees(yaw - mc.getThePlayer().getRotationYaw()));
		mc.getThePlayer().setRotationPitch(mc.getThePlayer().getRotationPitch()
			+ wrapDegrees(pitch -mc.getThePlayer().getRotationPitch()));
	}

	public static float getNeaestPlayerBlockDistance(double posX, double posY, double posZ)
	{
		return getBlockDistance((float)(mc.getThePlayer().getPosX() - posX), (float)(mc.getThePlayer().getPosY() + 1 - posY), (float)(mc.getThePlayer().getPosZ() - posZ));
	}
	
	public static float getBlockDistance(float xDiff, float yDiff, float zDiff)
	{
		return (float)Math.sqrt(
			(xDiff - 0.5F) * (xDiff - 0.5F) + (yDiff - 0.5F) * (yDiff - 0.5F)
				+ (zDiff - 0.5F) * (zDiff - 0.5F));
	}
	public static float wrapDegrees(float value)
	{
		value = value % 360.0F;

		if (value >= 180.0F)
		{
			value -= 360.0F;
		}

		if (value < -180.0F)
		{
			value += 360.0F;
		}

		return value;
	}
}
