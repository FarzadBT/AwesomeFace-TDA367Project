package faces.awesome.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import faces.awesome.AwesomeGame;
import faces.awesome.controllers.GameCtrl;
import faces.awesome.controllers.PlayerCtrl;
import faces.awesome.controllers.ScreenSwitchListener;
import faces.awesome.controllers.ScreenSwitcher;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;
import faces.awesome.controllers.ScreenSwitcher.ScreenType;

import faces.awesome.model.WorldMap;

import static faces.awesome.AwesomeGame.TILE_SIZE;

public class GameScreen implements Screen, ScreenSwitchListener {

    private final AwesomeGame game;

    private GameCtrl gameController;
    private PlayerCtrl playerCtrl;

    //tmp fields

    private AnimationDefinition animDefs;

    //tmp fields

    private OrthographicCamera camera;
    private Viewport gamePort;

    private WorldMap world;
    private MapRenderer mapRenderer;

    private CharacterView character;

    private SpriteBatch sprBatch;

    public GameScreen(final AwesomeGame game, WorldMap world) {
        this.game = game;

        camera = new OrthographicCamera(AwesomeGame.VIEW_PORT_WIDTH, AwesomeGame.VIEW_PORT_HEIGHT);
        camera.setToOrtho(false);
        gamePort = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        gamePort.apply();

        this.world = world;
        refetchMap();

        //tmp
        animDefs = new AnimationDefinition("maskman", 64, 64, 8);

        sprBatch = new SpriteBatch();

        // tmp
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();

        PlayerCharacter playerChar = new PlayerCharacter(new Position(w / TILE_SIZE / 2, h / TILE_SIZE / 2));

        character = new CharacterView(playerChar);

        playerCtrl = new PlayerCtrl(playerChar, game.world);

        gameController = new GameCtrl(playerCtrl, camera);

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
        // test stuff

        Vector3 camCopy = camera.position.cpy();
        Vector3 target = new Vector3(new Vector2( 1504f, 256f), 0);
        camera.position.set(camCopy.lerp(camCopy.lerp(target.cpy(), 0.1f), 0));

        // test stuff

        //System.out.println("y: "+character.getCharacter().getPos().getY() * TILE_SIZE);
        //System.out.println("y: "+game.player.getPos().getY() * AwesomeGame.TILE_SIZE);

        update(delta);
        // RGB(0, 0, 0, 1) = black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.render();

        sprBatch.begin();
        sprBatch.setProjectionMatrix(camera.combined);
        //character.draw(sprBatch);
        //sprBatch.draw(animDefs.getCharacterStandingUp(), character.getCharacter().getPos().getX() * TILE_SIZE, character.getCharacter().getPos().getY() * TILE_SIZE);
        //spr.setPosition(game.player.getPos().getX() * AwesomeGame.TILE_SIZE, game.player.getPos().getY() * AwesomeGame.TILE_SIZE);
        //spr.draw(sprBatch);
        //spr.setPosition((game.player.getPos().getX() % 32) * TILE_SIZE, (game.player.getPos().getY() % 16) * TILE_SIZE);
        //spr.draw(sprBatch);
        sprBatch.end();
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
    }

    @Override
    public void onScreenChange(ScreenType screen) {
        switch (screen) {
            default:
                break;
            /* This is where we can go from another screen, none other yet defined. */
        }
    }
}
