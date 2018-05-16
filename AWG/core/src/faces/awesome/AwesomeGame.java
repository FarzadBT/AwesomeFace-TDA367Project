package faces.awesome;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import faces.awesome.controllers.PlayerCtrl;
import faces.awesome.model.MapStorage;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;
import faces.awesome.model.WorldMap;
import faces.awesome.utils.Clock;
import faces.awesome.view.GameScreen;

public class AwesomeGame extends Game {
    public static final int TILE_SIZE = 32;
    public static final int VIEW_PORT_WIDTH = 1024;
    public static final int VIEW_PORT_HEIGHT = 512;

    // Not final for obvious reasons, represents the time in the game.
    public static Clock AWG_TIME;



    //public GameWorld world;
    // TO-do: instead of having a a HasA depndency, let's just use dependency inject playerCharacter where we need it.

    //public TiledMap map;

    //public PlayerCtrl playerCtrl;

    public WorldMap world;
    //MapStorage maps;


    @Override
    public void create() {
        // setup model here.

        AWG_TIME = new Clock();

        Map map = new TmxMapLoader().load("core/assets/theMap.tmx");

        //Wraps the TileMap for easier access
        world = new WorldMap((TiledMap) map);

        //maps = new MapStorage();
        MapStorage.AddMap("mainMap", (TiledMap) map);
        MapStorage.AddMap("smallHouse", new TmxMapLoader().load("core/assets/smallHouse.tmx"));
        MapStorage.AddMap("mediumHouse", new TmxMapLoader().load("core/assets/mediumHouse.tmx"));
        MapStorage.AddMap("bigHouse", new TmxMapLoader().load("core/assets/bigHouse.tmx"));

        this.setScreen(new GameScreen(this, world));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
    }


}
