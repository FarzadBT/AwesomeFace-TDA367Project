package faces.awesome.model.objects.pickup;

import faces.awesome.model.GameObject;
import faces.awesome.model.characters.PlayerCharacter;
import faces.awesome.model.Position;

/**
 * @author Farzad Besharati
 *
 * TODO skriva vad klassen gör
 */
public abstract class BasePickup extends GameObject {

    public BasePickup(Position pos, String name) {

public abstract class PickupItem extends GameObject {

    public PickupItem(Position pos, String name) {
        super(pos, name);
    }

    public abstract void onPickup(PlayerCharacter playerCharacter);

}
