package al.nya.reflect.utils.pathfinding.astar;

public class Cell {

    public final int x;
    public final int y;
    public final int z;

    public int g = 0;
    public int h = 0;
    public int f = 0;

    public Cell parent;

    public Cell(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean equals(Object o) {
        if (o instanceof Cell) {
            Cell c = (Cell) o;
            return c.x == x && c.y == y && c.z == z;
        }
        return false;
    }

    public int hashCode() {
        return x * 31 + y * 31 + z * 31;
    }

    public String toString() {
        return "Cell(" + x + ", " + y + ", " + z + ")";
    }
}