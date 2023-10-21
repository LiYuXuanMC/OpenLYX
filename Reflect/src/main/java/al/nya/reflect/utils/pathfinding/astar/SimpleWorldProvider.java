package al.nya.reflect.utils.pathfinding.astar;

import java.util.ArrayList;

public class SimpleWorldProvider implements IWorldProvider {

    private final ArrayList<Cell> walls = new ArrayList<>();

    public void addWall(Cell cell) {
        walls.add(cell);
    }

    @Override
    public boolean isBlocked(Cell cell) {
        return walls.contains(cell);
    }
}
