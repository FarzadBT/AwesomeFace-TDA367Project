package faces.awesome;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;
import faces.awesome.controllers.EnemyCtrl;
import faces.awesome.controllers.PlayerCtrl;
import faces.awesome.utils.AwesomeClock;
import faces.awesome.services.AssetManager;
import faces.awesome.view.ScreenRepository;
import faces.awesome.events.MapChangedEvent;
import faces.awesome.model.*;
import faces.awesome.model.item.items.consumables.Bomb;
import faces.awesome.model.item.items.permanents.Hammer;
import faces.awesome.model.item.items.permanents.Sword;
import faces.awesome.services.GdxWrapperService;
import faces.awesome.services.MapStorage;
import faces.awesome.services.Tiles;
import faces.awesome.services.WorldMap;

/**
 * @author Linus Wallman
 *         Updated by:
 *         TODO: Skriv vad klassen gör
 */


public class GDXWrapper extends Game {


    /**
     * Flytta på dessa konstanter?
     */

    public static final int TILE_SIZE = 32;
    public static final int VIEW_PORT_WIDTH = 1024;
    public static final int VIEW_PORT_HEIGHT = 512;

    private TiledMap map;


    public AssetManager assets;

    public PlayerCharacter player;
    public PlayerCtrl playerCtrl;

    public EnemyCtrl enemyCtrl;

    public MapSegment segment;
    public WorldMap world;
    public Bus bus;

    public static AwesomeClock AWG_TIME;
    private AwesomeGame AWG;

    @Override
    public void create() {
        // setup model here.

        bus = new Bus(ThreadEnforcer.ANY);
        bus.register(this);

        //Instantiate asset manager
        assets = AssetManager.getInstance();

        reInit();

        AWG = new AwesomeGame(new GdxWrapperService(this), segment, player);

        //Creates textures from available files in core/assets/
        assets.addTexture("enemy", new TextureRegion(new Texture("core/assets/enemy.png")));
        assets.addTexture("player", new TextureRegion(new Texture("core/assets/linkk.png")));
        assets.addTexture("bossEnemy", new TextureRegion(new Texture("core/assets/giantenemycrab2.png")));
        assets.addTexture("blank", new TextureRegion(new Texture("core/assets/blank.png")));

        //Permanent Items
        assets.addTexture("Sword", new TextureRegion(new Texture("core/assets/sword.png")));
        assets.addTexture("Hammer", new TextureRegion(new Texture("core/assets/sword.png")));

        //Consumable Items
        assets.addTexture("Bomb", new TextureRegion(new Texture("core/assets/bomb.png")));

        //Animations
        assets.addAnimation(player.getName() + "-anim-south", new Animation<>(0.025f, setupRunningFrames(new TextureRegion(new Texture("core/assets/anims/link-sprites1.png")), 0, 16, 16, 16)));
        assets.addAnimation(player.getName() + "-anim-west", new Animation<>(0.025f, setupRunningFrames(new TextureRegion(new Texture("core/assets/anims/link-sprites1.png")), 16, 16, 16, 16)));
        assets.addAnimation(player.getName() + "-anim-north", new Animation<>(0.025f, setupRunningFrames(new TextureRegion(new Texture("core/assets/anims/link-sprites1.png")), 72, 16, 16, 16)));
        assets.addAnimation(player.getName() + "-anim-east", new Animation<>(0.025f, setupRunningFrames(new TextureRegion(new Texture("core/assets/anims/link-sprites1.png")), 48, 16, 16, 16)));

        assets.addTexture(player.getName() + "-south", new TextureRegion(new Texture("core/assets/anims/link-sprites1.png"), 180, 0, 32, 32));
        assets.addTexture(player.getName() + "-north", new TextureRegion(new Texture("core/assets/anims/link-sprites1.png"), 72, 0, 32, 32));
        TextureRegion eastRegion = new TextureRegion(new Texture("core/assets/anims/link-sprites1.png"), 252, 0, 32, 32);
        assets.addTexture(player.getName() + "-east", eastRegion);
        TextureRegion westRegion = new TextureRegion(eastRegion);
        westRegion.flip(true, false);
        assets.addTexture(player.getName() + "-west", westRegion);

        //assets.addFileHandle("mainUi", Gdx.files.internal("core/assets/shade/skin/uiskin.json"));

        MapStorage.addMap("mainMap", map);
        MapStorage.addMap("smallHouse", new TmxMapLoader().load("core/assets/maps/smallHouse.tmx"));
        MapStorage.addMap("mediumHouse", new TmxMapLoader().load("core/assets/maps/mediumHouse.tmx"));
        MapStorage.addMap("bigHouse", new TmxMapLoader().load("core/assets/maps/bigHouse.tmx"));
        MapStorage.addMap("cathedral", new TmxMapLoader().load("core/assets/maps/cathedral.tmx"));

        handleMapChangedEvent(null);

        playerCtrl = new PlayerCtrl(player, world, segment);

        enemyCtrl = new EnemyCtrl(segment);

        ScreenRepository.setMainMenuScreen(this);
    }

    public void reInit() {
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();

        player = CharacterFactory.createPlayer(w / TILE_SIZE / 2, h / TILE_SIZE / 2, bus, "player", segment);

        map = new TmxMapLoader().load("core/assets/maps/theMap.tmx");

        world = new WorldMap(map, bus);
        segment = new MapSegment(this);

        player.addNewToInventory(ItemFactory.CreateSword());
        player.addNewToInventory(ItemFactory.CreateHammer());

        player.addNewToInventory(new Bomb(10));

        player.addNewToInventory(new Sword());
        player.addNewToInventory(new Hammer());
        player.setSlot1(player.getInventory().getItem("Sword"));
        player.setSlot2(player.getInventory().getItem("Bomb"));
    }

    @Override
    public void render() {

        super.render();

    }

    @Override
    public void dispose() {

        //img.dispose();
    }

    public boolean isSolid(int x, int y) {

        return Tiles.isSolid(world.getCurrentMap(), x, y);

    }


    public Position setNewMap(Position position) {

        return world.setNewMap(position);

    }


    @Subscribe
    public void handleMapChangedEvent(MapChangedEvent event) {

        segment.setEnemiesInWorld(Tiles.populateWorldWithEnemies(world.getCurrentMap(), bus));

    }

    public AssetManager getAssets() {
        return assets;
    }

    public WorldMap getMap() {
        return world;
    }

    private Array<TextureRegion> setupRunningFrames(TextureRegion region, int x, int y, int width, int height) {
        Array<TextureRegion> frames = new Array<>();
        int j = 0;
        for (int i = 1; i < 8; i++) {
            j = ((j + 1) % 2) + 1;
            TextureRegion tr = new TextureRegion(region, (j * x), y, width, height);
            frames.add(tr);
        }

        return frames;
    }


}