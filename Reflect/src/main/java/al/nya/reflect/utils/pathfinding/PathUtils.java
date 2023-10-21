package al.nya.reflect.utils.pathfinding;

import al.nya.reflect.utils.math.Vector3d;
import al.nya.reflect.utils.pathfinding.astar.Cell;
import al.nya.reflect.utils.pathfinding.astar.Pathfinder;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;

import java.util.ArrayList;
import java.util.List;

public class PathUtils {

    public static List<Vector3d> findBlinkPath(final double tpX, final double tpY, final double tpZ) {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        final List<Vector3d> positions = new ArrayList<>();

        double curX = thePlayer.getPosX();
        double curY = thePlayer.getPosY();
        double curZ = thePlayer.getPosZ();
        double distance = Math.abs(curX - tpX) + Math.abs(curY - tpY) + Math.abs(curZ - tpZ);

        for (int count = 0; distance > 0.0D; count++) {
            distance = Math.abs(curX - tpX) + Math.abs(curY - tpY) + Math.abs(curZ - tpZ);

            final double diffX = curX - tpX;
            final double diffY = curY - tpY;
            final double diffZ = curZ - tpZ;
            final double offset = (count & 1) == 0 ? 0.4D : 0.1D;

            final double minX = Math.min(Math.abs(diffX), offset);
            if (diffX < 0.0D) curX += minX;
            if (diffX > 0.0D) curX -= minX;

            final double minY = Math.min(Math.abs(diffY), 0.25D);
            if (diffY < 0.0D) curY += minY;
            if (diffY > 0.0D) curY -= minY;

            double minZ = Math.min(Math.abs(diffZ), offset);
            if (diffZ < 0.0D) curZ += minZ;
            if (diffZ > 0.0D) curZ -= minZ;

            positions.add(new Vector3d(curX, curY, curZ));
        }

        return positions;
    }

    public static List<Vec3> findBlinkPath(double curX, double curY, double curZ, final double tpX, final double tpY, final double tpZ, final double dashDistance) {
        final MinecraftWorldProvider worldProvider = new MinecraftWorldProvider(Minecraft.getMinecraft().getTheWorld());
        final Pathfinder pathfinder = new Pathfinder(new Cell((int) curX, (int) curY, (int) curZ), new Cell((int) tpX, (int) tpY, (int) tpZ),
                Pathfinder.COMMON_NEIGHBORS, worldProvider);

        return simplifyPath(pathfinder.findPath(3000), dashDistance, worldProvider);
    }

    public static List<Vector3d> findPath(final double tpX, final double tpY, final double tpZ, final double offset) {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        final List<Vector3d> positions = new ArrayList<>();
        double posX = thePlayer.getPosX();
        double posY = thePlayer.getPosY();
        double posZ = thePlayer.getPosZ();
        final double steps = Math.ceil(getDistance(posX, posY, posZ, tpX, tpY, tpZ) / offset);

        final double dX = tpX - posX;
        final double dY = tpY - posY;
        final double dZ = tpZ - posZ;

        for (double d = 1D; d <= steps; ++d) {
            positions.add(new Vector3d(posX + (dX * d) / steps, posY + (dY * d) / steps, posZ + (dZ * d) / steps));
        }

        return positions;
    }

    public static ArrayList<Vec3> simplifyPath(final ArrayList<Cell> path, final double dashDistance, final MinecraftWorldProvider worldProvider) {
        final ArrayList<Vec3> finalPath = new ArrayList<>();

        Cell cell = path.get(0);
        Vec3 vec3;
        Vec3 lastLoc = new Vec3(cell.x + 0.5, cell.y, cell.z + 0.5);
        Vec3 lastDashLoc = lastLoc;
        for (int i = 1; i < path.size() - 1; i++) {
            cell = path.get(i);
            vec3 = new Vec3(cell.x + 0.5, cell.y, cell.z + 0.5);
            boolean canContinue = true;
            if (vec3.squareDistanceTo(lastDashLoc) > dashDistance * dashDistance) {
                canContinue = false;
            } else {
                double smallX = Math.min(lastDashLoc.getXCoord(), vec3.getXCoord());
                double smallY = Math.min(lastDashLoc.getYCoord(), vec3.getYCoord());
                double smallZ = Math.min(lastDashLoc.getZCoord(), vec3.getZCoord());
                double bigX = Math.max(lastDashLoc.getXCoord(), vec3.getXCoord());
                double bigY = Math.max(lastDashLoc.getYCoord(), vec3.getYCoord());
                double bigZ = Math.max(lastDashLoc.getZCoord(), vec3.getZCoord());
                cordsLoop:
                for (int x = (int) smallX; x <= bigX; x++) {
                    for (int y = (int) smallY; y <= bigY; y++) {
                        for (int z = (int) smallZ; z <= bigZ; z++) {
                            if (worldProvider.isBlocked(x, y, z)) {
                                canContinue = false;
                                break cordsLoop;
                            }
                        }
                    }
                }
            }
            if (!canContinue) {
                finalPath.add(lastLoc);
                lastDashLoc = lastLoc;
            }
            lastLoc = vec3;
        }

        return finalPath;
    }
    private static double getDistance(final double x1, final double y1, final double z1, final double x2, final double y2, final double z2) {
        final double xDiff = x1 - x2;
        final double yDiff = y1 - y2;
        final double zDiff = z1 - z2;

        return Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff);
    }
}
