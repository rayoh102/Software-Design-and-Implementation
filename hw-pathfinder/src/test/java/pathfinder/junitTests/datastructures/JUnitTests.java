package pathfinder.junitTests.datastructures;

import pathfinder.DijkstrasAlgorithm;
import org.junit.Test;

public class JUnitTests {

    @Test(expected = IllegalArgumentException.class)
    public void testMinimumCostPathInvalid() {
        // Tests to make sure exception is thrown when we pass in null to searchPath
        DijkstrasAlgorithm shortestPath = new DijkstrasAlgorithm();
        shortestPath.minimumCostPath(null, null, null);
    }
}
