package faces.awesome.model;

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


    //Positions own toString method
    @Override
    public String toString() {
        return "Position { x = " + x + ", y = " + y + " }";
    }

    public boolean equals(Position other){
        return this.getX() == other.getX() && this.getY() == other.getY();
    }
}
