package faces.awesome.model;

public class Position {
    private float x;
    private float y;

    /*public Position(float x, float y){
        this(new Position(x,y));
    }*/

    //The constructor
    public Position(float x, float y){
        this.x = x;
        this.y = y;
    }

    //Move the position
    public Position movePos (float deltaX, float deltaY) {
        deltaX += this.x;
        deltaY += this.y;
        return new Position(deltaX, deltaY);
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
        return "Position{x=" + x + ", y=" + y + '}';
    }

}
