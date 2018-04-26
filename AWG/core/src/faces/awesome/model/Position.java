package faces.awesome.model;

public class Position {
    private float x;
    private float y;

    //The constructor
    public Position(float x, float y){
        this.x = x;
        this.y = y;
    }

    //Move the position
    public Position movePos (float deltaX, float deltaY) {
        this.x += deltaX;
        this.y += deltaY;
        return this;
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
