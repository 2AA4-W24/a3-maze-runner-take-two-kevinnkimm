package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PathTest {
    @Test
    void getCanonicalForm() {
        Path path = new Path("FLFFFFFRFFRFFLFFFFFFRFFFFLF");

        assertEquals("F L FFFFF R FF R FF L FFFFFF R FFFF L F", path.getCanonicalForm());
    }

    @Test
    void getFactorizedForm() {
        Path path = new Path("FLFFFFFRFFRFFLFFFFFFRFFFFLF");

        assertEquals("F L 5F R 2F R 2F L 6F R 4F L F", path.getFactorizedForm());
    }

    @Test
    void expandedPath() {
        Path path = new Path("4F 3R L");

        assertEquals("FFFF RRR L", path.getCanonicalForm());
    }


    @Test
    void expandedPath2() {
        Path path = new Path("10F 11R");

        assertEquals("FFFFFFFFFF RRRRRRRRRRR", path.getCanonicalForm());
    }

    @Test
    void getBFS() throws Exception {
        BreadthFirstSearchSolver bfs = new BreadthFirstSearchSolver();
        String filePath = "/Users/kevinkim/a3-maze-runner-take-two-kevinnkimm/examples/straight.maz.txt";
        Maze maze = new Maze(filePath);
        String expectedPath = "4F";
        Path actualPath = bfs.solve(maze);
        String actualPathStr = actualPath.getFactorizedForm();
        assertEquals(expectedPath, actualPathStr);
    }

    @Test
    void getTinyBFS() throws Exception {
        BreadthFirstSearchSolver bfs = new BreadthFirstSearchSolver();
        String filePath = "/Users/kevinkim/a3-maze-runner-take-two-kevinnkimm/examples/tiny.maz.txt";
        Maze maze = new Maze(filePath);
        String expectedPath = "3F L 4F R 3F";
        Path actualPath = bfs.solve(maze);
        String actualPathStr = actualPath.getFactorizedForm();
        assertEquals(expectedPath, actualPathStr);
    }

    // @Test
    // void smallMazeBFS() throws Exception {
    //     BreadthFirstSearchSolver bfs = new BreadthFirstSearchSolver();
    //     String filePath = "/Users/kevinkim/a3-maze-runner-take-two-kevinnkimm/examples/small.maz.txt";
    //     Maze maze = new Maze(filePath);
    //     String expectedPath = "F L F R 2F L 6F R 4F R 2F L 2F R 2F L F";
    //     Path actualPath = bfs.solve(maze);
    //     String actualPathStr = actualPath.getFactorizedForm();
    //     assertEquals(expectedPath, actualPathStr);
    // }
}