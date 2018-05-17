package faces.awesome.model;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    //The constructor
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    //Move the position
    public Position movePos (int deltaX, int deltaY) {
        return new Position(this.x + deltaX, this.y + deltaY );
    }

    //Get the x value
    public int getX () {
        return x;
    }

    //Get the y value
    public int getY () {
        return y;
    }


    //To set coordinates. Not immutable, For testing purposes only.
    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

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

    //Positions own toString method
    @Override
    public String toString() {
        return "Position { x = " + x + ", y = " + y + " }";
    }
}
