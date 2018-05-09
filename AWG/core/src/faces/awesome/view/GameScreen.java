package faces.awesome.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import faces.awesome.AwesomeGame;
import faces.awesome.controllers.GameCtrl;
import faces.awesome.model.WorldMap;

import static faces.awesome.AwesomeGame.TILE_SIZE;

public class GameScreen implements Screen {

    private final AwesomeGame game;
    private GameCtrl gameController;

    private OrthographicCamera camera;
    private Viewport gamePort;

    private WorldMap world;
    private MapRenderer mapRenderer;

    private SpriteBatch sprBatch;
    private Sprite spr;
    private Texture texture;

    //tmp
    private SpriteBatch sprBat;
    private Sprite sprite;
    private Texture text;



    public GameScreen(final AwesomeGame game, WorldMap world) {
        this.game = game;

        camera = new OrthographicCamera(AwesomeGame.VIEW_PORT_WIDTH, AwesomeGame.VIEW_PORT_HEIGHT);
        camera.setToOrtho(false);
        gamePort = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        gamePort.apply();

        this.world = world;
        refetchMap();

        //tmp
        sprBatch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("core/assets/linkk.png"));
        spr = new Sprite(texture);

        //enemie
        sprBat = new SpriteBatch();
        text = new Texture(Gdx.files.internal("core/assets/linkk.png"));
        sprite = new Sprite(text);



        gameController = new GameCtrl(game.playerCtrl, camera);
        Gdx.input.setInputProcessor(gameController);

    }


    @Override
    public void show() {

    }

    // render-logic here
    public void update(float delta) {
        refetchMap();
        camera.position.x = ((world.getMapPosition().getX() * 32) + 16) * TILE_SIZE;
        camera.position.y = ((world.getMapPosition().getY() * 16) + 8) * TILE_SIZE;

        camera.update();
        mapRenderer.setView(camera);
    }

    @Override
    public void render(float delta) {

        game.enemy.move();
        //System.out.println("x: " + game.playerCharacter.getPos().getX() + " y: " + game.playerCharacter.getPos().getY());
        update(delta);
        // RGB(0, 0, 0, 1) = black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.render();

        sprBatch.begin();
        spr.setPosition((game.player.getPos().getX() % 32) * TILE_SIZE,(game.player.getPos().getY() % 16) * TILE_SIZE);
        spr.draw(sprBatch);
        sprBatch.end();

        sprBat.begin();
        sprite.setPosition((game.enemy.getPos().getX() % 32) * TILE_SIZE,(game.enemy.getPos().getY() % 32) * TILE_SIZE);
        sprite.draw(sprBat);
        sprBat.end();

    }

    public void refetchMap () {

        mapRenderer = new OrthogonalTiledMapRenderer(world.getCurrent());

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        sprBatch.dispose();
        texture.dispose();
    }
}
