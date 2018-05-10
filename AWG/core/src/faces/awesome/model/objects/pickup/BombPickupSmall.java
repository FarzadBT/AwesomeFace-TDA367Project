package faces.awesome.model.objects.pickup;

import faces.awesome.model.GameObject;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;
import faces.awesome.model.item.items.consumables.Bomb;

/**
 * Created by Mr Cornholio on 10/05/2018.
 */
public class BombPickupSmall extends GameObject {

    public BombPickupSmall(Position pos) {
        super(pos);
    }

    public void onPickup(PlayerCharacter playerCharacter) {
        Bomb bomb = new Bomb(1);
        playerCharacter.addToInventory(bomb);
    }
}
