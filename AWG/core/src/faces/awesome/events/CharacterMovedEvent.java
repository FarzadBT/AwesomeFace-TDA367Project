package faces.awesome.events;

import faces.awesome.model.Position;

/**
 * @author Linus Wallman
 */
public class CharacterMovedEvent {
    Position pos;

    public CharacterMovedEvent(Position pos) {
        this. pos = pos;
    }

    public Position getPosition() {
        return pos;
    }
}
