package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*; // PMD false positive (UnnecessaryImport), Main needs to import this to use parsing
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        
        if (logger.isInfoEnabled()) {
           logger.info("** Starting Maze Runner");         
        }
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(getParserOptions(), args);
            String filePath = cmd.getOptionValue('i');
            Maze maze = new Maze(filePath);

            if (cmd.getOptionValue("p") != null) {
                logger.info("Validating path");
                Path path = new Path(cmd.getOptionValue("p"));
                if (maze.validatePath(path)) {
                    System.out.println("correct path");
                } else {
                    System.out.println("incorrect path");
                }
            }
            
            else if (cmd.hasOption("baseline") & cmd.hasOption("method")) {
                String baseline = cmd.getOptionValue("baseline");
                String method = cmd.getOptionValue("method");
                System.out.println("Activating baseline using: " + baseline);
                System.out.println("Activating method using: " + method);
                
                // time spent loading maze
                long benchmarkStartTime = System.currentTimeMillis();
                new Maze(filePath);
                long benchmarkEndTime = System.currentTimeMillis();
                double benchmarkTime = benchmarkEndTime - benchmarkStartTime;

                // time spent exploring maze using -baseline
                long baselineStartTime = System.currentTimeMillis();
                Path baselinePath = solveMaze(baseline, maze);
                long baselineEndTime = System.currentTimeMillis();
                float baselineTime = (float) (baselineEndTime - baselineStartTime);

                
                // time spent exploring maze using -method
                long methodStartTime = System.currentTimeMillis();
                Path methodLinePath = solveMaze(method, maze);
                long methodEndTime = System.currentTimeMillis();
                float methodLineTime = (float) (methodEndTime - methodStartTime);

                // for the speed up path
                float speedUpPath = (float) baselinePath.getLength() / methodLinePath.getLength();

                // rounding to 2 decimal
                String formattedLoadTime = String.format("%.2f", benchmarkTime);
                String formattedBaselineTime = String.format("%.2f", baselineTime);
                String speedUp = String.format("%.2f", methodLineTime);
                String newSpeedUp = String.format("%.2f", speedUpPath);
                
                // printing stdout of loading file, solving maze using baseline and method, and speed up time
                System.out.println("Time spent loading file: " + formattedLoadTime + " milliseconds");
                System.out.println("Time spent solving maze baseline: " + formattedBaselineTime + " milliseconds");
                System.out.println("Time spent solving maze method: " + speedUp + " milliseconds");
                System.out.println("Speed up time is baseline/method ->  " + baselinePath.getLength() + "/" + methodLinePath.getLength() + " = " + newSpeedUp);
            }
            // only using baseline command
            else if (cmd.hasOption("baseline")) {
                String baseline = cmd.getOptionValue("baseline");
                
                // time spent loading maze
                long benchmarkStartTime = System.currentTimeMillis();
                new Maze(filePath);
                long benchmarkEndTime = System.currentTimeMillis();
                double benchmarkTime = benchmarkEndTime - benchmarkStartTime;

                // time spent exploring maze using -baseline
                long baselineStartTime = System.currentTimeMillis();
                solveMaze(baseline, maze);
                long baselineEndTime = System.currentTimeMillis();
                float baselineTime = (float) (baselineEndTime - baselineStartTime);
                
                // rounding to 2 decimal
                String formattedLoadTime = String.format("%.2f", benchmarkTime);
                String formattedBaselineTime = String.format("%.2f", baselineTime);
                
                // printing method
                System.out.println("Time spent loading file: " + formattedLoadTime + " milliseconds");
                System.out.println("Time spent solving maze baseline: " + formattedBaselineTime + " milliseconds");
            }

            else {
                String method = cmd.getOptionValue("method", "righthand");
                Path path = solveMaze(method, maze);
                System.out.println(path.getFactorizedForm());
            }
        } catch (Exception e) {
            System.err.println("MazeSolver failed.  Reason: " + e.getMessage());
            if (logger.isErrorEnabled()) {
                logger.error("MazeSolver failed.  Reason: " + e.getMessage());
                logger.error("PATH NOT COMPUTED");     
            }
        }
        if (logger.isInfoEnabled()) {
            logger.info("End of MazeRunner");         
        }
    }

    /**
     * Solve provided maze with specified method.
     *
     * @param method Method to solve maze with
     * @param maze Maze to solve
     * @return Maze solution path
     * @throws Exception If provided method does not exist
     */
    private static Path solveMaze(String method, Maze maze) throws Exception {
        MazeSolverFactory factory;
        switch (method) {
            case "righthand":
                factory = new RightHandSolverFactory();
                if (logger.isDebugEnabled()) {
                    logger.debug("RightHand algorithm chosen.");
                }
                break;
            case "tremaux":
                factory = new TremauxSolverFactory();
                if (logger.isDebugEnabled()) {
                    logger.debug("Tremaux algorithm chosen.");
                }
                break;
            case "bfs":
                factory = new BreadthFirstSearchSolverFactory();
                if (logger.isDebugEnabled()) {
                    logger.debug("Breadth First Search algorithm chosen.");
                }
                break;
            default:
                throw new Exception("Maze solving method '" + method + "' not supported.");
        }
        if (logger.isDebugEnabled()) {
            logger.info("Computing path");
        }
        MazeSolver solver = factory.createSolver();
        return solver.solve(maze);
    }

    /**
     * Get options for CLI parser.
     *
     * @return CLI parser options
     */
    private static Options getParserOptions() {
        Options options = new Options();

        Option fileOption = new Option("i", true, "File that contains maze");
        fileOption.setRequired(true);
        options.addOption(fileOption);

        options.addOption(new Option("p", true, "Path to be verified in maze"));
        options.addOption(new Option("method", true, "Specify which path computation algorithm will be used"));
        options.addOption(new Option("baseline", true, "Benchmark with algorithms"));

        return options;
    }
}
