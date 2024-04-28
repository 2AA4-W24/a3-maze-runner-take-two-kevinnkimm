package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        String filePath = "examples/straight.maz.txt";
        Maze maze = new Maze(filePath);
        String expectedPath = "4F";
        Path actualPath = bfs.solve(maze);
        String actualPathStr = actualPath.getFactorizedForm();
        assertEquals(expectedPath, actualPathStr);
    }

    @Test
    void getTinyBFS() throws Exception {
        BreadthFirstSearchSolver bfs = new BreadthFirstSearchSolver();
        String filePath = "examples/tiny.maz.txt";
        Maze maze = new Maze(filePath);
        String expectedPath = "3F L 4F R 3F";
        Path actualPath = bfs.solve(maze);
        String actualPathStr = actualPath.getFactorizedForm();
        assertEquals(expectedPath, actualPathStr);
    }

    @Test
    void directMazeBFS() throws Exception {
        BreadthFirstSearchSolver bfs = new BreadthFirstSearchSolver();
        String filePath = "examples/direct.maz.txt";
        Maze maze = new Maze(filePath);
        String expectedPath = "F R 2F L 3F R F L F R F L 2F";
        Path actualPath = bfs.solve(maze);
        String actualPathStr = actualPath.getFactorizedForm();
        assertEquals(expectedPath, actualPathStr);
    }

    @Test
    void hugeMazeBFS() throws Exception {
        BreadthFirstSearchSolver bfs = new BreadthFirstSearchSolver();
        String filePath = "examples/huge.maz.txt";
        Maze maze = new Maze(filePath);
        String expectedPath = "F L 7F R 2F L 4F R 4F L 2F R 6F R 2F L 6F R 4F L 2F R 2F L 10F L 2F R 2F R 2F L 4F R 10F L 2F R 4F L 6F R 2F L 2F R 4F L 2F R 6F L 2F R 2F L 2F R 2F L 2F R 4F L 4F R 2F L 4F R 4F R 2F L 4F L 4F R 2F L 6F R 2F L 2F R 6F L 4F R 2F L 4F R 2F L 2F R 2F L 2F R 4F L 6F R 4F L 4F R 6F L 4F R F L F";
        Path actualPath = bfs.solve(maze);
        String actualPathStr = actualPath.getFactorizedForm();
        assertEquals(expectedPath, actualPathStr);
    }

    @Test
    void largeMazeBFS() throws Exception {
        BreadthFirstSearchSolver bfs = new BreadthFirstSearchSolver();
        String filePath = "examples/large.maz.txt";
        Maze maze = new Maze(filePath);
        String expectedPath = "15F R 2F L 8F R 2F L 4F R 2F L 4F R 4F L 6F R 2F L 2F R 2F L 2F R 2F L 2F R 2F L 2F R 2F L 2F R 6F L 2F L 2F R F";
        Path actualPath = bfs.solve(maze);
        String actualPathStr = actualPath.getFactorizedForm();
        assertEquals(expectedPath, actualPathStr);
    }
    // tests whether map is generated with the correct dimensions
    @Test
    void mapTest() throws Exception {
        String filePath = "/Users/kevinkim/a3-maze-runner-take-two-kevinnkimm/examples/straight.maz.txt";
        Maze maze = new Maze(filePath);
        assertEquals(5, maze.getSizeX());
        assertEquals(5, maze.getSizeY());
    }
    // should not return anything but an exception
    @Test
    void falseMapTest() throws Exception {
        assertThrows(Exception.class, () -> {
            Maze maze = new Maze("/Users/kevinkim/a3-maze-runner-take-two-kevinnkimm/examples/false.maz.txt");
            System.out.println(maze.getEnd());
        });
    }

    // tests the validity of path
    @Test
    void validatePath() throws Exception {
        String filePath = "/Users/kevinkim/a3-maze-runner-take-two-kevinnkimm/examples/straight.maz.txt";
        Maze maze = new Maze(filePath);
        Path validPath = new Path("4F");
        Path invalidPath = new Path("3F");
        assertTrue(maze.validatePath(validPath));
        assertFalse(maze.validatePath(invalidPath));
    }
    
    // tests the position and direction of the node created
    @Test
    void nodeCreation() {
        Position position = new Position(4, 12);
        Node node = new Node(Direction.UP, position);

        assertEquals(Direction.UP, node.getDirection());
        assertEquals(position, node.getPosition());
    }
    // tests the movement 
    @Test
    void testPositionMovement() {
        Position startPos = new Position(3, 5);

        Position movedUp = startPos.move(Direction.UP);
        assertEquals(3, movedUp.getColumn());
        assertEquals(4, movedUp.getRow());

        Position movedRight = startPos.move(Direction.RIGHT);
        assertEquals(4, movedRight.getColumn());
        assertEquals(5, movedRight.getRow());

        Position movedDown = startPos.move(Direction.DOWN);
        assertEquals(3, movedDown.getColumn());
        assertEquals(6, movedDown.getRow());

        Position movedLeft = startPos.move(Direction.LEFT);
        assertEquals(2, movedLeft.getColumn());
        assertEquals(5, movedLeft.getRow());
    }

    @Test
    void boundaryTest() throws Exception {
        BreadthFirstSearchSolver bfs = new BreadthFirstSearchSolver();
        String filePath = "/Users/kevinkim/a3-maze-runner-take-two-kevinnkimm/examples/boundary.maz.txt";
        Maze maze = new Maze(filePath);
        String expectedPath = "5F L F R 2F";
        Path actualPath = bfs.solve(maze);
        String actualPathStr = actualPath.getFactorizedForm();
        assertEquals(expectedPath, actualPathStr);
    }




}