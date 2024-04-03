package ca.mcmaster.se2aa4.mazerunner;

public interface Finder {
    
    void findPath(char[][] maze) throws IllegalStateException;

    String getPathString() throws IllegalStateException;

    void leftOrientation();

    void rightOrientation();

}