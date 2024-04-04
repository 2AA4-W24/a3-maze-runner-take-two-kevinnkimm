package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BreadthFirstSearchSolver implements MazeSolver {

    private static final Logger logger = LogManager.getLogger();
    private List<List<Boolean>> grid = new ArrayList<>();
    private Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

    @Override
    public Path solve(Maze maze) {
        Path path = new Path();
        return path; // ignore these for now

    }

    public void adjacencyList(List<List<Boolean>> grid) {
        int rows = grid.size();
        int columns = grid.get(0).size();
        int vertexCount = rows * columns;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int currIdx = i * columns + j; // Calculate 1D index
                
                List<Integer> neighbors = new ArrayList<>();
                
                // Check and add valid neighbors
                if (i > 0 && !grid.get(i - 1).get(j)) {
                    neighbors.add((i - 1) * columns + j); // Upper neighbor
                }
                if (j > 0 && !grid.get(i).get(j - 1)) {
                    neighbors.add(i * columns + (j - 1)); // Left neighbor
                }
                if (i < rows - 1 && !grid.get(i + 1).get(j)) {
                    neighbors.add((i + 1) * columns + j); // Lower neighbor
                }
                if (j < columns - 1 && !grid.get(i).get(j + 1)) {
                    neighbors.add(i * columns + (j + 1)); // Right neighbor
                }
                
                adjacencyList.put(currIdx, neighbors);
            }
        }
        
        // Print the adjacency list for demonstration
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            System.out.println(entry.getValue());
        }
    }

    private void queue() {

    }

}
