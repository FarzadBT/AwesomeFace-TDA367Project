package faces.awesome.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import faces.awesome.model.GameObject;


public abstract class GameObjectView {

    protected GameObject gameObject;

    protected float stateTime = 0.f;

    public GameObjectView(GameObject o) {
        this.gameObject = o;
    }

    public abstract void draw(SpriteBatch sprBatch);
}
