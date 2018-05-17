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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import faces.awesome.AwesomeGame;
import faces.awesome.controllers.GameCtrl;
import faces.awesome.model.BossEnemy;
import faces.awesome.model.Enemy;
import faces.awesome.model.MapSegment;
import faces.awesome.model.WorldMap;
import faces.awesome.model.item.Item;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import static faces.awesome.AwesomeGame.TILE_SIZE;

public class GameScreen implements Screen, Observer {

    private final AwesomeGame game;
    private GameCtrl gameController;

    private OrthographicCamera camera;
    private Viewport gamePort;

    private WorldMap world;
    private MapRenderer mapRenderer;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch sprBatch;

    private Sprite playerSprite;
    private Sprite enemySprite;
    private Sprite bossSprite;

    private Sprite slot1Sprite;
    private Texture slot1Texture;
    private Sprite slot2Sprite;
    private Texture slot2Texture;



    private HashMap<String, Texture> ItemTextures = new HashMap<>();

    public GameScreen(final AwesomeGame game, WorldMap world) {
        this.game = game;

        camera = new OrthographicCamera(AwesomeGame.VIEW_PORT_WIDTH, AwesomeGame.VIEW_PORT_HEIGHT);
        camera.setToOrtho(false);
        gamePort = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        gamePort.apply();

        this.world = world;
        this.world.addObserver(this);
        refetchMap();

        //tmp
        sprBatch = new SpriteBatch();

        Texture texture = new Texture(Gdx.files.internal("core/assets/linkk.png"));
        playerSprite = new Sprite(texture);

        //enemie
        Texture enemyTexture = new Texture(Gdx.files.internal("core/assets/enemy.png"));
        enemySprite = new Sprite(enemyTexture);

        Texture bossTexture = new Texture(Gdx.files.internal("core/assets/giantenemycrab2.png"));
        bossSprite = new Sprite(bossTexture);

        slot1Texture = new Texture(Gdx.files.internal("core/assets/blank.png"));
        slot1Sprite = new Sprite(slot1Texture);
        slot2Texture = new Texture(Gdx.files.internal("core/assets/blank.png"));
        slot2Sprite = new Sprite(slot2Texture);

        ItemTextures.put("Sword", new Texture("core/assets/sword.png"));
        ItemTextures.put("Hammer", new Texture("core/assets/sword.png"));


        shapeRenderer = new ShapeRenderer();

        gameController = new GameCtrl(game.playerCtrl, camera);
        Gdx.input.setInputProcessor(gameController);

    }


    @Override
    public void show() {

    }

    // render-logic here
    public void update(float delta) {

        game.segment.getEnemiesInSegment().forEach(Enemy::move);
        game.segment.getEnemiesInSegment().forEach(enemy -> enemy.attack(game.player));

        game.segment.boss.move();
        game.segment.boss.attack(game.player);

        //System.out.println(game.player.getHealth());

        camera.position.x = ((world.getMapPosition().getX() * 32) + 16) * TILE_SIZE;
        camera.position.y = ((world.getMapPosition().getY() * 16) + 8) * TILE_SIZE;

        game.HP = "HP:" + game.player.getHealth();

        slot1Sprite.setTexture(ItemTextures.get(game.segment.player.getSlot1().getName()));
        slot2Sprite.setTexture((ItemTextures.get(game.segment.player.getSlot2().getName())));


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

        sprBatch.begin();

        playerSprite.setPosition((game.player.getPos().getX() % 32) * TILE_SIZE,(game.player.getPos().getY() % 16) * TILE_SIZE);
        playerSprite.draw(sprBatch);


        //bossSprite.setPosition((game.segment.boss.getPos().getX() % 32) * TILE_SIZE,(game.segment.boss.getPos().getY() % 16) * TILE_SIZE);
        //bossSprite.draw(sprBatch);
        //bossSprite.setScale(2.0f);

        //TODO när man går in i nya kartor dyker fienderna upp igen, de fattar inte att det är en ny karta

        game.segment.getEnemiesInSegment().forEach(enemy -> {
            enemySprite.setPosition((enemy.getPos().getX() % 32) * TILE_SIZE,(enemy.getPos().getY() % 16) * TILE_SIZE);
            enemySprite.draw(sprBatch);
        });

        slot1Sprite.setPosition(26, 20);
        slot1Sprite.setScale(2.0f);
        slot1Sprite.draw(sprBatch);

        slot2Sprite.setPosition(51, 20);
        slot2Sprite.setScale(2.0f);
        slot2Sprite.draw(sprBatch);

        game.HPfont.setColor(1.0f, 1.0f, 1.0f, 10.f);
        game.HPfont.draw(sprBatch, game.HP, 25,500);

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
}
