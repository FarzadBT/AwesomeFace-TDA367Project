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
    public float getX () {
        return x;
    }

    //Get the y value
    public float getY () {
        return y;
    }

    //Positions own toString method
    @Override
    public String toString() {
        return "Position { x = " + x + ", y = " + y + " }";
    }

}
