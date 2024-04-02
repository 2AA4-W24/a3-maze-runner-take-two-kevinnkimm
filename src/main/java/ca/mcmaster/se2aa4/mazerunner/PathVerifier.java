package ca.mcmaster.se2aa4.mazerunner;

public class PathVerifier {

    private boolean isPathVerifiedEast;
    private boolean isPathVerifiedWest;
    private int entryRow;
    private int entryColumn;
    private int exitRow;
    private int exitColumn;
    private int row;
    private int column;
    private String defactorizedPath;
    private char orientation;

    // sets orientation from the right
    private void rightOrientation() {

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

    // sets orientation from the left
    private void leftOrientation() {
        
        switch (orientation){
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

    // checks if theres a wall or not in the front
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
    
    // method to move forward based on orientation
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

    // takes path input and defactorizes it for path verification
    private void defactorized(String path) {
        path = path.replaceAll("\\s", "");
        StringBuilder newPath = new StringBuilder();
        
        int i = 0;
        while (i < path.length()) {
            char currentChar = path.charAt(i);
    
            boolean isADigit = Character.isDigit(currentChar);
            if (isADigit) {
                int digit = Character.getNumericValue(currentChar);
    
                // checks if there is a next character
                int nextIteration = i + 1;
                if (path.length() > nextIteration) {
                    char nextChar = path.charAt(nextIteration);
    
                    // repeats the next character 
                    for (int j = 0; j < digit - 1; j++) {
                        newPath.append(nextChar);
                    }
                }
            } else {
                newPath.append(currentChar);
            }
            i += 1;
            
        }
    
        defactorizedPath = newPath.toString();
    }

    // path verification for east side
    public boolean verifiedPathEast(char[][] maze, String path) {
        defactorized(path);
        getEntry(maze);
        getExit(maze);
        // east side
        row = entryRow;
        column = entryColumn;

        orientation = 'E';
        // traverses through path and determines if path is true or false
        for (int i = 0; i < defactorizedPath.length(); i++) {
            
            if (defactorizedPath.charAt(i) == 'R') {
                rightOrientation();   
            }
            
            else if (defactorizedPath.charAt(i) == 'F') {                
                if (frontChecker(maze)) {
                    moveForward();
                }

                else {
                    break;
                }
            }

            else if (defactorizedPath.charAt(i) == 'L') {
                leftOrientation();
            }

            else {
                leftOrientation();
                leftOrientation();
            }
        }
        // checks if exit is reached
        if (row == exitRow && column == exitColumn) {
            isPathVerifiedEast = true;
        }

        return isPathVerifiedEast;
    }

    // verifies path from west side
    public boolean verifiedPathWest(char[][] maze, String path) {
        defactorized(path);
        getEntry(maze);
        getExit(maze);
        // west east
        row = exitRow;
        column = exitColumn;

        orientation = 'W';
        // traverses through path and determines if path is true or false
        for (int i = 0; i < defactorizedPath.length(); i++) {
            
            if (defactorizedPath.charAt(i) == 'R') {
                rightOrientation();   
            }
            
            else if (defactorizedPath.charAt(i) == 'F') {                
                if (frontChecker(maze)) {
                    moveForward();
                }

                else {
                    break;
                }
            }

            else if (defactorizedPath.charAt(i) == 'L') {
                leftOrientation();
            }

            else {
                leftOrientation();
                leftOrientation();
            }
        }
        
        // checks exit is reached in west side perspective
        if (row == entryRow && column == entryColumn) {
            isPathVerifiedWest = true;
        }

        return isPathVerifiedWest;
    }

    // returns boolean if either west or east path verification is true
    public boolean statement(char[][] maze, String path) {
        verifiedPathEast(maze, path);
        verifiedPathWest(maze, path);

        if (isPathVerifiedWest || isPathVerifiedEast) {
            return true;
        }
        return false;
    }


}
