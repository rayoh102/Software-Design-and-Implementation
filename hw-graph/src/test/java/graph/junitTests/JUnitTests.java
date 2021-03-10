package graph.junitTests;
import graph.*;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class JUnitTests {
    private static final int TIMEOUT = 100000;

    @Test(timeout = TIMEOUT)
    public void testAddSameNode() {
        // Tests whether Graph<String, String> contains node
        Graph<String, String> graph1 = new Graph<>();
        graph1.addNode("n1");
        assertTrue(graph1.containsNode("n1"));
    }

    @Test(timeout = TIMEOUT)
    public void testContainsNonExistentNode() {
        // Tests to confirm a nonexistent node isn't in graph
        Graph<String, String> graph1 = new Graph<>();
        graph1.addNode("n1");
        assertFalse(graph1.containsNode("n2"));
    }

    @Test(timeout = TIMEOUT)
    public void testContainsNonExistentEdge() {
        // Tests to confirm a nonexistent Edge<String, String> isn't in graph
        Graph<String, String> graph1 = new Graph<>();
        graph1.addNode("n1");
        graph1.addNode("n2");
        assertFalse(graph1.containsEdge("n1", "n2", "e1"));
    }

    @Test(timeout = TIMEOUT)
    public void testAddMultipleNodes() {
        // Tests if we can add multiple nodes
        Graph<String, String> graph1 = new Graph<>();
        graph1.addNode("n1");
        assertTrue(graph1.containsNode("n1"));
        graph1.addNode("n2");
        assertTrue(graph1.containsNode("n2"));
    }

    @Test(timeout = TIMEOUT)
    public void testAddSimpleEdge() {
        // Tests if we can add multiple nodes
        Graph<String, String> graph1 = new Graph<>();
        graph1.addNode("n1");
        graph1.addNode("n2");
        graph1.addEdge("n1", "n2", "e1");
        assertTrue(graph1.containsEdge("n1","n2", "e1"));
    }


    @Test(timeout = TIMEOUT)
    public void testAddDoubleDirectionEdge() {
        // Tests adding two Edges between two nodes
        Graph<String, String> graph1 = new Graph<>();
        graph1.addNode("n1");
        graph1.addNode("n2");
        graph1.addEdge("n1", "n2", "e1");
        graph1.addEdge("n2", "n1", "e1");
        assertTrue(graph1.containsEdge("n1","n2", "e1"));
        assertTrue(graph1.containsEdge("n2","n1", "e1"));
    }

    @Test(timeout = TIMEOUT)
    public void testAddSameDirectionEdgeDiffLabel() {
        // Tests adding two Edges same direction different label
        Graph<String, String> graph1 = new Graph<>();
        graph1.addNode("n1");
        graph1.addNode("n2");
        graph1.addEdge("n1", "n2", "e1");
        graph1.addEdge("n1", "n2", "e2");
        assertTrue(graph1.containsEdge("n1","n2", "e1"));
        assertTrue(graph1.containsEdge("n1","n2", "e2"));
    }

    @Test(timeout = TIMEOUT)
    public void testAddReflexiveEdge() {
        // Tests adding two Edges between two nodes
        Graph<String, String> graph1 = new Graph<>();
        graph1.addNode("n1");
        graph1.addEdge("n1", "n1", "e1");
        assertTrue(graph1.containsEdge("n1","n1", "e1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullAddNode() {
        // Tests to make sure exception is thrown when we pass in null to addNode
        Graph<String, String> graph1 = new Graph<>();
        graph1.addNode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullAddEdge() {
        // Tests to make sure exception is thrown when we pass in null to addEdge
        Graph<String, String> graph1 = new Graph<>();
        graph1.addEdge(null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAddEdge() {
        // Tests to make sure exception is thrown when we pass invalid Edge<String, String> to addEdge
        Graph<String, String> graph1 = new Graph<>();
        graph1.addEdge("n1", "n2", "e1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullRemoveNode() {
        // Tests to make sure exception is thrown when we pass in null to addNode
        Graph<String, String> graph1 = new Graph<>();
        graph1.addNode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRemoveNull() {
        // Tests to make sure exception is thrown when we pass invalid Edge<String, String> to removeNode
        Graph<String, String> graph1 = new Graph<>();
        graph1.removeNode("n1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullRemoveEdge() {
        // Tests to make sure exception is thrown when we pass in null to addEdge
        Graph<String, String> graph1 = new Graph<>();
        graph1.removeEdge(null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRemoveEdge() {
        // Tests to make sure exception is thrown when we pass invalid Edge<String, String> to removeEdge
        Graph<String, String> graph1 = new Graph<>();
        graph1.removeEdge("n1", "n2", "e1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullContainsNode() {
        // Tests to make sure exception is thrown when we pass in null to containsNode
        Graph<String, String> graph1 = new Graph<>();
        graph1.containsNode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullContainsEdge() {
        // Tests to make sure exception is thrown when we pass in null to containsEdge
        Graph<String, String> graph1 = new Graph<>();
        graph1.containsEdge(null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidContainsEdge() {
        // Tests to make sure exception is thrown when we pass invalid Edge<String, String> to containsEdge
        Graph<String, String> graph1 = new Graph<>();
        graph1.containsEdge("n1", "n2", "e1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullFindChildren() {
        // Tests to make sure exception is thrown when we pass in null to findChildren
        Graph<String, String> graph1 = new Graph<>();
        graph1.getChildren(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFindChildren() {
        // Tests to make sure exception is thrown when we pass invalid node to findChildren
        Graph<String, String> graph1 = new Graph<>();
        graph1.getChildren("n1");
    }

    @Test(timeout = TIMEOUT)
    public void testFindChildren() {
        // Tests to make sure we can find children of a node
        Graph<String, String> graph1 = new Graph<>();
        graph1.addNode("n1");
        graph1.addNode("n2");
        graph1.addEdge("n1", "n1", "e1");
        graph1.addEdge("n1", "n2", "e2");
        Set<String> children = new HashSet<>();
        children.add("n1");
        children.add("n2");
        assertEquals(children, graph1.getChildren("n1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetEdgesNull() {
        // Tests to make sure exception is thrown when we pass in null to getEdges
        Graph<String, String> graph1 = new Graph<>();
        graph1.getEdges(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetEdgesInvalid() {
        // Tests to make sure exception is thrown when we pass in null to getEdges
        Graph<String, String> graph1 = new Graph<>();
        graph1.getEdges("n1");
    }

    @Test(timeout = TIMEOUT)
    public void testGetEdges() {
        // Tests to make sure we get the right set of Edges for a node in our graph
        Graph<String, String> graph1 = new Graph<>();
        graph1.addNode("n1");
        graph1.addNode("n2");
        graph1.addEdge("n1", "n2", "e1");
        Set<labelEdge> Edges = new HashSet<>();
        labelEdge<String, String> e1 = new labelEdge<>("n1", "n2", "e1");
        for(labelEdge<String, String> e : graph1.getEdges("n1")) {
            assertTrue(e.equals(e1));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testGetNodes() {
        // Tests to make sure we get the right set of nodes in our graph
        Graph<String, String> graph1 = new Graph<>();
        graph1.addNode("n1");
        graph1.addNode("n2");
        Set<String> nodes = new HashSet<>();
        nodes.add("n1");
        nodes.add("n2");
        assertEquals(nodes, graph1.getNodes());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveNode() {
        // Tests to make sure a node was appropriately removed
        Graph<String, String> graph1 = new Graph<>();
        graph1.addNode("n1");
        assertTrue(graph1.containsNode("n1"));
        graph1.removeNode("n1");
        assertFalse(graph1.containsNode("n1"));
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveEdge() {
        // Tests to make sure an Edge<String, String> was appropriately removed
        Graph<String, String> graph1 = new Graph<>();
        graph1.addNode("n1");
        graph1.addNode("n2");
        graph1.addEdge("n1", "n2", "e1");
        assertTrue(graph1.containsEdge("n1","n2", "e1"));
        graph1.removeEdge("n1", "n2", "e1");
        assertFalse(graph1.containsEdge("n1", "n2", "e1"));
    }

    @Test(timeout = TIMEOUT)
    public void testAddRemoveNodesAndEdges() {
        // Tests to make sure we can combine multiple adds/removes appropriately
        Graph<String, String> graph1 = new Graph<>();
        graph1.addNode("n1");
        graph1.addNode("n2");
        assertTrue(graph1.containsNode("n1"));
        assertTrue(graph1.containsNode("n2"));
        graph1.addEdge("n1", "n2", "e1");
        assertTrue(graph1.containsEdge("n1", "n2", "e1"));
        graph1.removeEdge("n1", "n2", "e1");
        assertFalse(graph1.containsEdge("n1", "n2", "e1"));
        graph1.removeNode("n2");
        assertFalse(graph1.containsNode("n2"));
        graph1.addNode("n3");
        assertTrue(graph1.containsNode("n3"));
        graph1.addEdge("n1", "n3", "e3");
        assertTrue(graph1.containsEdge("n1", "n3", "e3"));
    }

    @Test(timeout = TIMEOUT)
    public void testEqualEdges() {
        // Tests to make sure two identical Edges are considered equal and vice versa
        labelEdge<String, String> e1 = new labelEdge<>("n1", "n2", "e1");
        labelEdge<String, String> e2 = new labelEdge<>("n1", "n2", "e1");
        labelEdge<String, String> e3 = new labelEdge<>("n1", "n2", "e2");
        assertTrue(e1.equals(e2));
        assertFalse(e1.equals(e3));
    }

    @Test(timeout = TIMEOUT)
    public void testHashCode() {
        // Tests to make sure hashcode of two identical Edges is the same
        labelEdge<String, String> e1 = new labelEdge<>("n1", "n2", "e1");
        labelEdge<String, String> e2 = new labelEdge<>("n1", "n2", "e1");
        assertEquals(e1.hashCode(), e2.hashCode());
    }
}


