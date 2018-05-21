package faces.awesome.model;

/*
 * Author: Philip Nilsson
 * Updated by: Therese Sturesson
 *
 * A class that represents the position with two ints, x and y.
 */

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


    //Sets the x value
    public void setX(int x){
        this.x = x;
    }

    //Sets the y value
    public void setY(int y){
        this.y = y;
    }


    //Positions own toString method     TODO används inte?
    @Override
    public String toString() {
        return "Position { x = " + x + ", y = " + y + " }";
    }


    //Positions own equals method
    public boolean equals(Position other){
        return this.getX() == other.getX() && this.getY() == other.getY();
    }

}
