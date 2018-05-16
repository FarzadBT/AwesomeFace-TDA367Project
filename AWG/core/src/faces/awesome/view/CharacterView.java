package faces.awesome.view;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import faces.awesome.AwesomeGame;
import faces.awesome.model.Character;
import faces.awesome.model.Facing;
import faces.awesome.model.Position;

import java.util.Timer;
import java.util.TimerTask;

public class CharacterView {

    public enum State { STANDING, RUNNING }

    private AnimationDefinition animDefs;

    private final Character character;

    private State currentState = State.STANDING;

    //private TextureRegion currentTexture;

    //private Animation<TextureRegion> currentAnimation;

    CharacterView(Character c) {
        this.character = c;
    }

    public void draw(SpriteBatch sprBatch, Position oldPos, Position destination) {
        switch (currentState) {
            case STANDING: {
                TextureRegion region;
                if (character.getFacing() == Facing.NORTH) {
                    region = animDefs.getCharacterStandingUp();
                } else if (character.getFacing() == Facing.WEST) {
                    region = animDefs.getCharacterStandingLeft();
                } else if (character.getFacing() == Facing.SOUTH) {
                    region = animDefs.getCharacterStandingDown();
                } else if (character.getFacing() == Facing.EAST) {
                    region = animDefs.getCharacterStandingRight();
                } else {
                    // throw some exception perhaps. However, this case should never happen. It's just here to keep the interpreter from going insane
                    region = null;
                }
                sprBatch.draw(region, character.getPos().getX() * AwesomeGame.TILE_SIZE, character.getPos().getY() * AwesomeGame.TILE_SIZE);
                break;
            }

            case RUNNING: {
                Animation<TextureRegion> region;
                if (character.getFacing() == Facing.NORTH) {
                    region = animDefs.getCharacterRunningUp();
                } else if (character.getFacing() == Facing.WEST) {
                    region = animDefs.getCharacterRunningLeft();
                } else if (character.getFacing() == Facing.SOUTH) {
                    region = animDefs.getCharacterRunningDown();
                } else if (character.getFacing() == Facing.EAST) {
                    region = animDefs.getCharacterRunningRight();
                } else {
                    region = null;
                }
                // TextureRegion currentFrame = region.getKeyFrame();
                // sprBatch.draw(currentFrame, )

                drawWalk(region, oldPos, destination, 4);

                break;
            }

            default:
                break;
        }

    }

    private void drawWalk(Animation<TextureRegion> region, Position oldPos, Position destination, int walkOffset) {
        if (hasReachedDestination(oldPos, destination)) {
            currentState = State.STANDING;
            return;
        }

        if (currentState != State.RUNNING) {
            currentState = State.RUNNING;
        }

        if (walkOffset > AwesomeGame.TILE_SIZE) {
            return;
        }


    }

    private boolean hasReachedDestination(Position oldPos, Position destination) {
        return oldPos.equals(destination);
    }

    /*
    private void scheduleWalk(Position from, Position destination) {

        if (from.equals(destination)) {
            return;
        }

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println();
            }
        }, 62); // milliseconds
    }*/
}
