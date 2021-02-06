package graph.junitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import graph.*;

/**
 * This class contains test cases that test the implementation of labelEdge class
 */

public class labelEdgeTest {
    private static final int TIMEOUT = 2000;
    private labelEdge<String, String> lEdge;

    @Before
    public void setUp() throws Exception {
        lEdge = new labelEdge<String, String>("b", "AB");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void makeEdgeWithNullDestinationTest() {
        new labelEdge<String, String>(null, "AB");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void makeEdgeWithNullLabelTest() {
        new labelEdge<String, String>("b", null);
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
        assertFalse(lEdge.equals(new labelEdge<String, String>("a", "AB")));
    }

    @Test(timeout = TIMEOUT)
    public void equalSameLabelEdgeTest() {
        assertTrue(lEdge.equals(new labelEdge<String, String>("b", "AB")));
    }

    @Test(timeout = TIMEOUT)
    public void hashCodeForSameLabelEdgeTest() {
        int hc = "b".hashCode() + "AB".hashCode();
        assertEquals(hc, lEdge.hashCode());
    }

    @Test(timeout = TIMEOUT)
    public void compareToLexicographicallyGreaterDestinationTest() {
        assertTrue(lEdge.compareTo(new labelEdge<String, String>("c", "AB")) < 0);
    }

    @Test(timeout = TIMEOUT)
    public void compareToLexicographicallyLessDestinationTest() {
        assertTrue(lEdge.compareTo(new labelEdge<String, String>("a", "AB")) > 0);
    }

    @Test(timeout = TIMEOUT)
    public void compareToLexicographicallyGreaterLabelTest() {
        assertTrue(lEdge.compareTo(new labelEdge<String, String>("b", "BB")) < 0);
    }

    @Test(timeout = TIMEOUT)
    public void compareToLexicographicallyLessLabelTest() {
        assertTrue(lEdge.compareTo(new labelEdge<String, String>("b", "AA")) > 0);
    }

    @Test(timeout = TIMEOUT)
    public void compareToSameLabelEdgeTest() {
        assertTrue(lEdge.compareTo(new labelEdge<String, String>("b", "AB")) == 0);
    }
}
