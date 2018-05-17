package faces.awesome.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import faces.awesome.AwesomeGame;
import faces.awesome.controllers.GameCtrl;
import faces.awesome.model.BossEnemy;
import faces.awesome.model.Enemy;
import faces.awesome.model.MapSegment;
import faces.awesome.controllers.PlayerCtrl;
import faces.awesome.controllers.ScreenSwitchListener;
import faces.awesome.controllers.ScreenSwitcher;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;
import faces.awesome.controllers.ScreenSwitcher.ScreenType;

import faces.awesome.model.WorldMap;

import java.util.Observable;
import java.util.Observer;

import static faces.awesome.AwesomeGame.TILE_SIZE;

public class GameScreen implements Screen, Observer, ScreenSwitchListener {

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
    private ShapeRenderer shapeRenderer;

    private CharacterView character;

    private SpriteBatch sprBatch;

    private Sprite playerSprite;
    private Sprite enemySprite;
    private Sprite bossSprite;


    public GameScreen(final AwesomeGame game, WorldMap world, MapSegment segment) {
        this.game = game;

        camera = new OrthographicCamera(AwesomeGame.VIEW_PORT_WIDTH, AwesomeGame.VIEW_PORT_HEIGHT);
        camera.setToOrtho(false);
        gamePort = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        gamePort.apply();

        this.world = world;
        this.world.addObserver(this);
        refetchMap();

        //tmp
        animDefs = new AnimationDefinition("maskman", 64, 64, 8);

        sprBatch = new SpriteBatch();

        Texture texture = new Texture(Gdx.files.internal("core/assets/linkk.png"));
        playerSprite = new Sprite(texture);

        //enemie
        Texture enemyTexture = new Texture(Gdx.files.internal("core/assets/enemy.png"));
        enemySprite = new Sprite(enemyTexture);

        Texture bossTexture = new Texture(Gdx.files.internal("core/assets/giantenemycrab2.png"));
        bossSprite = new Sprite(bossTexture);


        shapeRenderer = new ShapeRenderer();

        // tmp
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();

        PlayerCharacter playerChar = new PlayerCharacter(new Position(w / TILE_SIZE / 2, h / TILE_SIZE / 2));

        character = new CharacterView(playerChar);

        playerCtrl = new PlayerCtrl(playerChar, game.world, segment);

        gameController = new GameCtrl(playerCtrl, camera);

        Gdx.input.setInputProcessor(gameController);
    }

    @Override
    public void show() {

    }

    // render-logic here
    public void update(float delta) {

        MapSegment.getEnemiesInSegment().forEach(Enemy::move);
        MapSegment.getEnemiesInSegment().forEach(enemy -> enemy.attack(game.player));

        game.segment.boss.move();
        game.segment.boss.attack(game.player);


        //System.out.println(game.player.getHealth());

        camera.position.x = ((world.getMapPosition().getX() * 32) + 16) * TILE_SIZE;
        camera.position.y = ((world.getMapPosition().getY() * 16) + 8) * TILE_SIZE;

        game.HP = "HP:" + game.player.getHealth();


        //shapeRenderer.setProjectionMatrix(camera.combined);

        camera.update();

        mapRenderer.setView(camera);


    }

    @Override
    public void render(float delta) {
/*
        Vector3 camCopy = camera.position.cpy();
        Vector3 target = new Vector3(new Vector2( 1504f, 256f), 0);
        camera.position.set(camCopy.lerp(camCopy.lerp(target.cpy(), 0.1f), 0));
*/

        update(delta);
        // RGB(0, 0, 0, 1) = black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.render();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 0, 1, 0);
        shapeRenderer.rect(4, 10, 20, 50);
        shapeRenderer.rect(24, 10, 20, 50);
        shapeRenderer.end();

        sprBatch.begin();

        playerSprite.setPosition((game.player.getPos().getX() % 32) * TILE_SIZE,(game.player.getPos().getY() % 16) * TILE_SIZE);
        playerSprite.draw(sprBatch);


        //bossSprite.setPosition((game.segment.boss.getPos().getX() % 32) * TILE_SIZE,(game.segment.boss.getPos().getY() % 16) * TILE_SIZE);
        //bossSprite.draw(sprBatch);
        //bossSprite.setScale(2.0f);

        //TODO när man går in i nya kartor dyker fienderna upp igen, de fattar inte att det är en ny karta

        MapSegment.getEnemiesInSegment().forEach(enemy -> {
            enemySprite.setPosition((enemy.getPos().getX() % 32) * TILE_SIZE,(enemy.getPos().getY() % 16) * TILE_SIZE);
            enemySprite.draw(sprBatch);
        });



        game.HPfont.setColor(1.0f, 1.0f, 1.0f, 10.f);
        game.HPfont.draw(sprBatch, game.HP, 25,500);

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
    public void update(Observable observable, Object o) {

        refetchMap();
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
