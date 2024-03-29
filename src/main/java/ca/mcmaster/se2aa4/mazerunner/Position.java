package ca.mcmaster.se2aa4.mazerunner;

public record Position(int x, int y) {
    /**
     * Adds another position to this position and return the sum of both.
     *
     * @param other Other position
     * @return Sum of both positions
     */
    public Position add(Position other) {
        return new Position(this.x + other.x, this.y + other.y);
    }

    /**
     * Return the new position after moving in provided direction.
     *
     * @param direction Direction in which to move
     * @return The new position
     */
    public Position move(Direction direction) {
        switch (direction) {
            case UP -> {
                return this.add(new Position(0, -1));
            }
            case DOWN -> {
                return this.add(new Position(0, 1));
            }
            case LEFT -> {
                return this.add(new Position(-1, 0));
            }
            case RIGHT -> {
                return this.add(new Position(1, 0));
            }
        }
        throw new IllegalStateException("Unexpected value: " + this);
    }

    @Override
    public String toString() {
        return "Position(" + this.x + "," + this.y + ")";
    }
}
