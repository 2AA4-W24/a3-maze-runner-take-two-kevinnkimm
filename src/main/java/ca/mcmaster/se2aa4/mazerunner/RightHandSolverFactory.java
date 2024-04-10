package ca.mcmaster.se2aa4.mazerunner;

public class RightHandSolverFactory implements MazeSolverFactory {
    
    @Override
    public MazeSolver createSolver() {
        // Factory method for RHS
        return new RightHandSolver();
    }
    
}
