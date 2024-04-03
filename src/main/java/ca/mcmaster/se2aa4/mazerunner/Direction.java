package ca.mcmaster.se2aa4.mazerunner;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    /**
     * Get the direction to the right of the current one.
     *
     * @return The direction to the right.
     */
    public Direction turnRight() {
        switch (this) {
            case UP -> {
                return RIGHT;
            }
            case DOWN -> {
                return LEFT;
            }
            case LEFT -> {
                return UP;
            }
            case RIGHT -> {
                return DOWN;
            }
        }
        throw new IllegalStateException("Unexpected value: " + this);
    }

    /**
     * Get the direction to the left of the current one.
     *
     * @return The direction to the left.
     */
    public Direction turnLeft() {
        switch (this) {
            case UP -> {
                return LEFT;
            }
            case DOWN -> {
                return RIGHT;
            }
            case LEFT -> {
                return DOWN;
            }
            case RIGHT -> {
                return UP;
            }
        }
        throw new IllegalStateException("Unexpected value: " + this);
    }
}
