package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchSolver implements MazeSolver {

    @Override
    public Path solve(Maze maze) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[maze.getSizeX()][maze.getSizeY()];
        HashMap<Position, Node> newMap = new HashMap<>(); // [1] is prev Position rlly important said Alina she
                                                          // wants say it louder :D shes laughing is she okay
        Position start = maze.getStart();
        Node startNode = new Node(Direction.RIGHT, start);

        queue.add(startNode);
        visited[start.getRow()][start.getColumn()] = true;
        newMap.put(startNode.getPosition(), null);

        while (!queue.isEmpty()) {
            Node current = queue.remove();

            if (current.getPosition().equals(maze.getEnd())) {
                break;
            }

            for (Direction direction : Direction.values()) {
                Position next = current.getPosition().move(direction);
                if (maze.isValidPosition(next) && !visited[next.getRow()][next.getColumn()] && !maze.isWall(next)) {
                    Node nextNode = new Node(direction, next);
                    System.out.println(
                            "on cell: " + current.getPosition() + " looking at edge: " + nextNode.getPosition());
                    queue.add(nextNode);
                    visited[next.getRow()][next.getColumn()] = true;
                    // Assuming Position has a setPrevious method to track path
                    newMap.put(next, current);

                    if (start.equals(maze.getEnd())) {
                        break;
                    }
                }
            }
        }
        // for getting previous
        List<Node> list = new ArrayList<>();
        Node prev = newMap.get(maze.getEnd());
        list.add(prev);
        System.out.println("we've just added the first position i believe");
        while (prev != null) {
            list.add(prev);
            System.out.println("we've just added to prev: " + prev.getPosition());
            prev = newMap.get(prev.getPosition());
        }

        Path path = new Path();
        list = list.reversed();
        for (int i = 0; i < list.size() - 1; i++) {
            System.out.println("get pathing for " + list.get(i).getPosition());
            path = getPath(list.get(i), list.get(i + 1), path);
        }
        System.out.println("complete");
        return path;
    }

    private Path getPath(Node first, Node second, Path previousPath) {
        System.out.println("we're looking at " + first.getPosition() + " and " + second.getPosition());
        if (first.getDirection() == second.getDirection()) {
            System.out.println("it's forward");
            previousPath.addStep('F');
        }

        else if (second.getDirection() == first.getDirection().turnLeft()) { // might have to fix that
            System.out.println("it's left");
            previousPath.addStep('L');
            previousPath.addStep('F');
        }

        else if (second.getDirection() == first.getDirection().turnRight()) {
            System.out.println("it's right");
            previousPath.addStep('R');
            previousPath.addStep('F');
        }

        return previousPath;

    }
}

// Queue start with a node
// while queue is not empty, take the next node from the queue
// mark as visited node
// then every single edges that the node is connected to, add them to the queue
//