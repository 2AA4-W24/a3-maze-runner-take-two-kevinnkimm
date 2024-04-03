package ca.mcmaster.se2aa4.mazerunner;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;


public class CommandParser {
    private Options options = new Options();
    private CommandLineParser parser = new DefaultParser();

    public CommandParser() {
        options = new Options();
        parser = new DefaultParser();

        // Define options
        options.addOption("i", true, "enter -i in the command line");
        options.addOption("p", true, "enter -p in the command line");
    }

    public CommandLine parseCommandLine(String[] args) throws org.apache.commons.cli.ParseException {
        return parser.parse(options, args);
    }
}
