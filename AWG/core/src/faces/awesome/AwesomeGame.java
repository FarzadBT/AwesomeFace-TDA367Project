package faces.awesome;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import faces.awesome.controllers.PlayerCtrl;
import faces.awesome.model.*;
import faces.awesome.model.Character;
import faces.awesome.model.item.items.permanents.Hammer;
import faces.awesome.model.item.items.permanents.Sword;
import faces.awesome.view.GameScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class AwesomeGame extends Game implements Observer {
    public static final int TILE_SIZE = 32;
    public static final int VIEW_PORT_WIDTH = 1024;
    public static final int VIEW_PORT_HEIGHT = 512;


    // TO-do: instead of having a a HasA depndency, let's just use dependency inject playerCharacter where we need it.
    public AssetManager assets;

    public PlayerCharacter player;

    public BossEnemy boss;

    public PlayerCtrl playerCtrl;

    public MapSegment segment;

    public List<Enemy> enemiesInWorld = new ArrayList<>();


    public WorldMap world;

    public int health;
    public String HP;
    public BitmapFont HPfont;

    @Override
    public void create() {
        // setup model here.

        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();
        player = CharacterFactory.createPlayer(w/TILE_SIZE/2, h/TILE_SIZE/2);

        //player = new PlayerCharacter(new Position(w / TILE_SIZE / 2, h / TILE_SIZE / 2));
        player.addNewToInventory(ItemFactory.CreateSword());
        player.addNewToInventory(ItemFactory.CreateHammer());
        player.setSlot1(player.getInventory().getItem("Sword"));
        player.setSlot2(player.getInventory().getItem("Hammer"));

        boss = new BossEnemy(new Position(8, 10), this);

        Map map = new TmxMapLoader().load("core/assets/maps/theMap.tmx");

        //Wraps the TileMap for easier access
        world = new WorldMap((TiledMap) map);

        world.addObserver(this);

        //Stores maps for later use
        MapStorage.AddMap("mainMap", (TiledMap) map);
        MapStorage.AddMap("smallHouse", new TmxMapLoader().load("core/assets/maps/smallHouse.tmx"));
        MapStorage.AddMap("mediumHouse", new TmxMapLoader().load("core/assets/maps/mediumHouse.tmx"));
        MapStorage.AddMap("bigHouse", new TmxMapLoader().load("core/assets/maps/bigHouse.tmx"));
        MapStorage.AddMap("cathedral", new TmxMapLoader().load("core/assets/maps/cathedral.tmx"));

        //Instantiate asset manager
        assets = new AssetManager();

        //Creates textures from available files in core/assets/
        assets.addTexture("enemy", new Texture("core/assets/enemy.png"));
        assets.addTexture("Sword", new Texture("core/assets/sword.png"));
        assets.addTexture("Hammer", new Texture("core/assets/sword.png"));
        assets.addTexture("player", new Texture("core/assets/linkk.png"));
        assets.addTexture("bossEnemy", new Texture("core/assets/giantenemycrab.png"));
        assets.addTexture("blank", new Texture("core/assets/blank.png"));

        segment = new MapSegment(world, enemiesInWorld, player, boss);

        update(null, null);

        playerCtrl = new PlayerCtrl(player, world, segment);

        System.out.println(enemiesInWorld);


        health = player.getMaxHealth();
        HPfont = new BitmapFont();
        HPfont.getData().setScale(2.0f);

        this.setScreen(new GameScreen(this, world));
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


    @Override
    public void update(Observable observable, Object o) {

        enemiesInWorld.clear();

        enemiesInWorld.addAll(Tiles.populateWorldWithEnemies(world.getCurrent(), this));

        segment.setEnemiesInWorld(enemiesInWorld);

    }
}
