package ca.mcmaster.se2aa4.mazerunner;

public interface MazeSolver {
    /**
     * Solve maze and return path through maze.
     *
     * @param maze Maze to solve
     * @return Path that solves the provided maze
     */
    Path solve(Maze maze);
}
