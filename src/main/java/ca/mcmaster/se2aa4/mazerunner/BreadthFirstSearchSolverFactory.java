package ca.mcmaster.se2aa4.mazerunner;

public class BreadthFirstSearchSolverFactory implements MazeSolverFactory {
    
    @Override
    public MazeSolver createSolver() {
        return new BreadthFirstSearchSolver();
    }
    
}

