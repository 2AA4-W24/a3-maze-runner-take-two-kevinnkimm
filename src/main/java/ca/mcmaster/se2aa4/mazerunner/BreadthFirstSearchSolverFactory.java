package ca.mcmaster.se2aa4.mazerunner;

public class BreadthFirstSearchSolverFactory implements MazeSolverFactory {
    // Factory method for BFS
    @Override
    public MazeSolver createSolver() {
        return new BreadthFirstSearchSolver();
    }
    
}

