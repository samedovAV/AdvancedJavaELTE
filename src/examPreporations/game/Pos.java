package examPreporations.game;

import java.util.Objects;

public class Pos {

    private int x;

    private int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pos pos = (Pos) o;
        return x == pos.x && y == pos.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    public int getXDist() {
        return 0;
    }

    public int getYDist() {
        return 0;
    }

    public int getMaxDist() {
        return 0;
    }

    public boolean isDiagonalTo(Pos anotherPos) {
        return true;
    }

    public boolean isHorseyMoveTo(Pos anotherPos) {
        return true;
    }
}
