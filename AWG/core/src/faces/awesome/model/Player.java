package faces.awesome.model;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.lang.*;

public class Player extends Character implements InputProcessor {
	private Facing facing;

	public Player(Position pos){
		super(pos);
	}


	@Override
	protected void move() {

	}


	@Override
	public boolean keyDown(int keycode) {

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

		if(keycode == Input.Keys.LEFT) {
			pos.movePos(-32,0);
			//player.setPos(player.getPos().movePos(-32, 0));
		}

		if(keycode == Input.Keys.RIGHT) {
			pos.movePos(32,0);
			//player.setPos(player.getPos().movePos(32, 0));
		}

		if(keycode == Input.Keys.UP) {
			pos.movePos(0,32);
			//player.setPos(player.getPos().movePos(0, 32));
		}

		if(keycode == Input.Keys.DOWN) {
			pos.movePos(0,-32);
			//player.setPos(player.getPos().movePos(0, -32));
		}

		return false;
	}

	//Methods we do not use
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