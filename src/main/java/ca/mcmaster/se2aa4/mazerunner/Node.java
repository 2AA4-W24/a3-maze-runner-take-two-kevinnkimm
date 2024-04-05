package ca.mcmaster.se2aa4.mazerunner;

public class Node {
    private Direction direction;
    private Position position;

    public Node(Direction direction, Position position) {
        this.direction = direction;
        this.position = position;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public Position getPosition() {
        return this.position;
    }

}
