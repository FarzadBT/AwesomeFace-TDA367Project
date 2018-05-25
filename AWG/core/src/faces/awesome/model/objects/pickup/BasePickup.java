package faces.awesome.model.objects.pickup;

import faces.awesome.model.GameObject;
import faces.awesome.model.characters.PlayerCharacter;
import faces.awesome.model.Position;

/**
 * @author Farzad Besharati
 *
 * A base pickup item, when pickup items are picked up they are meant to have a direct
 * effect on the player
 */
public abstract class BasePickup extends GameObject {

    public BasePickup(Position pos, String name) {super(pos, name);}

    public abstract void onPickup(PlayerCharacter playerCharacter);

}
