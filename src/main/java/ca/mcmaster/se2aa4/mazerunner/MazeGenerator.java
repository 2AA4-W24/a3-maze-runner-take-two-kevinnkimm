package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MazeGenerator {
    // add values to a maze
    private char[][] maze;

    // return the maze, instead of instantiating a new class for each class
    public char[][] getMaze() {
        return maze;
    }
    // gets height
    private int findHeight(String filepath) {
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            while(reader.readLine() != null) {
                count++;
            }
            reader.close();
        }

        catch (IOException e) {
            System.out.print("error");
        }
        return count;
    }
    // gets width
    private int findWidth(String filepath) {
        int count = 0;
            try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            count = reader.readLine().length();
            reader.close();
        }
            catch (IOException e) {
                System.out.print("error");
            }
        return count;
    }

    // assign maze state and create new maze from file
    public void maze(String filepath) throws FileNotFoundException {
        
        int height = findHeight(filepath);
        int width = findWidth(filepath); 

        char[][] newMaze = new char[height][width];
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            int rows = 0;
            String line;
            // traverses and copies a maze
            while ((line = reader.readLine())!= null) {
                System.out.println(line);
                for (int columns = 0; columns < width; columns++) {
                    if (line.charAt(columns) == ' ' || line.charAt(columns) == '\0')  {
                        newMaze[rows][columns] = ' ';
                    }
                    else if (line.charAt(columns) == '#') {
                        newMaze[rows][columns] = '#';
                    }
                }

                rows++;
            }
            reader.close();
        }

        catch (IOException e) {

        }
        maze = newMaze;
        
    }

}
