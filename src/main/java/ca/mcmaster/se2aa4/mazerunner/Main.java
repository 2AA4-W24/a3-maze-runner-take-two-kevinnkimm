package ca.mcmaster.se2aa4.mazerunner;


public class Main {

    public static void main(String[] args) {
        // calling configuration class methods
        try {
            MazeRunner configuration = new MazeRunner();
            configuration.MazeExporter(args);
            configuration.getFilename();
        }
        
        catch (Exception e) {
            System.out.println(e);
        }

    }
}
