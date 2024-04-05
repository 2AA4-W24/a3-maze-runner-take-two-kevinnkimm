package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchSolver implements MazeSolver {

    private Node node;

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

        while (!queue.isEmpty()) {
            Node current = queue.remove();

            if (current.getPosition().equals(maze.getEnd())) {
                break;
            }

            for (Direction direction : Direction.values()) {
                Position next = current.getPosition().move(direction);
                if (maze.isValidPosition(next) && !visited[next.getRow()][next.getColumn()] && !maze.isWall(next)) {
                    Node nextNode = new Node(direction, next);
                    queue.add(nextNode);
                    visited[next.getRow()][next.getColumn()] = true;
                    // Assuming Position has a setPrevious method to track path
                    newMap.put(next, current);
                }
            }
        }

        // for getting previous
        List<Node> list = new ArrayList<>();
        Node prev = newMap.get(maze.getEnd());
        while (prev != null) {
            list.add(prev);
            prev = newMap.get(prev.getPosition());
        }

        Path path = new Path();
        list = list.reversed();
        for (int i = 0; i < list.size() - 2; i++) {
            path = getPath(list.get(i), list.get(i + 1), path);
        }

        return path;
    }

    private Path getPath(Node first, Node second, Path previousPath) {
        // calculate first and second one somehow
        // figure out
        

        
        previousPath.addStep('F');
        return previousPath;


    }
}

// Queue start with a node
// while queue is not empty, take the next node from the queue
// mark as visited node
// then every single edges that the node is connected to, add them to the queue
//