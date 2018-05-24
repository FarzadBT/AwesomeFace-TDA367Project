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
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.squareup.otto.Subscribe;
import faces.awesome.GDXWrapper;
import faces.awesome.AwesomeGame;
import faces.awesome.controllers.*;
import faces.awesome.events.MapChangedEvent;
import faces.awesome.model.BossEnemy;

import faces.awesome.model.Enemy;
import faces.awesome.model.MapSegment;
import faces.awesome.model.PlayerCharacter;
import faces.awesome.model.Position;
import faces.awesome.controllers.ScreenSwitcher.ScreenType;

import faces.awesome.services.WorldMap;
import faces.awesome.model.*;
import faces.awesome.model.item.Item;


import java.util.HashMap;


public class GameScreen implements Screen, ScreenSwitchListener {

    private final GDXWrapper game;

    private GameScreenCtrl gameController;
    private PlayerCtrl playerCtrl;


    private OrthographicCamera camera;
    private Viewport gamePort;

    private WorldMap world;
    private MapRenderer mapRenderer;
    private ShapeRenderer shapeRenderer;

    private List<GameObjectView > gameObjectViews;

    private SpriteBatch sprBatch;

    private Sprite playerSprite;
    private Sprite enemySprite;
    private Sprite bossSprite;

    private Sprite slot1Sprite;
    private Sprite slot2Sprite;

    private BitmapFont HPfont;
    private String HP;
    private HashMap<String, Texture> textures = new HashMap<>();

    public GameScreen(final GDXWrapper game, WorldMap world) {

        this.game = game;
        camera = new OrthographicCamera(GDXWrapper.VIEW_PORT_WIDTH, GDXWrapper.VIEW_PORT_HEIGHT);
        camera.setToOrtho(false);
        gamePort = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        gamePort.apply();

        this.world = world;
        game.bus.register(this);
        refetchMap();


        sprBatch = new SpriteBatch();

        //textures.put("playerCharacter", new Texture("core/assets/linkk.png"));
        playerSprite = new Sprite(game.assets.getTexture("player"));

        //enemies
        //textures.put("enemy", new Texture("core/assets/enemy.png"));
        enemySprite = new Sprite(game.assets.getTexture("enemy"));

        //textures.put("bossEnemy", new Texture("core/assets/giantenemycrab2.png"));
        bossSprite = new Sprite(game.assets.getTexture("bossEnemy"));


        //textures.put("slot1", new Texture("core/assets/blank.png"));
        slot1Sprite = new Sprite(game.assets.getTexture("blank"));
        //textures.put("slot2", new Texture("core/assets/blank.png"));
        slot2Sprite = new Sprite(game.assets.getTexture("blank"));

        //textures.put("Sword", new Texture("core/assets/sword.png"));
        //textures.put("Hammer", new Texture("core/assets/sword.png"));


        shapeRenderer = new ShapeRenderer();

        gameController = new GameScreenCtrl(playerCtrl);

        HPfont = new BitmapFont();
        HPfont.getData().setScale(2.0f);


        Gdx.input.setInputProcessor(gameController);
    }

    @Override
    public void show() {

    }

    // render-logic here
    public void update(float delta) {

        MapSegment.getEnemiesInSegment().forEach(enemy -> game.enemyCtrl.tryMove(enemy));

        MapSegment.getEnemiesInSegment().forEach(enemy -> game.enemyCtrl.shouldAttack(enemy, game.player));

        Vector3 cameraPos = camera.position.cpy();

        int newX = (game.segment.getMapPosition().getX() * 32 + 16) * GDXWrapper.TILE_SIZE;
        int newY = (game.segment.getMapPosition().getY() * 16 + 8) * GDXWrapper.TILE_SIZE;

        Vector3 vec3 = new Vector3(newX, newY, 0);

        camera.position.set(cameraPos.lerp(vec3, 0.1f));
        /*
        camera.position.x = (game.segment.getMapPosition().getX() * 32 + 16) * GDXWrapper.TILE_SIZE;
        camera.position.y = (game.segment.getMapPosition().getY() * 16 + 8) * GDXWrapper.TILE_SIZE;
*/
        HP = "HP:" + game.player.getHealth();


        // changed this, make sure it works.
        slot1Sprite.setRegion(game.assets.getTexture(game.segment.player.getSlot1().getName()));
        slot2Sprite.setRegion(game.assets.getTexture(game.segment.player.getSlot2().getName()));


        //shapeRenderer.setProjectionMatrix(camera.combined);

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

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.rect(24, 10, 25, 40);
        shapeRenderer.rect(50, 10, 25, 40);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.rect(24,10,25,40);
        shapeRenderer.rect(50, 10, 25, 40);
        shapeRenderer.end();


        sprBatch.begin();

        playerSprite.setPosition((game.player.getPos().getX() % 32) * GDXWrapper.TILE_SIZE,(game.player.getPos().getY() % 16) * GDXWrapper.TILE_SIZE);
        playerSprite.draw(sprBatch);


        //bossSprite.setPlayerPosOnMap((game.segment.boss.getPos().getX() % 32) * TILE_SIZE,(game.segment.boss.getPos().getY() % 16) * TILE_SIZE);
        //bossSprite.draw(sprBatch);
        //bossSprite.setScale(2.0f);


        MapSegment.getEnemiesInSegment().forEach(enemy -> {

            if (enemy instanceof BossEnemy) {
                bossSprite.setPosition((enemy.getPos().getX() % 32) * GDXWrapper.TILE_SIZE,(enemy.getPos().getY() % 16) * GDXWrapper.TILE_SIZE);
                bossSprite.draw(sprBatch);

            } else {
                enemySprite.setPosition((enemy.getPos().getX() % 32) * GDXWrapper.TILE_SIZE, (enemy.getPos().getY() % 16) * GDXWrapper.TILE_SIZE);
                enemySprite.draw(sprBatch);
            }
        });

        slot1Sprite.setPosition(27, 20);
        slot1Sprite.setScale(2.0f);
        slot1Sprite.draw(sprBatch);

        slot2Sprite.setPosition(53, 20);
        slot2Sprite.setScale(2.0f);
        slot2Sprite.draw(sprBatch);

        HPfont.setColor(1.0f, 1.0f, 1.0f, 10.f);
        HPfont.draw(sprBatch, HP, 25,500);

        sprBatch.end();

    }

    private void refetchMap () {

        mapRenderer = new OrthogonalTiledMapRenderer(world.getCurrentMap());

    }

    public void initialize() {
        playerCtrl = new PlayerCtrl(game.player, game.world, game.segment);
        gameController = new GameScreenCtrl(playerCtrl);
        Gdx.input.setInputProcessor(gameController);
        ScreenSwitcher.setListener(this);
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


    @Subscribe
    public void mapchangedEvent(MapChangedEvent event) {
        refetchMap();
    }

    @Override
    public void onScreenChange(ScreenType screen) {
        switch (screen) {
            case MainScreen:
                ScreenRepository.setMainMenuScreen(game);
                break;
            case GameOverScreen:
                ScreenRepository.setGameOverScreen(game);
                break;
            default:
                break;
            /* This is where we can go from another screen, none other yet defined. */
        }
    }
}
