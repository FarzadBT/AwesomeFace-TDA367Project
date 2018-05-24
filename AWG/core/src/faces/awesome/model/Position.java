package faces.awesome.model;

import java.util.Objects;

/**
 * @author Philip Nilsson
 * Updated by: Therese Sturesson
 *
 * A class that represents the position with two ints, x and y.
 *
 * TODO:
 * Look into why you can't just return a reference in {@link #movePos(int, int)}
 */


public class Position {

    // The x and y value
    private int x;
    private int y;

    //The constructor
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Move the position
    public Position movePos(int deltaX, int deltaY) {
        return new Position(this.x + deltaX, this.y + deltaY);
    }

    //Get the x value
    public int getX() {
        return x;
    }

    //Get the y value
    public int getY() {
        return y;
    }


    //Sets the x value
    public void setX(int x) {
        this.x = x;
    }

    //Sets the y value
    public void setY(int y) {
        this.y = y;
    }


    // Our own equals method
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o == this) {
            return true;
        }

        if (!(o instanceof Position)) {
            return false;
        }

        Position other = (Position) o;

        return this.x == other.x && this.y == other.y;

    }

    @Override
    public int hashCode() {
        // From Effective Java
        return Objects.hash(this.x, this.y);
    }

    public Position cpy() {
        return new Position(x, y);
    }

    //Our own toString method
    @Override
    public String toString() {
        return "Position { x = " + x + ", y = " + y + " }";
    }
}

