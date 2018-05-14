package faces.awesome.model;

public class WorldPosition {

    private int x;
    private int y;
    private String map;

    public WorldPosition (int x, int y, String map) {
        this.x = x;
        this.y = y;
        this.map = map;
    }

    public int getX () {
        return x;
    }

    public int getY () {
        return y;
    }

    public String getMap () {
        return map;
    }

}
