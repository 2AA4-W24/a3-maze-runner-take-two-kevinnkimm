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
        boolean[][] visited = new boolean[maze.getSizeY()][maze.getSizeX()];
        HashMap<Position, Node> newMap = new HashMap<>();
        Position start = maze.getStart();
        Node startNode = new Node(Direction.RIGHT, start);
        Node endNode = new Node(Direction.RIGHT, start);

        queue.add(startNode);
        visited[start.getRow()][start.getColumn()] = true;
        newMap.put(startNode.getPosition(), null);

        while (!queue.isEmpty()) {
            Node current = queue.remove();

            if (current.getPosition().equals(maze.getEnd())) {
                endNode = current;
                break;
            }

            for (Direction direction : Direction.values()) {
                Position next = current.getPosition().move(direction);

                if (maze.isValidPosition(next) && !visited[next.getRow()][next.getColumn()] && !maze.isWall(next)) {
                    Node nextNode = new Node(direction, next);
                    queue.add(nextNode);
                    visited[next.getRow()][next.getColumn()] = true;
                    newMap.put(next, current);
                }
            }

            
        }
        // for getting previous
        List<Node> list = new ArrayList<>();
        Node prev = newMap.get(maze.getEnd());
        list.add(endNode);
        while (prev != null) {
            list.add(prev);
            prev = newMap.get(prev.getPosition());
        }

        Path path = new Path();
        list = list.reversed();
        for (int i = 0; i < list.size() - 1; i++) {
            path = getPath(list.get(i), list.get(i + 1), path);
        }
        return path;
    }

    // could be an issue with my path
    private Path getPath(Node first, Node second, Path previousPath) {
        if (first.getDirection() == second.getDirection()) {
            previousPath.addStep('F');
        }
    
        else if (second.getDirection() == first.getDirection().turnLeft()) { // might have to fix that
            previousPath.addStep('L');
            previousPath.addStep('F');
        }

        else if (second.getDirection() == first.getDirection().turnRight()) {
            previousPath.addStep('R');
            previousPath.addStep('F');
        }
        return previousPath;
    }
}