package faces.awesome.model;

/**
 * Author: Farzad Besharati
 * Updated by: Therese Sturesson
 *
 * GameObjects are interactable things in the world. They could be a placed bomb,
 * a Character walking around or a rock that can be broken
 */

public class GameObject {

    // A GameObject has a position
    protected Position pos;

    //A GameObject has a facing
    protected Facing facing;

    //A GameObject has a name
    protected String name;

    public GameObject(Position pos, String name) {
        this.pos = pos;
        this.facing = Facing.NONE;
        this.name = name;
    }

    //Get the facing of the GameObject, a gameobject doesn't necessarily have a facing though..
    public Facing getFacing() {
        return facing;
    }

    //Set the facing of the GameObject
    public void setFacing(Facing facing) {
        this.facing = facing;
    }

    // Get the position of the GameObject
    public Position getPos() {
        return pos;
    }

    // Set the position of the GameObject
    public void setPos(Position pos) {
        this.pos = pos;
    }

    //Get the name of the GameObject
    public String getName() {
        return name;
    }

}
