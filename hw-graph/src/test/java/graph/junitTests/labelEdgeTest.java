package graph.junitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import graph.*;

import org.junit.Rule;
import org.junit.rules.Timeout;

/**
 * This class contains test cases that test the implementation of labelEdge class
 * @Rule public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested
 */

public class labelEdgeTest {
    private static final int TIMEOUT = 2000;
    private labelEdge lEdge;

    @Before
    public void setUp() throws Exception {
        lEdge = new labelEdge("b", "AB");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void makeEdgeWithNullDestinationTest() {
        new labelEdge(null, "AB");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void makeEdgeWithNullLabelTest() {
        new labelEdge("b", null);
    }

    @Test(timeout = TIMEOUT)
    public void getDestinationTest() {
        assertEquals("b", lEdge.getDest());
    }

    @Test(timeout = TIMEOUT)
    public void getLabelTest() {
        assertEquals("AB", lEdge.getLabel());
    }

    @Test(timeout = TIMEOUT)
    public void toStringTest() {
        assertEquals("b(AB)", lEdge.toString());
    }

    @Test(timeout = TIMEOUT)
    public void equalsOnNonLabelEdgeObjectTest() {
        assertFalse(lEdge.equals("b(AB)"));
    }

    @Test(timeout = TIMEOUT)
    public void equalsOnhDiffLabelEdgeTest() {
        assertFalse(lEdge.equals(new labelEdge("a", "AB")));
    }

    @Test(timeout = TIMEOUT)
    public void equalSameLabelEdgeTest() {
        assertTrue(lEdge.equals(new labelEdge("b", "AB")));
    }

    @Test(timeout = TIMEOUT)
    public void hashCodeForSameLabelEdgeTest() {
        int hc = "b".hashCode() + "AB".hashCode();
        assertEquals(hc, lEdge.hashCode());
    }

}
