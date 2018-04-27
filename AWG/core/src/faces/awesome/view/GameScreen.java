package faces.awesome.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import faces.awesome.AwesomeGame;
import faces.awesome.controllers.GameCtrl;

public class GameScreen implements Screen {

    private final AwesomeGame game;
    private GameCtrl gameController;

    private OrthographicCamera camera;
    private Viewport gamePort;

    private TiledMap map;
    private TiledMapRenderer mapRenderer;

    private SpriteBatch sprBatch;
    private Sprite spr;
    private Texture texture;

    public GameScreen(final AwesomeGame game) {
        this.game = game;

        camera = new OrthographicCamera(AwesomeGame.VIEW_PORT_WIDTH, AwesomeGame.VIEW_PORT_HEIGHT);
        camera.setToOrtho(false);
        gamePort = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        gamePort.apply();

        map = new TmxMapLoader().load("core/assets/theMap.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        //tmp
        sprBatch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("core/assets/linkk.png"));
        spr = new Sprite(texture);

        gameController = new GameCtrl(game.playerCtrl, camera);
        Gdx.input.setInputProcessor(gameController);

    }


    @Override
    public void show() {

    }

    // render-logic here
    public void update(float delta) {
        camera.update();
        mapRenderer.setView(camera);
    }

    @Override
    public void render(float delta) {
        //System.out.println("x: " + game.playerCharacter.getPos().getX() + " y: " + game.playerCharacter.getPos().getY());
        update(delta);
        // RGB(0, 0, 0, 1) = black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.render();

        sprBatch.begin();
        spr.setPosition(game.playerCharacter.getPos().getX() * AwesomeGame.TILE_SIZE, game.playerCharacter.getPos().getY() * AwesomeGame.TILE_SIZE);
        spr.draw(sprBatch);
        sprBatch.end();
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
