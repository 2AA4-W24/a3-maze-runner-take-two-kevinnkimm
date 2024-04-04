package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    void adjacencyListTest() {
        BreadthFirstSearchSolver hi = new BreadthFirstSearchSolver();
        List<List<Boolean>> grid = new ArrayList<>();
        grid.add(Arrays.asList(false, true, false)); // Example row 1
        grid.add(Arrays.asList(true, false, true)); // Example row 2
        grid.add(Arrays.asList(false, true, false)); // Example row 3

        hi.adjacencyList(grid);
    }
}