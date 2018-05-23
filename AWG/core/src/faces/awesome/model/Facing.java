package faces.awesome.model;

/*
 * Author: Philip Nilsson
 * Updated by: Linus Wallman, added INVALIDFACING
 *
 * This class defines an enum for which direction you are facing.
 *
 * short note: The INVALIDFACING value in the enum is in place to replace usage of null in facings.
 */

public enum Facing {
    NORTH,
    EAST,
    WEST,
    SOUTH,
    INVALIDFACING
}
