import com.squareup.otto.Bus;
import faces.awesome.GDXWrapper;
import faces.awesome.model.Enemy;
import faces.awesome.model.MapSegment;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;
import faces.awesome.model.item.items.consumables.Bomb;
import faces.awesome.model.item.items.permanents.Hammer;
import faces.awesome.model.item.items.permanents.Sword;
import faces.awesome.model.objects.pickup.BombBag;
import faces.awesome.model.objects.pickup.SmallBomb;
import faces.awesome.model.objects.pickup.SmallHeart;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Mr Cornholio on 24/04/2018.
 */


public class ItemTests {
    GDXWrapper game = new GDXWrapper();


    Bus bus;
    MapSegment segment;
    PlayerCharacter playerCharacter;

    Enemy e1, e2, e3;

    Bomb bomb;
    Sword sword;

    Hammer hammer;

    SmallHeart smallHeart;
    SmallBomb smallBomb;
    BombBag bombBag;

    @BeforeEach
    public void init() {
        segment = new MapSegment(game);
        bus = new Bus();

        playerCharacter = new PlayerCharacter(new Position(1,1), bus, "player", segment);

        smallHeart = new SmallHeart(new Position(0,0));
        smallBomb = new SmallBomb(new Position(0,0));
        bombBag = new BombBag(new Position(0,0));

        bomb = new Bomb(1);

        hammer = new Hammer();
        sword = new Sword();


    }


    @Test
    public void testAddConsumable() {
        bombBag.onPickup(playerCharacter);
        assertTrue(playerCharacter.getInventory().isInInventory("Bomb"));
        bomb = (Bomb) playerCharacter.getInventory().getItem(bomb.getName());

        assertTrue(bomb.getMax() == 10);
        bombBag.onPickup(playerCharacter);
        assertTrue(bomb.getMax() == 20);

        bomb.use(playerCharacter.getPos(), playerCharacter.getFacing(), segment);
        assertTrue(bomb.getQuantity() == 19);
        smallBomb.onPickup(playerCharacter);
        assertTrue(bomb.getQuantity() == 20);
    }

    @Test
    public void testAddPermanent() {
        playerCharacter.addNewToInventory(sword);
        assertTrue(playerCharacter.getInventory().isInInventory(sword.getName()));

        assertTrue(playerCharacter.getInventory().getSize() == 1);
        playerCharacter.addNewToInventory(sword);
        assertTrue(playerCharacter.getInventory().getSize() == 1);
    }

    @Test
    public void testPickup() {
        smallHeart.onPickup(playerCharacter);
        assertTrue(playerCharacter.getHealth() == 100);

        playerCharacter.decreaseHealth(5);
        assertTrue(playerCharacter.getHealth() == 95);
        smallHeart.onPickup(playerCharacter);
        assertTrue(playerCharacter.getHealth() == 100);
    }
}