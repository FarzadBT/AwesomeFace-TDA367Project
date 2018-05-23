package faces.awesome.model;

/*
 * @author Therese Sturesson
 *
 * The class holds a position and string (for the map name).
 */

public class WorldPosition {

    //Varible for the position and map name
    private Position position;
    private String map;

    public WorldPosition(Position position, String map) {
        this.position = position;
        this.map = map;
    }

    //Gets the x value
    public Position getPosition() {
        return position;
    }

    //Gets the name of the map
    public String getMap() {
        return map;
    }

}
