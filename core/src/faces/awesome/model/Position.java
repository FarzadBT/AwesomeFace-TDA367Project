package faces.awesome.model;

public class Position {
    private float x;
    private float y;

    /*public Position(float x, float y){
        this(new Position(x,y));
    }*/

    public Position(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Position movePos (float deltaX, float deltaY) {
        deltaX += this.x;
        deltaY += this.y;
        return new Position(deltaX, deltaY);
    }

    public float getX () {
        return x;
    }

    public float getY () {
        return y;
    }

    @Override
    public String toString() {
        return "Position{x=" + x + ", y=" + y + '}';
    }

}
