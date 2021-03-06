package faces.awesome.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.squareup.otto.Subscribe;
import faces.awesome.GDXWrapper;
import faces.awesome.controllers.GameScreenCtrl;
import faces.awesome.controllers.PlayerCtrl;
import faces.awesome.controllers.ScreenSwitchListener;
import faces.awesome.controllers.ScreenSwitcher;
import faces.awesome.controllers.ScreenSwitcher.ScreenType;
import faces.awesome.events.BossEnemyDiedEvent;
import faces.awesome.events.MapChangedEvent;
import faces.awesome.events.PlayerCharacterDiedEvent;

import faces.awesome.model.objects.object.BombObject;

import java.util.ArrayList;
import java.util.List;
import faces.awesome.model.GameObject;
import faces.awesome.model.characters.BossEnemy;
import faces.awesome.model.objects.pickup.SmallBomb;
import faces.awesome.model.objects.pickup.SmallHeart;


/*
* @author Linus Wallman
* Updated by: Therese Sturesson, Philip Nilsson, Farzad Besharati
*
* This is the main rendering class. It handles the actual rendering of game objects in the world.
*/

public class GameScreen implements Screen, ScreenSwitchListener {

    private final GDXWrapper game;

    private GameScreenCtrl gameController;
    private PlayerCtrl playerController;

    private OrthographicCamera camera;
    private Viewport gamePort;

    private MapRenderer mapRenderer;
    private ShapeRenderer shapeRenderer;


    private List<GameObjectView> gameObjectViews;

    private SpriteBatch sprBatch;

    private Sprite playerSprite;
    private Sprite enemySprite;
    private Sprite bossSprite;

    private Sprite slot1Sprite;
    private Sprite slot2Sprite;

    private Sprite bombObjectSprite;

    private Sprite smallBombPickupSprite;
    private Sprite smallHeartPickupSprite;

    private BitmapFont HPfont;
    private String HP;

    public GameScreen(final GDXWrapper g) {

        this.game = g;
        camera = new OrthographicCamera(GDXWrapper.VIEW_PORT_WIDTH, GDXWrapper.VIEW_PORT_HEIGHT);
        camera.setToOrtho(false);
        gamePort = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        gamePort.apply();

        game.bus.register(this);
        refetchMap();

        sprBatch = new SpriteBatch();

        //Sprite for the player
        playerSprite = new Sprite(game.assets.getTexture("player"));

        //Sprite for the enemy
        enemySprite = new Sprite(game.assets.getTexture("enemy"));

        //Sprite for the boss
        bossSprite = new Sprite(game.assets.getTexture("bossEnemy"));

        //Sprite for some misc. GameObjects
        bombObjectSprite = new Sprite(game.assets.getTexture("Bomb"));

        //Sprite for some misc. Pickups
        smallBombPickupSprite = new Sprite(game.assets.getTexture("smallBomb"));
        smallHeartPickupSprite = new Sprite(game.assets.getTexture("smallHeart"));

        //Sprite for the item slot
        slot1Sprite = new Sprite(game.assets.getTexture("blank"));
        slot2Sprite = new Sprite(game.assets.getTexture("blank"));

        shapeRenderer = new ShapeRenderer();

        gameController = new GameScreenCtrl(game.playerCtrl);

        HPfont = new BitmapFont();
        HPfont.getData().setScale(2.0f);

        gameObjectViews = new ArrayList<>();

    }

    @Override
    public void show() {

    }

    // render-logic here
    public void update(float delta) {

        game.segment.getEnemiesInSegment().forEach(enemy -> game.enemyCtrl.checkDeath(enemy));

        game.segment.getEnemiesInSegment().forEach(enemy -> game.enemyCtrl.tryMove(enemy));

        game.segment.getEnemiesInSegment().forEach(enemy -> game.enemyCtrl.shouldAttack(enemy, game.player));

        Vector3 cameraPos = camera.position.cpy();

        int newX = (game.segment.getMapPosition().getX() * 32 + 16) * GDXWrapper.TILE_SIZE;
        int newY = (game.segment.getMapPosition().getY() * 16 + 8) * GDXWrapper.TILE_SIZE;

        Vector3 vec3 = new Vector3(newX, newY, 0);

        camera.position.set(cameraPos.lerp(vec3, 0.1f));

        HP = "HP:" + game.player.getHealth();

        slot1Sprite.setRegion(game.assets.getTexture(game.player.getSlot1().getName()));
        slot2Sprite.setRegion(game.assets.getTexture(game.player.getSlot2().getName()));

        camera.update();

        mapRenderer.setView(camera);


    }

    @Override
    public void render(float delta) {
        CharacterView c = (CharacterView) gameObjectViews.get(0);

        update(delta);

        // RGB(0, 0, 0, 1) = black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.render();

        sprBatch.begin();

        gameObjectViews.forEach(g -> g.draw(sprBatch));

        game.segment.getObjectsInSegment().forEach(gameObject -> {
            if (gameObject instanceof BombObject) {

                renderGameObject(gameObject, bombObjectSprite);

            }
        });


        game.segment.getEnemiesInSegment().forEach(enemy -> {

            if (enemy instanceof BossEnemy) {

                renderGameObject(enemy, bossSprite);

            } else {

                renderGameObject(enemy, enemySprite);
            }
        });


        game.segment.getPickupsInSegment().forEach(pickup -> {
            if (pickup instanceof SmallBomb) {

                renderGameObject(pickup, smallBombPickupSprite);

            } else if (pickup instanceof SmallHeart) {

                renderGameObject(pickup, smallHeartPickupSprite);

            }
        });

        sprBatch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.rect(25, 10, 50, 50);
        shapeRenderer.rect(75, 10, 50, 50);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.rect(25,10,50,50);
        shapeRenderer.rect(75, 10, 50, 50);
        shapeRenderer.end();

        sprBatch.begin();

        slot1Sprite.setPosition(40, 25);
        slot1Sprite.setScale(2.0f);
        slot1Sprite.draw(sprBatch);

        slot2Sprite.setPosition(92, 25);
        slot2Sprite.setScale(2.0f);
        slot2Sprite.draw(sprBatch);

        HPfont.setColor(1.0f, 1.0f, 1.0f, 10.f);
        HPfont.draw(sprBatch, HP, 25,500);


        sprBatch.end();

    }

    private void renderGameObject(GameObject object, Sprite sprite) {

        sprite.setPosition((object.getPos().getX() % 32) * GDXWrapper.TILE_SIZE, (object.getPos().getY() % 16) * GDXWrapper.TILE_SIZE);
        sprite.draw(sprBatch);

    }

    private void refetchMap () {

        mapRenderer = new OrthogonalTiledMapRenderer(game.world.getCurrentMap());

    }

    public void initialize() {
        playerController = new PlayerCtrl(game.player, game.getMap(), game.segment);
        gameController = new GameScreenCtrl(playerController);
        Gdx.input.setInputProcessor(gameController);
        ScreenSwitcher.setListener(this);

        if (gameObjectViews.size() > 0) {
            gameObjectViews.remove(0);
        }

        gameObjectViews.add(new CharacterView(game.player));
    }


    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }


    @Override
    public void dispose() {
        sprBatch.dispose();
    }


    //When the map changes the event will happen and refetch the map
    @Subscribe
    public void mapchangedEvent(MapChangedEvent event) {
        refetchMap();
    }


    //When the player dies this event will happen and changes the screen
    @Subscribe
    public void playerCharacterDievEvent(PlayerCharacterDiedEvent event){
        onScreenChange(ScreenType.GameOverScreen);
    }


    //When the boss enemy dies this event will happen and changes the screen
    @Subscribe
    public void bossEnemyDiedEvent(BossEnemyDiedEvent event) {
        onScreenChange(ScreenType.GameWonScreen);
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
            case GameWonScreen:
                ScreenRepository.setGameWonScreen(game);
                break;
            case CreditScreen:
                ScreenRepository.setCreditScreen(game);
                break;
            default:
                break;
        }
    }

    //Methods we must have but do not use
    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


}
