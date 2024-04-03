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
    private Direction orientation;
    private MazeGenerator mazeGenerator;
    private char[][] maze = mazeGenerator.getMaze();

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
            case N:
                orientation = Direction.E;
                break;
            case E:
                orientation = Direction.S;
                break;
            case S:
                orientation = Direction.W;
                break;
            case W:
                orientation = Direction.N;
                break;

        }
    }
    
    // left orientation perspective
    @Override
    public void leftOrientation() {
        
        switch (orientation) {
            case N:
                orientation = Direction.W;
                break;
            case E:
                orientation = Direction.N;
                break;
            case S:
                orientation = Direction.E;
                break;
            case W:
                orientation = Direction.S;
                break;

        }
    }
    // checks if there is a '#' in the front
    private boolean frontChecker() {
        if (orientation == Direction.W) {
            if (maze[row][column-1] == '#') {
                return false;
            } 
        }

        else if (orientation == Direction.S) {
            if (maze[row+1][column] == '#') {
                return false;
            } 
        }

        else if (orientation == Direction.E) {
            if (maze[row][column+1] == '#') {
                return false;
            } 
        }

        else if (orientation == Direction.N) {
            if (maze[row-1][column] == '#') {
                return false;
            } 
        }
        return true;
    }
    
    // checks if there is a '#' in the right
    private boolean rightChecker() {

        if (orientation == Direction.W) {
            if (maze[row-1][column] == '#') {
                return false;
            } 
        }

        else if (orientation == Direction.S) {
            if (maze[row][column-1] == '#') {
                return false;
            } 
        }

        else if (orientation == Direction.E) {
            if (maze[row+1][column] == '#') {
                return false;
            } 
        }

        else if (orientation == Direction.N) {
            if (maze[row][column+1] == '#') {
                return false;
            } 
        }
        return true;
    }
    // checks if there is a '#' in the left
    private boolean leftChecker() {

        if (orientation == Direction.W) {
            if (maze[row+1][column] == '#') {
                return false;
            } 
        }

        else if (orientation == Direction.S) {
            if (maze[row][column+1] == '#') {
                return false;
            } 
        }

        else if (orientation == Direction.E) {
            if (maze[row-1][column] == '#') {
                return false;
            } 
        }

        else if (orientation == Direction.N) {
            if (maze[row][column-1] == '#') {
                return false;
            } 
        }
        return true;
    }

    // changes row and column
    private void moveForward() {
        if (orientation == Direction.N) {
            row--;
        }

        else if (orientation == Direction.E) {
            column++;
        }

        else if (orientation == Direction.S) {
            row++;
        }

        else if (orientation == Direction.W) {
            column--;
        }
    }

    // traverses and gets entry on the maze if there is a ' ' 
    private void getEntry() {
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
    private void getExit() {
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
        getEntry();
        getExit();
        orientation = Direction.E;

        // setting start location
        row = entryRow;
        column = entryColumn;
        path = "";

        // right hand rule
        while ((row != exitRow) || (column != exitColumn)) {
            if (rightChecker()) {
                rightOrientation();
                moveForward();
                path = path + "R";
                path = path + "F";
            }
            
            else if (frontChecker()) {
                moveForward();
                path = path + "F";
            }

            else if (leftChecker()) {
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


