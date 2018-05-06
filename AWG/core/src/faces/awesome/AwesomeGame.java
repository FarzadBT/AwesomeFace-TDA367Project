package faces.awesome;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import faces.awesome.controllers.GameCtrl;
import faces.awesome.controllers.PlayerCtrl;
import faces.awesome.model.GameWorld;
import faces.awesome.model.MapStorage;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;
import faces.awesome.model.WorldMap;
import faces.awesome.view.GameScreen;

public class AwesomeGame extends Game {
    public static final int TILE_SIZE = 32;
    public static final int VIEW_PORT_WIDTH = 1024;
    public static final int VIEW_PORT_HEIGHT = 512;


    public GameWorld world;
    // TO-do: instead of having a a HasA depndency, let's just use dependency inject playerCharacter where we need it.

    //public TiledMap map;

    public PlayerCtrl playerCtrl;

    //WorldMap worlds;
    //MapStorage maps;


    @Override
    public void create() {
        // setup model here.

        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();

//        Map mapp = new TmxMapLoader().load("core/assets/theMap.tmx");
//        world = new GameWorld(mapp);

        //Map maps = new TmxMapLoader().load("core/assets/theMap.tmx");


        //Wraps the TileMap for easier access
        //worlds = new WorldMap(map);

        //maps = new MapStorage();
        //maps.AddMap("Main", mapp);

        //playerCtrl = new PlayerCtrl(player, world);

        //Shouldn't be here, it's just here temporarily.
        //Gdx.input.setInputProcessor(new GameCtrl(playerCtrl));

        this.setScreen(new GameScreen(this));
    }

    @Override
    public void render() {

        super.render();
        /*// setup rendering logic for controllers
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        Position playerPos = player.getPos();
        float playerX = playerPos.getX() * TILE_SIZE;
        float playerY = playerPos.getY() * TILE_SIZE;

        batch.begin();
        sprite.setPosition(playerX, playerY);
        sprite.draw(batch);
        batch.end();*/
    }

    @Override
    public void dispose() {
        //img.dispose();
    }


}
