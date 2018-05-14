import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;
import faces.awesome.model.item.items.consumables.Bomb;
import faces.awesome.model.item.items.instants.SingleHeart;
import faces.awesome.model.item.items.permanents.Hammer;
import faces.awesome.model.objects.pickup.BombBag;
import faces.awesome.model.objects.pickup.BombPickupSmall;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Mr Cornholio on 24/04/2018.
 */
public class InventoryTests {
    PlayerCharacter pc;
    BombPickupSmall bs;
    BombBag bb;
    Bomb b;
    Hammer h;
    SingleHeart sh;

    @BeforeEach
    public void init() {
        pc = new PlayerCharacter(new Position(0,0));
        bs = new BombPickupSmall(new Position(0,0));
        bb = new BombBag(new Position(0,0));
        b = new Bomb(1);
        h = new Hammer();
        sh = new SingleHeart();
    }


    @Test
    public void testAddConsumable() {
        bb.onPickup(pc);
        assertTrue(pc.getInventory().isInInventory("Bomb"));
        b = (Bomb) pc.getInventory().getItem(b.getName());

        assertTrue(b.getMax() == 10);
        bb.onPickup(pc);
        assertTrue(b.getMax() == 20);

        b.use(pc.getPos(), pc.getFacing());
        assertTrue(b.getQuantity() == 19);
        bs.onPickup(pc);
        assertTrue(b.getQuantity() == 20);
    }

    @Test
    public void testAddPermanent() {
        pc.addNewToInventory(h);
        assertTrue(pc.getInventory().isInInventory(h.getName()));

        assertTrue(pc.getInventory().getSize() == 1);
        pc.addNewToInventory(h);
        assertTrue(pc.getInventory().getSize() == 1);
    }

    @Test
    public void testInstant() {
        pc.addNewToInventory(sh);
        assertTrue(!pc.getInventory().isInInventory(sh.getName()));
        assertTrue(pc.getInventory().getSize() == 0);
        assertTrue(pc.getHealth() == 15);

        pc.decreaseHealth(5);
        assertTrue(pc.getHealth() == 10);
        pc.addNewToInventory(sh);
        assertTrue(pc.getHealth() == 11);
    }


}
