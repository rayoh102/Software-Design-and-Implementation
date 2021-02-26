package graph.junitTests;


import java.util.HashSet;
import java.util.Set;
import graph.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * This class has test cases that test the implementation of the Graph class
 * @Rule public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested
 */
public class GraphTest {
    private static final int TIMEOUT = 2000;

    private final String NODE_A = "a";
    private final String NODE_B = "b";
    private final String EDGE_AA = "AA";
    private final String EDGE_AB = "AB";
    private final String EDGE_AB2 = "AB2";
    private final String EDGE_BA = "BA";
    private final String EDGE_BB = "BB";

    private Graph<String, String> graph;
    private Set<String> nodes;
    private Set<labelEdge<String, String>> edges;

    @Before
    public void setUp() throws Exception {
        graph = new Graph<>();
        nodes = new HashSet<String>();
        edges = new HashSet<labelEdge<String, String>>();
    }


    @Test(timeout = TIMEOUT)
    public void graphGetNodesTest() {
        assertEquals(nodes, graph.getNodes());
    }

    @Test(timeout = TIMEOUT)
    public void graphToStringTest() {
        assertEquals("{}", graph.toString());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addNullNodeTest() {
        graph.addNode(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void containsNullNodeTest() {
        graph.containsNode(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void getChildrenNullNodeTest() {
        graph.getChildren(null);
    }

    @Test(timeout = TIMEOUT)
    public void containsNodeWithoutAddingNodeTest() {
        assertFalse(graph.containsNode(NODE_A));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void getChildrenOfNodeWithoutAddingNodeTest() {
        graph.getChildren(NODE_A);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addEdgeWithFromToLabelNullTest() {
        graph.addEdge(null, null, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addEdgeWithToLabelNullTest() {
        graph.addEdge(NODE_A, null, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addEdgeWithLabelNullTest() {
        graph.addEdge(NODE_A, NODE_B, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void removeEdgeWithFromToLabelNullTest() {
        graph.removeEdge(null, null, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void removeEdgeWithToLabelNullTest() {
        graph.removeEdge(NODE_A, null, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void removeEdgeWithLabelNullTest() {
        graph.removeEdge(NODE_A, NODE_B, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void containsEdgeWithFromToLabelNullTest() {
        graph.containsEdge(null, null, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void containsEdgeWithToLabelNullTest() {
        graph.containsEdge(NODE_A, null, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void containsEdgeWithLabelNullTest() {
        graph.containsEdge(NODE_A, NODE_B, null);
    }

    @Test(timeout = TIMEOUT)
    public void addOneNodeTest() {
        assertTrue(graph.addNode(NODE_A));
    }



    @Test(timeout = TIMEOUT)
    public void checkToStringAfterAddOneNodeTest() {
        addOneNodeTest();
        assertEquals("{a=[]}", graph.toString());
    }

    @Test(timeout = TIMEOUT)
    public void checkContainsNodeOnNodeAAfterAddNodeATest() {
        addOneNodeTest();
        assertTrue(graph.containsNode("a"));
    }

    @Test(timeout = TIMEOUT)
    public void checkContainsNodeOnNodeBAfterAddNodeATest() {
        addOneNodeTest();
        assertFalse(graph.containsNode(NODE_B));
    }

    @Test(timeout = TIMEOUT)
    public void checkGetChildrenNodeAWithoutAddEdgeTest() {
        addOneNodeTest();
        assertTrue(graph.getChildren(NODE_A).isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void checkGetNodesAfterAddOneNodeTest() {
        addOneNodeTest();
        nodes.add("a");
        assertEquals(nodes, graph.getNodes());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addEdgeABWithoutAddingNodeBTest() {
        addOneNodeTest();
        graph.addEdge(NODE_A, NODE_B, EDGE_AB);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addEdgeBAWithoutAddingNodeBTest() {
        addOneNodeTest();
        graph.addEdge(NODE_B, NODE_A, EDGE_BA);
    }


    @Test(timeout = TIMEOUT)
    public void addSameNodeTwiceTest() {
        addOneNodeTest();
        assertFalse(graph.addNode(NODE_A));
    }


    @Test(timeout = TIMEOUT)
    public void toStringAfterAddSameNodeTwiceTest() {
        addSameNodeTwiceTest();
        assertEquals("{a=[]}", graph.toString());
    }

    @Test(timeout = TIMEOUT)
    public void addTwoDiffNodesTest() {
        addOneNodeTest();
        assertTrue(graph.addNode("b"));
    }

    @Test(timeout = TIMEOUT)
    public void getNodesAfterAddTwoDiffNodesTest() {
        addTwoDiffNodesTest();
        nodes.add(NODE_A);
        nodes.add(NODE_B);
        assertEquals(nodes, graph.getNodes());
    }


    @Test(timeout = TIMEOUT)
    public void addSameEdgeOnNodeATest() {
        addOneNodeTest();
        assertTrue(graph.addEdge(NODE_A, NODE_A, EDGE_AA));
    }

    @Test(timeout = TIMEOUT)
    public void getChildrenNodeAAfterAddSameEdgeOnNodeATest() {
        addSameEdgeOnNodeATest();
        edges.add(new labelEdge<>("a", "a", "AA"));
        assertEquals(edges, graph.getChildren(NODE_A));
    }

    @Test(timeout = TIMEOUT)
    public void toStringAfterAddSameEdgeOnNodeATest() {
        addSameEdgeOnNodeATest();
        assertEquals("{a=[a(AA)]}", graph.toString());
    }


    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void removeSameEdgeOnNodeAWithNullEdgeTest() {
        addSameEdgeOnNodeATest();
        graph.removeEdge(NODE_A, NODE_B, EDGE_AA);
    }


    @Test(timeout = TIMEOUT)
    public void getChildrenAfterRemoveSameEdgeOnNodeATest() {
        addSameEdgeOnNodeATest();
        assertEquals(edges, graph.getChildren(NODE_A));
    }

    @Test(timeout = TIMEOUT)
    public void toStringAfterRemoveSameEdgeOnNodeATest() {
        addSameEdgeOnNodeATest();
        assertEquals("{a=[]}", graph.toString());
    }

    @Test(timeout = TIMEOUT)
    public void addOneEdgeBetweenTwoNodesTest() {
        addSameEdgeOnNodeATest();
        assertTrue(graph.addEdge(NODE_A, NODE_B, EDGE_AB));
    }

    @Test(timeout = TIMEOUT)
    public void getChildrenNodeAAfterAddOneEdgeBetweenNodeAAndBTest() {
        addOneEdgeBetweenTwoNodesTest();
        edges.add(new labelEdge<>("a", "b", "AB"));
        assertEquals(edges, graph.getChildren(NODE_A));
    }

    @Test(timeout = TIMEOUT)
    public void getChildrenNodeBAfterAddOneEdgeBetweenNodeAAndBTest() {
        addOneEdgeBetweenTwoNodesTest();
        assertEquals(edges, graph.getChildren(NODE_B));
    }

    @Test(timeout = TIMEOUT)
    public void addSameEdgeAfterAddOneEdgeBetweenTwoNodesTest() {
        addOneEdgeBetweenTwoNodesTest();
        assertFalse(graph.addEdge(NODE_A, NODE_B, EDGE_AB));
    }

    @Test(timeout = TIMEOUT)
    public void getChildrenNodeAAfterAddingSameEdgeTest() {
        addSameEdgeAfterAddOneEdgeBetweenTwoNodesTest();
        edges.add(new labelEdge<>("a", "b", "AB"));
        assertEquals(edges, graph.getChildren(NODE_A));
    }

    @Test(timeout = TIMEOUT)
    public void getChildrenNodeBAfterAddSameEdgeTest() {
        getChildrenNodeAAfterAddingSameEdgeTest();
        assertEquals(new HashSet<labelEdge>(),
                graph.getChildren(NODE_B));
    }

    @Test(timeout = TIMEOUT)
    public void addTwoInverseDirectionsEdgesBetweenNodeAAndBTest() {
        addOneEdgeBetweenTwoNodesTest();
        assertTrue(graph.addEdge(NODE_B, NODE_A, EDGE_BA));
    }

    @Test(timeout = TIMEOUT)
    public void getChildrenNodeAAfterAddTwoInverseDirectionsEdgesTest() {
        addTwoInverseDirectionsEdgesBetweenNodeAAndBTest();
        edges.add(new labelEdge<>("a", "b", "AB"));
        assertEquals(edges, graph.getChildren(NODE_A));
    }

    @Test(timeout = TIMEOUT)
    public void getChildrenNodeBAfterAddTwoInverseDirectionsEdgesTest() {
        addTwoInverseDirectionsEdgesBetweenNodeAAndBTest();
        edges.add(new labelEdge<>("b", "a", "BA"));
        assertEquals(edges, graph.getChildren(NODE_B));
    }

    @Test(timeout = TIMEOUT)
    public void makeCompleteTwoNodeGraphTest() {
        addSameEdgeOnNodeATest();
        assertTrue(graph.addNode(NODE_B));
        assertTrue(graph.addEdge(NODE_A, NODE_B, EDGE_AB));
        assertTrue(graph.addEdge(NODE_B, NODE_A, EDGE_BA));
        assertTrue(graph.addEdge(NODE_B, NODE_B, EDGE_BB));
    }

    @Test(timeout = TIMEOUT)
    public void getChildrenOfNodeAAfterMakeCompleteTwoNodeGraphTest() {
        makeCompleteTwoNodeGraphTest();
        edges.add(new labelEdge<>("a", "a", "AA"));
        edges.add(new labelEdge<>("a", "b", "AB"));
        assertEquals(edges, graph.getChildren(NODE_A));
    }

    @Test(timeout = TIMEOUT)
    public void getChildrenOfNodeBAftermakeCompleteTwoNodeGraphTest() {
        makeCompleteTwoNodeGraphTest();
        edges.add(new labelEdge<>("b", "a", "BA"));
        edges.add(new labelEdge<>("b", "b", "BB"));
        assertEquals(edges, graph.getChildren(NODE_B));
    }



}