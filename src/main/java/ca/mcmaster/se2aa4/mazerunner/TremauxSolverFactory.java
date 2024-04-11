package ca.mcmaster.se2aa4.mazerunner;

public class TremauxSolverFactory implements MazeSolverFactory {
    // Factory method for Tremaux
    @Override
    public MazeSolver createSolver() {
        return new TremauxSolver();
    }
}
