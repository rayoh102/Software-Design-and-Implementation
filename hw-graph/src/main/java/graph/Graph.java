package graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <b>Graph</b> represents a mutable, directed, labeled graph.
 * <p>
 * @spec.specfield nodes : Set
 * @spec.specfield outgoingEdges : Collection
 */

public class Graph <T, E extends Comparable<E>>  {

    // Graph
    private final Map<T, HashSet<labelEdge<T, E>>> Graph;


    /**
     * Creates an empty graph.
     *
     * @spec.effects constructs an empty graph
     */
    public Graph() {
        Graph = new HashMap<T, HashSet<labelEdge<T, E>>>();
    }

    /**
     * If its not already present, adds the node <var>n</var> to the graph
     *
     * @param n, the node to be added
     * @spec.modifies this.nodes
     * @spec.effects adds node <var>n</var> to this.nodes if not already present
     * @return true if this graph did not already contain node <var>n</var>
     */
    public boolean addNode(T n) {
        throw new RuntimeException();
    }

    /**
     * If both the nodes <var>from</var> and <var>to</var> already exists in graph and the edge from <var>from</var>
     * to <var>to</var> with the label <var>label</var> isn't already within the graph, it will add the edge
     * from <var>from</var> to <var>to</var> with the label <var>label</var>
     *
     * @param from, the edges starting point
     * @param to, the edges destination
     * @param label, the edges label
     * @spec.requires from, to, label != null
     * @spec.modifies this.outgoingEdges
     * @spec.effects Adds the edge from <var>from</var> to <var>to</var> with label the <var>label</var> to the graph
     * if the same edge isn't already in the graph
     * @throws IllegalArgumentException if either the node <var>from</var> or <var>to</var> isn't within this.nodes
     * @return true if the graph didn't already contain the edge from<var>from</var> to <var>to</var>
     * with label <var>label</var>
     */
    public boolean addEdge(T from, T to, E label) {
        throw new RuntimeException();
    }

    /**
     * Return true if the node <var>n</var> is present within the graph.
     *
     * @param n, the node
     * @spec.requires n != null
     * @return true if the node <var>n</var> is present within this.nodes
     */
    /*@AssertNonNullIfTrue("dgraph.get(#1)")*/
    public boolean containsNode(T n) {
        throw new RuntimeException();
    }

    /**
     * Return the set of nodes.
     *
     * @return the set of nodes
     */
    public Set<T> getNodes() {
        throw new RuntimeException();
    }

    /**
     * Returns the number of nodes within the graph
     *
     * @return the number of nodes within the graph
     */
    public int size() {
        throw new RuntimeException();
    }

    /**
     * Returns true if graph is empty
     *
     * @return true if the graph is empty
     */
    public boolean isEmpty() {
        throw new RuntimeException();
    }

    /**
     * Returns a set of outgoing edges of the node <var>n</var>.
     *
     * @param n, the node
     * @spec.requires n != null
     * @return a set of outgoing edges of the node <var>n</var>
     * @throws IllegalArgumentException if the node <var>n</var> isn't present in this.nodes
     */
    public Set<labelEdge<T, E>> getChildren(T n) {
        throw new RuntimeException();
    }

    /**
     * Returns the number of edges from two nodes.
     *
     * @param node1 the edges starting point
     * @param node2 the edges destination
     * @spec.requires from, to != null
     * @throws IllegalArgumentException if either node <var>from</var> or <var>to</var> isn't present in this.nodes
     * @return number of edges from <var>node1</var> to <var>node2</var>
     */
    public int numberOfEdges(T node1, T node2) {
        throw new RuntimeException();
    }

    /**
     * Removes an edge from <var>from</var> to <var>to</var>
     * with the label <var>label</var> from the graph and returns
     * the edge. Returns null if the edge does not exist.
     *
     * @param from, the origin of the edge
     * @param to, the destination of the edge
     * @param label, the label of the edge
     * @spec.requires from, label != null
     * @spec.modifies this.outgoingEdges
     * @spec.effects removes the edge from this.outgoingEdges
     * @throws IllegalArgumentException if either nodes <var>from</var> or <var>to</var> isn't present in this.nodes
     * @return either the edge removed from the graph or null if the edge doesn't exist
     */
    public labelEdge<T, E> removeEdge(T from, T to, E label) {
        throw new RuntimeException();
    }

    /**
     * Returns the set view of the graph.
     *
     * @return the set view of the graph
     */
    public Set<Map.Entry<T, HashSet<labelEdge<T, E>>>> entrySet() {
        throw new RuntimeException();
    }

    /**
     * Returns the string representation of the graph.
     *
     * @return string representation of the graph
     */
    public String toString() {
        throw new RuntimeException();
    }

    /**
     * Checks if rep invariant holds.
     */
    private void checkRep(){
        throw new RuntimeException();
    }

}