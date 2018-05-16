package faces.awesome.model;

/**
 * Created by Mr Cornholio on 08/05/2018.
 */
public class GameObject {
    // Use a vector to encapsulate playerCharacter coordinates?
    // character position, perhaps this should be represented in a different way.
    protected Position pos;

    //Character facing, north, east, west, south. Could potentially be used in the attack method
    // to always swing in the direction that the character is facing.
    protected Facing facing;

    public GameObject(Position pos) {
        this.pos = pos;
        this.facing = Facing.SOUTH;
    }

    public Facing getFacing() {
        return facing;
    }

    public void setFacing(Facing facing) {
        this.facing = facing;
    }

    //Get the position of the GameObject
    public Position getPos () {
        return pos;
    }

    //Set the position of the GameObject
    public void setPos (Position pos) {
        this.pos = pos;
    }
}
