package faces.awesome.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import faces.awesome.GDXWrapper;
import faces.awesome.model.GameObject;

/**
 * @author Linus Wallman
 */
public class ItemView extends GameObjectView {

    public ItemView(GameObject o) {
        super(o);
    }

    @Override
    public void draw(SpriteBatch sprBatch) {
        TextureRegion region = GDXWrapper.assets.getTexture(gameObject.getName());
        sprBatch.draw(region, gameObject.getPos().getY() * GDXWrapper.TILE_SIZE, gameObject.getPos().getY() * GDXWrapper.TILE_SIZE);
    }
}
