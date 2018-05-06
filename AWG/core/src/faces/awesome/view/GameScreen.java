package faces.awesome.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import faces.awesome.AwesomeGame;
import faces.awesome.controllers.GameCtrl;
import faces.awesome.controllers.PlayerCtrl;
import faces.awesome.model.GameWorld;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;

import static faces.awesome.AwesomeGame.TILE_SIZE;

public class GameScreen implements Screen {

    private final AwesomeGame game;



    private GameCtrl gameController;
    private PlayerCtrl playerCtrl;

    //tmp fields

    private AnimationDefinition animDefs;

    //tmp fields

    private OrthographicCamera camera;
    private Viewport gamePort;

    private TiledMap map;
    private TiledMapRenderer mapRenderer;

    private UICharacter character;

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
        animDefs = new AnimationDefinition();

        sprBatch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("core/assets/linkk.png"));
        spr = new Sprite(texture);

        // tmp
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();

        character = new UICharacter(new PlayerCharacter(new Position(w / TILE_SIZE / 2, h / TILE_SIZE / 2)));

        playerCtrl = new PlayerCtrl( (PlayerCharacter) character.getCharacter(), game.world);

        gameController = new GameCtrl(playerCtrl, camera);

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
        System.out.println("x: "+character.getCharacter().getPos().getX() * TILE_SIZE);
        //System.out.println("y: "+game.player.getPos().getY() * AwesomeGame.TILE_SIZE);

        update(delta);
        // RGB(0, 0, 0, 1) = black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.render();

        sprBatch.begin();
        sprBatch.draw(animDefs.getCharacterStandingUp(), character.getCharacter().getPos().getX() * TILE_SIZE, character.getCharacter().getPos().getY() * TILE_SIZE);
        //spr.setPosition(game.player.getPos().getX() * AwesomeGame.TILE_SIZE, game.player.getPos().getY() * AwesomeGame.TILE_SIZE);
        //spr.draw(sprBatch);
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
