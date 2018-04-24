import faces.awesome.model.Player;
import faces.awesome.model.Position;
import faces.awesome.model.item.items.consumables.Bomb;
import faces.awesome.model.item.items.instants.SingleHeart;
import faces.awesome.model.item.items.permanents.Hammer;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Mr Cornholio on 24/04/2018.
 */
public class InventoryTests {
    Player p;
    Bomb b;
    Hammer h;
    SingleHeart sh;

    @BeforeEach
    public void init() {
        p = new Player(new Position(0,0));
        b = new Bomb(20);
        h = new Hammer();
        sh = new SingleHeart();
    }


    @Test
    public void testAddConsumable() {
        p.addToInventory(b);
        assertTrue(p.getInventory().isInInventory(b));

        assertTrue(b.getQuantity() == 1);
        p.addToInventory(b);
        assertTrue(b.getQuantity() == 2);
        b.use();
        assertTrue(b.getQuantity() == 1);
    }

    @Test
    public void testAddPermanent() {
        p.addToInventory(h);
        assertTrue(p.getInventory().isInInventory(h));

        assertTrue(p.getInventory().getSize() == 1);
        p.addToInventory(h);
        assertTrue(p.getInventory().getSize() == 1);
    }

    @Test
    public void testAddInstant() {
        p.addToInventory(sh);
        assertTrue(!p.getInventory().isInInventory(sh));
        assertTrue(p.getInventory().getSize() == 0);
    }
}
