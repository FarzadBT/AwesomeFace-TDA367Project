import faces.awesome.AwesomeGame;
import faces.awesome.model.Enemy;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;
import faces.awesome.model.item.items.consumables.Bomb;
import faces.awesome.model.item.items.instants.SingleHeart;
import faces.awesome.model.item.items.permanents.Hammer;
import faces.awesome.model.item.items.permanents.Sword;
import faces.awesome.model.objects.pickup.BombBag;
import faces.awesome.model.objects.pickup.BombPickupSmall;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Mr Cornholio on 24/04/2018.
 */
public class ItemTests {
    AwesomeGame awesomeGame = new AwesomeGame();
    PlayerCharacter playerCharacter;
    BombPickupSmall bombSmall;
    BombBag bombBag;
    Enemy e1, e2, e3;
    Bomb bomb;
    Sword sword;
    Hammer hammer;
    SingleHeart singleHeart;


    @BeforeEach
    public void init() {
        playerCharacter = awesomeGame.player;

        bombSmall = new BombPickupSmall(new Position(0,0));
        bombBag = new BombBag(new Position(0,0));

        bomb = new Bomb(1);

        hammer = new Hammer();
        sword = new Sword();

        singleHeart = new SingleHeart();

        e1 = new Enemy(new Position(1, 1), game);
        e2 = new Enemy(new Position(1, 0), game);
        e3 = new Enemy(new Position(9, 9), game);
    }


    @Test
    public void testAddConsumable() {
        bombBag.onPickup(playerCharacter);
        assertTrue(playerCharacter.getInventory().isInInventory("Bomb"));
        bomb = (Bomb) playerCharacter.getInventory().getItem(bomb.getName());

        assertTrue(bomb.getMax() == 10);
        bombBag.onPickup(playerCharacter);
        assertTrue(bomb.getMax() == 20);

        bomb.use(playerCharacter.getPos(), playerCharacter.getFacing());
        assertTrue(bomb.getQuantity() == 19);
        bombSmall.onPickup(playerCharacter);
        assertTrue(bomb.getQuantity() == 20);
    }

    @Test
    public void testAddPermanent() {
        playerCharacter.addNewToInventory(hammer);
        assertTrue(playerCharacter.getInventory().isInInventory(hammer.getName()));

        assertTrue(playerCharacter.getInventory().getSize() == 1);
        playerCharacter.addNewToInventory(hammer);
        assertTrue(playerCharacter.getInventory().getSize() == 1);
    }

    @Test
    public void testInstant() {
        playerCharacter.addNewToInventory(singleHeart);
        assertTrue(!playerCharacter.getInventory().isInInventory(singleHeart.getName()));
        assertTrue(playerCharacter.getInventory().getSize() == 0);
        assertTrue(playerCharacter.getHealth() == 15);

        playerCharacter.decreaseHealth(5);
        assertTrue(playerCharacter.getHealth() == 10);
        playerCharacter.addNewToInventory(singleHeart);
        assertTrue(playerCharacter.getHealth() == 11);
    }

    @Test
    public void testSword() {
        playerCharacter.addNewToInventory(sword);
        assertTrue(playerCharacter.getInventory().isInInventory(sword.getName()));

        playerCharacter.addNewToInventory(sword);
        assertTrue(playerCharacter.getInventory().getSize() == 1);
    }
}
