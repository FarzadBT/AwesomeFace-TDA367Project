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
import faces.awesome.model.characters.PlayerCharacter;
import faces.awesome.model.factories.CharacterFactory;
import faces.awesome.model.factories.ItemFactory;
import faces.awesome.services.AssetManager;
import faces.awesome.view.ScreenRepository;
import faces.awesome.events.MapChangedEvent;
import faces.awesome.model.*;
import faces.awesome.model.item.items.consumables.Bomb;
import faces.awesome.model.item.items.permanents.Hammer;
import faces.awesome.model.item.items.permanents.Sword;
import faces.awesome.services.GdxWrapperService;
import faces.awesome.services.MapStorage;
import faces.awesome.services.Tiles;
import faces.awesome.services.WorldMap;

/**
 * @author Linus Wallman
 * Updated by: Therese Sturesson, Philip Nilsson, Farzad Besharati
 *
 * TODO: Skriv vad klassen gör
 */

public class GDXWrapper extends Game {

    //TODO Flytta på dessa konstanter?
    public static final int TILE_SIZE = 32;
    public static final int VIEW_PORT_WIDTH = 1024;
    public static final int VIEW_PORT_HEIGHT = 512;


    public AssetManager assets;

    public PlayerCharacter player;
    public PlayerCtrl playerCtrl;
    public EnemyCtrl enemyCtrl;

    public MapSegment segment;
    public WorldMap world;
    public Bus bus;

    private AwesomeGame AWG;

    @Override
    public void create() {

        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();

        bus = new Bus(ThreadEnforcer.ANY);
        bus.register(this);

        //Instantiate asset manager
        assets = AssetManager.getInstance();

        //Sets the first map
        TiledMap map = new TmxMapLoader().load("core/assets/maps/theMap.tmx");

        //Wraps the TileMap for easier access
        world = new WorldMap(map, bus);

        //Creates the segment
        segment = new MapSegment(this);

        //Creates a player though the factory
        player = CharacterFactory.createPlayer(w / TILE_SIZE / 2, h / TILE_SIZE / 2, bus, "player", segment);

        //Adds itemns to the inventory
        player.addNewToInventory(ItemFactory.CreateSword());
        player.addNewToInventory(ItemFactory.CreateHammer());

        player.addNewToInventory(new Bomb(10));

        //Sets which items on which slots
        player.setSlot1(player.getInventory().getItem("Sword"));
        player.setSlot2(player.getInventory().getItem("Bomb"));

        //Creates an awesomegame for the wrapper service
        AWG = new AwesomeGame(new GdxWrapperService(this), segment, player);

        //Creates textures from available files in core/assets/
        assets.addTexture("enemy", new TextureRegion(new Texture("core/assets/enemy.png")));
        assets.addTexture("player", new TextureRegion(new Texture("core/assets/linkk.png")));
        assets.addTexture("bossEnemy", new TextureRegion(new Texture("core/assets/giantenemycrab2.png")));
        assets.addTexture("blank", new TextureRegion(new Texture("core/assets/blank.png")));

        //Permanent Items
        assets.addTexture("Sword", new TextureRegion(new Texture("core/assets/sword.png")));
        assets.addTexture("Hammer", new TextureRegion(new Texture("core/assets/sword.png")));

        //Consumable Items
        assets.addTexture("Bomb", new TextureRegion(new Texture("core/assets/bomb.png")));

        //Adds all the maps to the map storage
        MapStorage.addMap("mainMap", map);
        MapStorage.addMap("smallHouse", new TmxMapLoader().load("core/assets/maps/smallHouse.tmx"));
        MapStorage.addMap("mediumHouse", new TmxMapLoader().load("core/assets/maps/mediumHouse.tmx"));
        MapStorage.addMap("bigHouse", new TmxMapLoader().load("core/assets/maps/bigHouse.tmx"));
        MapStorage.addMap("cathedral", new TmxMapLoader().load("core/assets/maps/cathedral.tmx"));

        handleMapChangedEvent(null);

        //Creates a player ccontroller
        playerCtrl = new PlayerCtrl(player, world, segment);

        //Creates an enemy controller
        enemyCtrl = new EnemyCtrl(segment);

        //Sets the first screen to main meny screen
        ScreenRepository.setMainMenuScreen(this);

    }

    @Override
    public void render() {

        super.render();

    }

    //A method for delegation to the tiles
    public boolean isSolid(int x, int y) {

        return Tiles.isSolid(world.getCurrentMap(), x, y);

    }

    //A method for delegation to the world
    public Position setNewMap(Position position){

        return world.setNewMap(position);

    }

    //If the map changes this event will happen
    @Subscribe
    public void handleMapChangedEvent(MapChangedEvent event) {

        segment.setEnemiesInWorld(Tiles.populateWorldWithEnemies(world.getCurrentMap(), bus));

    }

    //Methods for the asset manager
    public AssetManager getAssets() {
        return assets;
    }

    public WorldMap getMap(){
        return world;
    }


    @Override
    public void dispose() {

    }

}