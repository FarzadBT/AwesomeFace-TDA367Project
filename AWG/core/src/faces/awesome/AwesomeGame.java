package faces.awesome;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import faces.awesome.controllers.PlayerCtrl;
import faces.awesome.model.*;
import faces.awesome.model.MapStorage;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;
import faces.awesome.model.WorldMap;
import faces.awesome.utils.Clock;
import faces.awesome.view.GameScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class AwesomeGame extends Game implements Observer {
    public static final int TILE_SIZE = 32;
    public static final int VIEW_PORT_WIDTH = 1024;
    public static final int VIEW_PORT_HEIGHT = 512;

    // Not final for obvious reasons, represents the time in the game.
    public static Clock AWG_TIME;

    // TO-do: instead of having a a HasA depndency, let's just use dependency inject playerCharacter where we need it.

    public PlayerCharacter player;

    public PlayerCtrl playerCtrl;

    //public TiledMap map;

    //public PlayerCtrl playerCtrl;

    //public MapSegment segment;

    public List<Enemy> enemiesInWorld = new ArrayList<>();
    public BossEnemy boss;

    public WorldMap world;

    public int health;
    public String HP;
    public BitmapFont HPfont;

    @Override
    public void create() {
        // setup model here.

        AWG_TIME = new Clock();

        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();

        player = new PlayerCharacter(new Position(w / TILE_SIZE / 2, h / TILE_SIZE / 2));

        boss = new BossEnemy(new Position(8, 10), this);

        Map map = new TmxMapLoader().load("core/assets/maps/theMap.tmx");

        //Wraps the TileMap for easier access
        world = new WorldMap((TiledMap) map);

        world.addObserver(this);


        MapStorage.AddMap("mainMap", (TiledMap) map);
        MapStorage.AddMap("smallHouse", new TmxMapLoader().load("core/assets/maps/smallHouse.tmx"));
        MapStorage.AddMap("mediumHouse", new TmxMapLoader().load("core/assets/maps/mediumHouse.tmx"));
        MapStorage.AddMap("bigHouse", new TmxMapLoader().load("core/assets/maps/bigHouse.tmx"));
        MapStorage.AddMap("cathedral", new TmxMapLoader().load("core/assets/maps/cathedral.tmx"));

        MapSegment segment = new MapSegment(world, enemiesInWorld, player, boss);

        update(null, null);

//        playerCtrl = new PlayerCtrl(player, world, segment);

        System.out.println(enemiesInWorld);


        health = player.getMaxHealth();
        HPfont = new BitmapFont();
        HPfont.getData().setScale(2.0f);

        this.setScreen(new GameScreen(this, world, segment));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
    }


    @Override
    public void update(Observable observable, Object o) {

        enemiesInWorld.clear();

        enemiesInWorld.addAll(Tiles.populateWorldWithEnemies(world.getCurrent(), this));

        segment.setEnemiesInWorld(enemiesInWorld);

    }
}
