package faces.awesome.model;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.lang.*;

public class Player extends Character implements InputProcessor {
	private Facing moveDirection;
	private Facing facing;

	public Player(Position pos){
		super(pos);
	}


	@Override
	protected void move() {

	}

	public Facing getMoveDirection () {
		return moveDirection;
	}

	@Override
	public boolean keyDown(int keycode) {

		if(keycode == Input.Keys.LEFT) {
			moveDirection = Facing.WEST;
		}

		if(keycode == Input.Keys.RIGHT) {
			moveDirection = Facing.EAST;
		}

		if(keycode == Input.Keys.UP) {
			moveDirection = Facing.NORTH;
		}

		if(keycode == Input.Keys.DOWN) {
			moveDirection = Facing.SOUTH;
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

		moveDirection = null;

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