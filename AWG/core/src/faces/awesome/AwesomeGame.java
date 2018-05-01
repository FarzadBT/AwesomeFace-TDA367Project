package faces.awesome;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import faces.awesome.controllers.GameCtrl;
import faces.awesome.controllers.PlayerCtrl;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;
import faces.awesome.model.WorldMap;
import faces.awesome.view.GameScreen;

public class AwesomeGame extends Game {
    public static final int TILE_SIZE = 32;
    public static final int VIEW_PORT_WIDTH = 1024;
    public static final int VIEW_PORT_HEIGHT = 512;

    //public TiledMap map;
    // TO-do: instead of having a a HasA depndency, let's just use dependency inject player where we need it.

    public PlayerCharacter player;

    public PlayerCtrl playerCtrl;

    WorldMap world;



    @Override
    public void create() {
        // setup model here.

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        player = new PlayerCharacter(new Position(w / TILE_SIZE / 2, h / TILE_SIZE / 2));

        TiledMap map = new TmxMapLoader().load("core/assets/theMap.tmx");


        //Wraps the TileMap for easier access
        world = new WorldMap(map);

        playerCtrl = new PlayerCtrl(player, world);

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
