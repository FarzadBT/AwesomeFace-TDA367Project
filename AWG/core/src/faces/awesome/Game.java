package faces.awesome;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import faces.awesome.controllers.InputCtrl;
import faces.awesome.controllers.PlayerCtrl;
import faces.awesome.model.Player;
import faces.awesome.model.Position;

public class Game extends ApplicationAdapter {
	public static final int TILE_SIZE = 32;
	SpriteBatch batch;
	//Texture img;
	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;
	Sprite sprite;
	Texture texture;
	Player player;
	PlayerCtrl playerCtrl;
	
	@Override
	public void create () {
		// setup model here.
		//batch = new SpriteBatch();
		//img = new Texture("core/assets/badlogic.jpg");

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		tiledMap = new TmxMapLoader().load("core/assets/testMap2.tmx");

		camera = new OrthographicCamera(1024, 512);
		camera.setToOrtho(false,w,h);
		camera.update();

		player = new Player(new Position(w/ TILE_SIZE /2, h/ TILE_SIZE /2));
		playerCtrl = new PlayerCtrl(player, tiledMap);

		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		Gdx.input.setInputProcessor(new InputCtrl(playerCtrl));

		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("core/assets/linkk.png"));
		sprite = new Sprite(texture);
	}

	@Override
	public void render () {
		// setup rendering logic for controllers
		Gdx.gl.glClearColor(1, 0, 0, 1);
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
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}


}