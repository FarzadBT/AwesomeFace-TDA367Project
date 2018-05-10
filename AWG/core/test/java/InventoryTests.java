import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;
import faces.awesome.model.item.items.consumables.Bomb;
import faces.awesome.model.item.items.instants.SingleHeart;
import faces.awesome.model.item.items.permanents.Hammer;
import faces.awesome.model.objects.pickup.BombPickupSmall;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Mr Cornholio on 24/04/2018.
 */
public class InventoryTests {
    PlayerCharacter pc;
    BombPickupSmall bs;
    Bomb b;
    Hammer h;
    SingleHeart sh;

    @BeforeEach
    public void init() {
        pc = new PlayerCharacter(new Position(0,0));
        bs = new BombPickupSmall(new Position(0,0));
        b = new Bomb(1);
        h = new Hammer();
        sh = new SingleHeart();
    }


    @Test
    public void testAddConsumable() {
        bs.onPickup(pc);
        assertTrue(pc.getInventory().isInInventory("Bomb"));
        b = (Bomb) pc.getInventory().getItem(b.getName());
        assertTrue(b.getQuantity() == 1);

        bs.onPickup(pc);
        assertTrue(b.getQuantity() == 2);
        b.use(pc.getPos(), pc.getFacing());
        assertTrue(b.getQuantity() == 1);
        b.use(pc.getPos(), pc.getFacing());
        assertTrue(b.getQuantity() == 0);
        b.use(pc.getPos(), pc.getFacing());
        assertTrue(b.getQuantity() == 0);
    }

    @Test
    public void testAddPermanent() {
        pc.addToInventory(h);
        assertTrue(pc.getInventory().isInInventory(h.getName()));

        assertTrue(pc.getInventory().getSize() == 1);
        pc.addToInventory(h);
        assertTrue(pc.getInventory().getSize() == 1);
    }

    @Test
    public void testInstant() {
        pc.addToInventory(sh);
        assertTrue(!pc.getInventory().isInInventory(sh.getName()));
        assertTrue(pc.getInventory().getSize() == 0);
        assertTrue(pc.getHealth() == 15);

        pc.decreaseHealth(5);
        assertTrue(pc.getHealth() == 10);
        pc.addToInventory(sh);
        assertTrue(pc.getHealth() == 11);
    }


}
