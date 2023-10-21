package al.nya.reflect.utils.pathfinding.astar;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author UnlegitMinecraft
 */
public class Pathfinder {

    public static final Cell[] COMMON_NEIGHBORS = new Cell[]{
            new Cell(1, 0, 0),
            new Cell(-1, 0, 0),
            new Cell(0, 1, 0),
            new Cell(0, -1, 0),
            new Cell(0, 0, 1),
            new Cell(0, 0, -1)
    };
    public static final Cell[] DIAGONAL_NEIGHBORS = new Cell[]{
            new Cell(1, 1, 0),
            new Cell(-1, -1, 0),
            new Cell(1, -1, 0),
            new Cell(-1, 1, 0),
            new Cell(0, 1, 1),
            new Cell(0, -1, -1),
            new Cell(0, 1, -1),
            new Cell(0, -1, 1),
            // also include the non-diagonal neighbors
            new Cell(1, 0, 0),
            new Cell(-1, 0, 0),
            new Cell(0, 1, 0),
            new Cell(0, -1, 0),
            new Cell(0, 0, 1),
            new Cell(0, 0, -1)
    };

    private final Cell start;
    private final Cell end;
    private final Cell[] neighbours;
    private final IWorldProvider world;

    public Pathfinder(final Cell start, final Cell end, final Cell[] neighbours, final IWorldProvider world) {
        this.start = start;
        this.end = end;
        this.neighbours = neighbours;
        this.world = world;
    }

    public Cell getStart() {
        return start;
    }

    public Cell getEnd() {
        return end;
    }

    public Cell[] getNeighbours() {
        return neighbours;
    }

    public IWorldProvider getWorld() {
        return world;
    }

    public ArrayList<Cell> findPath() {
        return findPath(Integer.MAX_VALUE);
    }

    /**
     * @param maxLoops used to prevent infinite loops caused by invalid path
     */
    public ArrayList<Cell> findPath(int maxLoops) {
        final ArrayList<Cell> open = new ArrayList<>();
        final ArrayList<Cell> closed = new ArrayList<>();

        open.add(start);

        Cell current = null;
        int currentIdx;
        int loops = 0;

        // Loop until you find the end
        while (!open.isEmpty() && loops < maxLoops) {
            // Get the current node
            current = open.get(0);
            currentIdx = 0;
            for (int i = 1; i < open.size(); i++) {
                if (open.get(i).f < current.f) {
                    current = open.get(i);
                    currentIdx = i;
                }
            }

            // Pop current off open list, add to closed list
            open.remove(currentIdx);
            closed.add(current);

            // Found the goal
            if (current.equals(end)) {
                break;
            }

            // Generate children
            final ArrayList<Cell> children = new ArrayList<>();
            for (final Cell neighbor : neighbours) {
                final Cell child = new Cell(current.x + neighbor.x, current.y + neighbor.y, current.z + neighbor.z);
                child.parent = current;

                if (world.isBlocked(child)) {
                    continue;
                }

                children.add(child);
            }

            // Loop through children
            for (final Cell child : children) {
                // Child is on the closed list
                if (closed.contains(child)) {
                    continue;
                }

                // Create the f, g, and h values
                child.g = current.g + 1;
                child.h = (int) (Math.pow(child.x - end.x, 2) + Math.pow(child.y - end.y, 2) + Math.pow(child.z - end.z, 2));
                child.f = child.g + child.h;

                // Child is already in the open list
                if (open.contains(child) && open.get(open.indexOf(child)).g > child.g) {
                    continue;
                }

                open.add(child);
            }

            loops++;
        }

        final ArrayList<Cell> path = new ArrayList<>();
        Cell cur = current;
        while (cur != null) {
            path.add(cur);
            cur = cur.parent;
        }
        // Reverse the list
        Collections.reverse(path);

        return path;
    }
}