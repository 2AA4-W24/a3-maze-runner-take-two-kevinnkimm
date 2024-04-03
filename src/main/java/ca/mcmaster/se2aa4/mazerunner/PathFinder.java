package ca.mcmaster.se2aa4.mazerunner;

public class PathFinder implements Finder {
    
    // private variables to store values
    private int row;
    private int column;
    private static String path;
    private int entryRow;
    private int entryColumn;
    private int exitRow;
    private int exitColumn;
    private char orientation;    
    
    // returns strings values of paths
    private String returnAllStrings() {
        System.out.println("Canonical path from east direction: " + addSpace());
        System.out.print("Factorized path: ");
        return getFactorizedPath();
    }

    @Override
    // returns canonical path and factorized path
    public String getPathString() {
        return returnAllStrings();
    }

    // adds a space when returning path value
    private static String addSpace() {
        
        StringBuilder space = new StringBuilder();
        space.append(path.charAt(0));
        int i = 1;
        while (i < path.length()) {
            char currentChar = path.charAt(i);
            char previousChar = path.charAt(i - 1);
            // determines to add a space
            boolean compare = currentChar != previousChar;
            if (compare) {
                space.append(" "); 
            }

            space.append(currentChar);
            i += 1;
        }

        return space.toString();
    }

    // combines and factors consecutive elements
    public String getFactorizedPath() {
        
        StringBuilder factorial = new StringBuilder();
        char currentChar = path.charAt(0);
        int count = 1;
        int i = 1;

        while(i < path.length()) {
            char nextChar = path.charAt(i);
            boolean compare = nextChar != currentChar;
            if (compare) {
                if (count > 1) {
                    factorial.append(count);
                }
                factorial.append(currentChar);
                factorial.append(" ");
                currentChar = nextChar;
                count = 1;
            } else {
                count++;
            }
            i += 1;
        }

        if (count > 1) {
            factorial.append(count);
        }
        factorial.append(currentChar);

        return factorial.toString();
    }

    // right orientation perspective
    @Override
    public void rightOrientation() {

        switch (orientation){
            case 'N':
                orientation = 'E';
                break;
            case 'E':
                orientation = 'S';
                break;
            case 'S':
                orientation = 'W';
                break;
            case 'W':
                orientation = 'N';
                break;

        }
    }
    
    // left orientation perspective
    @Override
    public void leftOrientation() {
        
        switch (orientation) {
            case 'N':
                orientation = 'W';
                break;
            case 'E':
                orientation = 'N';
                break;
            case 'S':
                orientation = 'E';
                break;
            case 'W':
                orientation = 'S';
                break;

        }
    }
    // checks if there is a '#' in the front
    private boolean frontChecker(char[][] maze) {

        if (orientation == 'W') {
            if (maze[row][column-1] == '#') {
                return false;
            } 
        }

        else if (orientation == 'S') {
            if (maze[row+1][column] == '#') {
                return false;
            } 
        }

        else if (orientation == 'E') {
            if (maze[row][column+1] == '#') {
                return false;
            } 
        }

        else if (orientation == 'N') {
            if (maze[row-1][column] == '#') {
                return false;
            } 
        }
        return true;
    }
    
    // checks if there is a '#' in the right
    private boolean rightChecker(char[][] maze) {

        if (orientation == 'W') {
            if (maze[row-1][column] == '#') {
                return false;
            } 
        }

        else if (orientation == 'S') {
            if (maze[row][column-1] == '#') {
                return false;
            } 
        }

        else if (orientation == 'E') {
            if (maze[row+1][column] == '#') {
                return false;
            } 
        }

        else if (orientation == 'N') {
            if (maze[row][column+1] == '#') {
                return false;
            } 
        }
        return true;
    }
    // checks if there is a '#' in the left
    private boolean leftChecker(char[][] maze) {

        if (orientation == 'W') {
            if (maze[row+1][column] == '#') {
                return false;
            } 
        }

        else if (orientation == 'S') {
            if (maze[row][column+1] == '#') {
                return false;
            } 
        }

        else if (orientation == 'E') {
            if (maze[row-1][column] == '#') {
                return false;
            } 
        }

        else if (orientation == 'N') {
            if (maze[row][column-1] == '#') {
                return false;
            } 
        }
        return true;
    }

    // changes row and column
    private void moveForward() {
        if (orientation == 'N') {
            row--;
        }

        else if (orientation == 'E') {
            column++;
        }

        else if (orientation == 'S') {
            row++;
        }

        else if (orientation == 'W') {
            column--;
        }

    }

    // traverses and gets entry on the maze if there is a ' ' 
    private void getEntry(char[][] maze) {
        row = 0;
        column = 0;

        for (int i = 0; i < maze.length; i++) {
            if (maze[i][0] == ' ') {
                entryRow = i;
                entryColumn = 0;
            }
        }
    }
    
    // traverses and gets exit on the maze if there is a ' '
    private void getExit(char[][] maze) {
        row = 0;
        column = maze[0].length - 1;

        for (int i = 0; i < maze.length; i++) {
            if (maze[i][column] == ' ') {
                exitRow = i;
                exitColumn = column;
            }
        }
    }
    // combines all methods to determine the path
    @Override
    public void findPath(char[][] maze) {
        // stores variables in the top state
        getEntry(maze);
        getExit(maze);
        orientation = 'E';

        // setting start location
        row = entryRow;
        column = entryColumn;
        path = "";

        // right hand rule
        while ((row != exitRow) || (column != exitColumn)) {
            if (rightChecker(maze)) {
                rightOrientation();
                moveForward();
                path = path + "R";
                path = path + "F";
            }
            
            else if (frontChecker(maze)) {
                moveForward();
                path = path + "F";
            }

            else if (leftChecker(maze)) {
                leftOrientation();
                moveForward();
                path = path + "L";
                path = path + "F";
            }

            else {
                leftOrientation();
                leftOrientation();
                path = path + "L";
                path = path + "L";
                moveForward();
                path = path + "F";
            }
        }
    }
}