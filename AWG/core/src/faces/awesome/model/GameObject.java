package faces.awesome.model;

/**
 * Created by Mr Cornholio on 08/05/2018.
=======
/*
 * Author: Farzad Besharati
 * Updated by: Therese Sturesson
 *
 * TODO skriva vad klassen gör
 */

public class GameObject {

    // A GameObject have a position
    protected Position pos;

    //A GameObject have a facing
    protected Facing facing;

    //A GameObject have a name
    protected String name;

    public GameObject(Position pos, String name) {
        this.pos = pos;
        this.facing = Facing.SOUTH;
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
    public Position getPos () {
        return pos;
    }

    // Set the position of the GameObject
    public void setPos (Position pos) {
        this.pos = pos;
    }

    //Get the name of the GameObject
    public String getName() {
        return name;
    }

}
