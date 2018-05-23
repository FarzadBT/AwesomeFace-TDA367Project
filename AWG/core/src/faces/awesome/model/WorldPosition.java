package faces.awesome.model;

/*
 * Author: Philip Nilsson
 * Updated by: Therese Sturesson
 *
 * TODO skriva vad klassen gör
 * TODO: Se över om det är möjligt att använda Position istället för x och y
 */

public class WorldPosition {

    //Varible for the x, y and map value
    private int x;
    private int y;
    private String map;

    public WorldPosition (int x, int y, String map) {
        this.x = x;
        this.y = y;
        this.map = map;
    }

    //Gets the x value
    public int getX () {
        return x;
    }

    //Gets the y value
    public int getY () {
        return y;
    }

    //Gets the name of the map
    public String getMap () {
        return map;
    }

}
