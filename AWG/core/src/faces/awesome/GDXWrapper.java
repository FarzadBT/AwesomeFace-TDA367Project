package faces.awesome;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;
import faces.awesome.controllers.EnemyCtrl;
import faces.awesome.controllers.PlayerCtrl;
import faces.awesome.controllers.ScreenRepository;
import faces.awesome.events.MapChangedEvent;
import faces.awesome.model.*;
import com.squareup.otto.Bus;
import faces.awesome.model.item.items.permanents.Hammer;
import faces.awesome.model.item.items.permanents.Sword;
import faces.awesome.services.MapStorage;
import faces.awesome.services.Tiles;
import faces.awesome.services.WorldMap;
import faces.awesome.utils.AwesomeClock;
import faces.awesome.view.GameScreen;

/**
 *
 * @author Linus Wallman
 * Updated by:
 * TODO: Skriv vad klassen gör
 *
 */


public class GDXWrapper extends Game {


    /**
     *
     * Flytta på dessa konstanter?
     * */

    public static final int TILE_SIZE = 32;
    public static final int VIEW_PORT_WIDTH = 1024;
    public static final int VIEW_PORT_HEIGHT = 512;

    // antingen behåller vi denna som statisk, eller gör den till en singleton.
    public static AssetManager assets;

    public PlayerCharacter player;
    public PlayerCtrl playerCtrl;

    public EnemyCtrl enemyCtrl;

    public MapSegment segment;
    public WorldMap world;
    public Bus bus;

    public static AwesomeClock AWG_TIME;


    @Override
    public void create() {
        // setup model here.

        AWG_TIME = new AwesomeClock();

        bus = new Bus(ThreadEnforcer.ANY);
        bus.register(this);

        //Instantiate asset manager
        assets = new AssetManager();

        TiledMap map = new TmxMapLoader().load("core/assets/maps/theMap.tmx");

        //Wraps the TileMap for easier access
        world = new WorldMap(map, bus);


        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();
        player = CharacterFactory.createPlayer(w/TILE_SIZE/2, h/TILE_SIZE/2, bus, "player");

        player.addNewToInventory(ItemFactory.CreateSword());
        player.addNewToInventory(ItemFactory.CreateHammer());

        segment = new MapSegment(world, player, bus);

        player.addNewToInventory(new Sword());
        player.addNewToInventory(new Hammer());
        player.setSlot1(player.getInventory().getItem("Sword"));
        player.setSlot2(player.getInventory().getItem("Hammer"));

        //Creates textures from available files in core/assets/
        assets.addTexture("enemy", new TextureRegion(new Texture("core/assets/enemy.png")));
        assets.addTexture("Sword", new TextureRegion(new Texture("core/assets/sword.png")));
        assets.addTexture("Hammer", new TextureRegion(new Texture("core/assets/sword.png")));
        assets.addTexture("player", new TextureRegion(new Texture("core/assets/linkk.png")));
        assets.addTexture("bossEnemy", new TextureRegion(new Texture("core/assets/giantenemycrab.png")));
        assets.addTexture("blank", new TextureRegion(new Texture("core/assets/blank.png")));

        //assets.addFileHandle("mainUi", Gdx.files.internal("core/assets/shade/skin/uiskin.json"));

        MapStorage.addMap("mainMap", map);
        MapStorage.addMap("smallHouse", new TmxMapLoader().load("core/assets/maps/smallHouse.tmx"));
        MapStorage.addMap("mediumHouse", new TmxMapLoader().load("core/assets/maps/mediumHouse.tmx"));
        MapStorage.addMap("bigHouse", new TmxMapLoader().load("core/assets/maps/bigHouse.tmx"));
        MapStorage.addMap("cathedral", new TmxMapLoader().load("core/assets/maps/cathedral.tmx"));

        handleMapChangedEvent(null);

        playerCtrl = new PlayerCtrl(player, world, segment);

        enemyCtrl = new EnemyCtrl(segment);

        ScreenRepository.setMainMenuScreen(this);
    }

    @Override
    public void render() {

        super.render();

    }

    @Override
    public void dispose() {

        //img.dispose();
    }

    @Subscribe
    public void handleMapChangedEvent(MapChangedEvent event) {

        segment.setEnemiesInWorld(Tiles.populateWorldWithEnemies(world.getCurrentMap(), bus));

    }

    public AssetManager getAssets() {
        return assets;
    }


}