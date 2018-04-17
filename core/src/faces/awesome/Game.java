package faces.awesome;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import faces.awesome.model.Facing;
import faces.awesome.model.Player;
import faces.awesome.model.Position;

public class Game extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture img;
	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;
	Sprite sprite;
	Texture texture;
	Player player;
	Facing direction;
	
	@Override
	public void create () {
		// setup model here.
		//batch = new SpriteBatch();
		//img = new Texture("core/assets/badlogic.jpg");

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		player = new Player(new Position(w/2, h/2));

		camera = new OrthographicCamera();
		camera.setToOrtho(false,w,h);
		camera.update();
		tiledMap = new TmxMapLoader().load("core/assets/testMap2.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		Gdx.input.setInputProcessor((InputProcessor) this);

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

		if(direction == Facing.WEST) {
			player.setPos(player.getPos().movePos(-32, 0));
		}

		if(direction == Facing.EAST) {
			player.setPos(player.getPos().movePos(32, 0));
		}

		if(direction == Facing.NORTH) {
			player.setPos(player.getPos().movePos(0, 32));
		}

		if(direction == Facing.SOUTH) {
			player.setPos(player.getPos().movePos(0, -32));
		}


		batch.begin();
		sprite.setPosition(player.getPos().getX(), player.getPos().getY());
		sprite.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}


	@Override
	public boolean keyUp(int keycode) {

		direction = null;

		return false;
	}

	@Override
	public boolean keyDown(int keycode) {

		if(keycode == Input.Keys.LEFT) {
			direction = Facing.WEST;
		}

		if(keycode == Input.Keys.RIGHT) {
			direction = Facing.EAST;
		}

		if(keycode == Input.Keys.UP) {
			direction = Facing.NORTH;
		}

		if(keycode == Input.Keys.DOWN) {
			direction = Facing.SOUTH;
		}

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}


}
