package faces.awesome;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;
import faces.awesome.controllers.EnemyCtrl;
import faces.awesome.controllers.PlayerCtrl;
import faces.awesome.events.MapChangedEvent;
import faces.awesome.model.MapSegment;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;
import faces.awesome.model.item.items.permanents.Hammer;
import faces.awesome.model.item.items.permanents.Sword;
import faces.awesome.services.MapStorage;
import faces.awesome.services.Tiles;
import faces.awesome.services.WorldMap;
import faces.awesome.view.GameScreen;

public class AwesomeGame extends Game {

    public static final int TILE_SIZE = 32;
    public static final int VIEW_PORT_WIDTH = 1024;
    public static final int VIEW_PORT_HEIGHT = 512;

    // TO-do: instead of having a a HasA depndency, let's just use dependency inject playerCharacter where we need it.

    public PlayerCharacter player;
    public PlayerCtrl playerCtrl;

    public EnemyCtrl enemyCtrl;

    public MapSegment segment;
    public WorldMap world;
    public Bus bus;


    @Override
    public void create() {
        // setup model here.

        bus = new Bus(ThreadEnforcer.ANY);
        bus.register(this);

        TiledMap map = new TmxMapLoader().load("core/assets/maps/theMap.tmx");

        //Wraps the TileMap for easier access
        world = new WorldMap(map, bus);


        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();
        player = new PlayerCharacter(new Position(w / TILE_SIZE / 2, h / TILE_SIZE / 2), bus);

        segment = new MapSegment(world, player, bus);

        player.addNewToInventory(new Sword(segment));
        player.addNewToInventory(new Hammer());
        player.setSlot1(player.getInventory().getItem("Sword"));
        player.setSlot2(player.getInventory().getItem("Hammer"));

        MapStorage.addMap("mainMap", map);
        MapStorage.addMap("smallHouse", new TmxMapLoader().load("core/assets/maps/smallHouse.tmx"));
        MapStorage.addMap("mediumHouse", new TmxMapLoader().load("core/assets/maps/mediumHouse.tmx"));
        MapStorage.addMap("bigHouse", new TmxMapLoader().load("core/assets/maps/bigHouse.tmx"));
        MapStorage.addMap("cathedral", new TmxMapLoader().load("core/assets/maps/cathedral.tmx"));

        handleMapChangedEvent(null);

        playerCtrl = new PlayerCtrl(player, world, segment);

        enemyCtrl = new EnemyCtrl(segment);


        this.setScreen(new GameScreen(this, world));
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

}
