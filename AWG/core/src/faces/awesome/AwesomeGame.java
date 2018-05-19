package faces.awesome;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import faces.awesome.controllers.PlayerCtrl;
import faces.awesome.model.*;
import faces.awesome.model.item.items.permanents.Hammer;
import faces.awesome.model.item.items.permanents.Sword;
import faces.awesome.services.MapStorage;
import faces.awesome.services.Tiles;
import faces.awesome.services.WorldMap;
import faces.awesome.view.GameScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class AwesomeGame extends Game implements Observer {
    public static final int TILE_SIZE = 32;
    public static final int VIEW_PORT_WIDTH = 1024;
    public static final int VIEW_PORT_HEIGHT = 512;


    // TO-do: instead of having a a HasA depndency, let's just use dependency inject playerCharacter where we need it.

    public PlayerCharacter player;

    public BossEnemy boss;

    public PlayerCtrl playerCtrl;

    public MapSegment segment;

    public List<Enemy> enemiesInWorld = new ArrayList<>();


    public WorldMap world;


    @Override
    public void create() {
        // setup model here.

        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();

        player = new PlayerCharacter(new Position(w / TILE_SIZE / 2, h / TILE_SIZE / 2));
        player.addNewToInventory(new Sword(this));
        player.addNewToInventory(new Hammer());
        player.setSlot1(player.getInventory().getItem("Sword"));
        player.setSlot2(player.getInventory().getItem("Hammer"));

        //boss = new BossEnemy(new Position(8, 10), this);

        TiledMap map = new TmxMapLoader().load("core/assets/maps/theMap.tmx");

        //Wraps the TileMap for easier access
        world = new WorldMap(map);

        world.addObserver(this);


        MapStorage.addMap("mainMap", map);
        MapStorage.addMap("smallHouse", new TmxMapLoader().load("core/assets/maps/smallHouse.tmx"));
        MapStorage.addMap("mediumHouse", new TmxMapLoader().load("core/assets/maps/mediumHouse.tmx"));
        MapStorage.addMap("bigHouse", new TmxMapLoader().load("core/assets/maps/bigHouse.tmx"));
        MapStorage.addMap("cathedral", new TmxMapLoader().load("core/assets/maps/cathedral.tmx"));

        segment = new MapSegment(world, enemiesInWorld, player);

        update(null, null);

        playerCtrl = new PlayerCtrl(player, world, segment);


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


    @Override
    public void update(Observable observable, Object o) {

        enemiesInWorld.clear();

        enemiesInWorld.addAll(Tiles.populateWorldWithEnemies(world.getCurrentMap(), this));

        segment.setEnemiesInWorld(enemiesInWorld);

    }
}
