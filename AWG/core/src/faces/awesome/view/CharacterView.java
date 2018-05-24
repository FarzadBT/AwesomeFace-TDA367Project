package faces.awesome.view;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import faces.awesome.GDXWrapper;
import faces.awesome.model.AssetManager;
import faces.awesome.model.Character;
import faces.awesome.model.Facing;
import faces.awesome.model.Position;
import faces.awesome.utils.AwesomeTimer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Linus Wallman
 *
 */

public class CharacterView extends GameObjectView {

    public enum State {STANDING, RUNNING}

    ;
    private State currentState = State.STANDING;

    private Position localPos;

    AwesomeTimer timer = new AwesomeTimer();

    public CharacterView(Character c) {
        super(c);
        localPos = c.getPos().cpy();
    }

    @Override
    public void draw(SpriteBatch sprBatch) {
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
                    // throw some exception perhaps. However, this case should never happen. It's just here to keep the interpreter from going insane
                    //region = null;
                }
                sprBatch.draw(region, gameObject.getPos().getX() * GDXWrapper.TILE_SIZE, gameObject.getPos().getY() * GDXWrapper.TILE_SIZE);
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
        if (hasReachedDestination(oldPos, destination)) {
            localPos = gameObject.getPos().cpy();
            currentState = State.STANDING;
            return;
        }

        if (currentState != State.RUNNING) {
            currentState = State.RUNNING;
        }

        if (walkOffset > GDXWrapper.TILE_SIZE) {
            return;
        }

        if (timer.ticksElapsed() >= 31) {
            Timer tt = new Timer();
            tt.schedule(new TimerTask() {
                @Override
                public void run() {
                    sprBatch.draw(region.getKeyFrame(stateTime), localPos.getX() + xPattern * 4, localPos.getY() * yPattern * 4);

                    drawWalk(sprBatch, region, oldPos, destination, xPattern, yPattern, walkOffset + 4);
                }
            }, 31); // milliseconds
        }

        drawWalk(sprBatch, region, oldPos, destination, xPattern, yPattern, walkOffset);

    }

    private boolean hasReachedDestination(Position oldPos, Position destination) {
        return oldPos.equals(destination);
    }
}
