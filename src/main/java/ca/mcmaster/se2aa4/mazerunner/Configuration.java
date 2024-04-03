package ca.mcmaster.se2aa4.mazerunner;
import org.apache.commons.cli.CommandLine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Configuration {

    private static final Logger logger = LogManager.getLogger();
    private MazeGenerator mazeGenerator = new MazeGenerator();
    private PathFinder pathFinder = new PathFinder();
    private PathVerifier pathVerifier = new PathVerifier();
    private String filename;
    private String optionP;
    
    public String getFilename() {
        return filename;
    }

    public String getOptionP() {
        return optionP;
    }

    public void MazeExporter(String[] args) {
        try {
            logger.info("** Starting Maze Runner");
            // creates option tags i and p
            CommandParser cmdParser = new CommandParser();
            CommandLine cmd = cmdParser.parseCommandLine(args);
            String optionValue = cmd.getOptionValue("i");
            String optionPValue = cmd.getOptionValue("p");

            // generates maze
            filename = optionValue;
            optionP = optionPValue;
            mazeGenerator.maze(getFilename());
            char[][] newMaze = mazeGenerator.getMaze();

            // finding path
            pathFinder.findPath(newMaze);
            logger.info(pathFinder.getPathString());

            // verification
            pathVerifier.verifiedPathEast(newMaze, getOptionP());
            pathVerifier.verifiedPathWest(newMaze, getOptionP());
            logger.info("Is path verified: " + pathVerifier.statement(newMaze, getOptionP()));
        } 
        
        catch(Exception e) {
            logger.error("/!\\ You have reached out of bounds /!\\");
        }

        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
    
}