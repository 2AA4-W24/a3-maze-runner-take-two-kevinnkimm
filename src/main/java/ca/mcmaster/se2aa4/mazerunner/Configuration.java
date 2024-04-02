package ca.mcmaster.se2aa4.mazerunner;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Configuration {

    private static final Logger logger = LogManager.getLogger();
    private String filename;
    private String optionP;

    public String getFilename() {
        return filename;
    }

    public String getOptionP() {
        return optionP;
    }
    // creates new objects
    MazeGenerator mazeGenerator = new MazeGenerator();
    PathFinder pathFinder = new PathFinder();
    PathVerifier pathVerifier = new PathVerifier();
    Finder implement = new PathFinder();
    Options options = new Options();
    CommandLineParser parser = new DefaultParser();


    public void MazeExporter(String[] args) {
        try {
            logger.info("** Starting Maze Runner");
            // creates option tags i and p
            options.addOption("i", true, "put ur -i in there buddy");
            options.addOption("p", true, "put ur -p in there buddy");   
            CommandLine cmd = parser.parse(options, args);
            String optionValue = cmd.getOptionValue("i");
            String optionPValue = cmd.getOptionValue("p");
            filename = optionValue;
            optionP = optionPValue;
            // gets maze from filename
            mazeGenerator.maze(getFilename());
            char[][] newMaze = mazeGenerator.getMaze();
            implement.findPath(newMaze);
            System.out.println(implement.getPathString());
            pathVerifier.verifiedPathEast(newMaze, getOptionP());
            pathVerifier.verifiedPathWest(newMaze, getOptionP());
            System.out.println("Is path verified: " + pathVerifier.statement(newMaze, getOptionP()));
        } 
        
        catch(Exception e) {
            logger.error("/!\\ You have reached out of bounds /!\\");
        }

        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
    
}
