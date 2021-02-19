package marvel.junitTests;

import marvel.MarvelPaths;
import org.junit.Test;

public class JUnitTests {
    @Test(expected = IllegalArgumentException.class)
    public void testCreateGraphInvalid() {
        // Tests to make sure exception is thrown when we pass in null to searchPath
        MarvelPaths paths = new MarvelPaths();
        paths.createGraph(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchPathInvalid() {
        // Tests to make sure exception is thrown when we pass in null to searchPath
        MarvelPaths paths = new MarvelPaths();
        paths.searchPath(null, null, null);
    }
}



