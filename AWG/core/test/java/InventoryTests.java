import faces.awesome.model.Player;
import faces.awesome.model.Position;
import faces.awesome.model.item.items.consumables.Bomb;
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

    @BeforeEach
    public void init() {
        p = new Player(new Position(0,0));
        b = new Bomb(20);
        h = new Hammer();
    }


    @Test
    public void testAddToInventory() {
        p.addToInventory(b);
        assertTrue(p.getInventory().isInInventory(b));

        assertTrue(b.getQuantity() == 1);
        p.addToInventory(b);
        assertTrue(b.getQuantity() == 2);
    }

    @Test
    public void testAddPermanent() {
        p.addToInventory(h);
        assertTrue(p.getInventory().isInInventory(h));
    }

}
