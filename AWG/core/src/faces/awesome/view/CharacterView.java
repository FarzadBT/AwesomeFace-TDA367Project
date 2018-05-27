package faces.awesome.view;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import faces.awesome.GDXWrapper;
import faces.awesome.model.characters.Character;
import faces.awesome.services.AssetManager;
import faces.awesome.model.Facing;
import faces.awesome.model.Position;
import faces.awesome.utils.AwesomeTimer;

/**
 * @author Linus Wallman
 * NOTE to teacher: We started working on animations, but couldn't finish it in time due to issues with libgdx (couldn't solve that problem in time)
 */

public class CharacterView extends GameObjectView {

    public enum State {STANDING, RUNNING}

    private State currentState = State.STANDING;

    private Position localPos;

    private AwesomeTimer timer = new AwesomeTimer();

    public CharacterView(Character c) {
        super(c);
        localPos = new Position(c.getPos().getX(), c.getPos().getY());
    }

    @Override
    public void draw(SpriteBatch sprBatch) {
        System.out.println(gameObject.getFacing());
        switch (currentState) {
            case STANDING: {
                TextureRegion region;
                if (gameObject.getFacing() == Facing.NORTH) {
                    region = AssetManager.getInstance().getTexture(gameObject.getName() + "-north");
                } else if (gameObject.getFacing() == Facing.WEST) {
                    region = AssetManager.getInstance().getTexture(gameObject.getName() + "-west");
                } else if (gameObject.getFacing() == Facing.SOUTH) {
                    region = AssetManager.getInstance().getTexture(gameObject.getName() + "-south");
                } else if (gameObject.getFacing() == Facing.EAST) {
                    region = AssetManager.getInstance().getTexture(gameObject.getName() + "-east");
                } else {
                    throw new NullPointerException();
                }

                sprBatch.draw(region, (gameObject.getPos().getX() % 32) * GDXWrapper.TILE_SIZE, (gameObject.getPos().getY() % 16) * GDXWrapper.TILE_SIZE);
                break;
            }

            case RUNNING: {
                if (!(localPos.equals(gameObject.getPos()))) {
                    return;
                }

                Animation<TextureRegion> region;
                int xPattern;
                int yPattern;
                if (gameObject.getFacing() == Facing.NORTH) {
                    region = AssetManager.getInstance().getAnimation(gameObject.getName() + "-anim-north");
                    xPattern = 0;
                    yPattern = 1;
                } else if (gameObject.getFacing() == Facing.WEST) {
                    region = AssetManager.getInstance().getAnimation(gameObject.getName() + "-anim-west");
                    xPattern = -1;
                    yPattern = 0;
                } else if (gameObject.getFacing() == Facing.SOUTH) {
                    region = AssetManager.getInstance().getAnimation(gameObject.getName() + "-anim-south");
                    xPattern = 0;
                    yPattern = -1;
                } else if (gameObject.getFacing() == Facing.EAST) {
                    region = AssetManager.getInstance().getAnimation(gameObject.getName() + "-anim-east");
                    xPattern = 1;
                    yPattern = 0;
                } else {
                    region = null;
                    xPattern = 0;
                    yPattern = 0;
                }

                drawWalk(sprBatch, region, localPos, gameObject.getPos(), xPattern, yPattern, 4);

                break;
            }

            default:
                break;
        }
    }

    private void drawWalk(SpriteBatch sprBatch, Animation<TextureRegion> region, Position oldPos, Position destination, int xPattern, int yPattern, int walkOffset) {
        if (walkOffset >= GDXWrapper.TILE_SIZE) {
            currentState = State.STANDING;
            return;
        }

        if (currentState != State.RUNNING) {
            currentState = State.RUNNING;
        }

        timer.restart();
        stateTime += 0.025f;


        sprBatch.draw(region.getKeyFrame(stateTime), ((localPos.getX() + walkOffset * 4) % 32) * GDXWrapper.TILE_SIZE, ((localPos.getY() + xPattern * 4) % 16) * GDXWrapper.TILE_SIZE);
        drawWalk(sprBatch, region, oldPos, destination, xPattern, yPattern, walkOffset + 4);
    }

    private boolean hasReachedDestination(Position oldPos, Position destination) {
        return oldPos.equals(destination);
    }

    public State getCurrentState() {
        return currentState;
    }

    private void setState(State state) {
        currentState = state;
    }

}