package graph;

import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.Set;

/**
 * <b>Graph</b> represents a mutable, directed, labeled graph.
 * <p>
 * @spec.specfield nodes : Set
 * @spec.specfield outgoingEdges : Collection
 */

public class Graph  {
    // Rep invariant:
    //     Graph != null
    //     Every node and every edge in Graph aren't null and Graph must contain node n if that node is
    //     included in any edge

    // Abstract function:
    //     AF(this) = directed graph g such that
    //     if a is a node, then a=[], ...} if a has no outgoing edges
    //     {a=[b(e), c(f), ...], b=[...], c=[...]} o.w.
    //     where letters outside the parentheses - b, c, etc. - are destinations of node a's edges and the letters
    //     inside the parentheses - e, f, etc. - are the edge's labels


    // checkRep variable
    private final static boolean check = false;

    // Graph
    private final Map<String, TreeSet<labelEdge>> Graph;


    /**
     * Creates an empty graph.
     *
     * @spec.effects constructs an empty graph
     */
    public Graph() {
        Graph = new TreeMap<String, TreeSet<labelEdge>>();
        checkRep();
    }

    /**
     * If its not already present, adds the node n to the graph
     *
     * @param n, the node to be added
     * @spec.modifies this.nodes
     * @spec.effects adds node n to this.nodes if not already present
     * @return true if this graph did not already contain node n
     */
    public boolean addNode(String n) {
        checkRep();

        if (n == null)
            throw new IllegalArgumentException();

        boolean addedNode = false;

        if (!(Graph.containsKey(n))) {
            Graph.put(n, new TreeSet<labelEdge>());
            addedNode = true;
        }

        checkRep();
        return addedNode;
    }

    /**
     * If both the nodes "start" and "end" already exists in graph and the edge from "start"
     * to "end" with the label "label" isn't already within the graph, it will add the edge
     * from "start" to "end" with the label "label"
     *
     * @param start, the edges starting point
     * @param end, the edges ending point
     * @param label, the edges label
     * @spec.requires start, end, label != null
     * @spec.modifies this.outgoingEdges
     * @spec.effects Adds the edge from "start" to "end" with the label "label" to the graph
     * if the same edge isn't already in the graph
     * @throws IllegalArgumentException if either the node "start" or "end" isn't within this.nodes
     * @return true if the graph didn't already contain the edge from "start" to "end"
     * with label "label"
     */
    public boolean addEdge(String start, String end, String label) {
        checkRep();

        //throws null exception of the nodes or label is null
        if (start == null || end == null || label == null)
            throw new IllegalArgumentException();

        // checks if the nodes are in the graph
        if (!(Graph.containsKey(start)))
            throw new IllegalArgumentException();

        if (!(Graph.containsKey(end)))
            throw new IllegalArgumentException();

        boolean addedEdge = false;

        TreeSet<labelEdge> startingNodesEdges = Graph.get(start);
        labelEdge e = new labelEdge(end, label);


        // check if the edge already exists in the graph
        if (!(startingNodesEdges.contains(e))) {
            startingNodesEdges.add(e);
            addedEdge = true;
        }

        checkRep();
        return addedEdge;
    }

    /**
     * Return true if the node n is present within the graph.
     *
     * @param n, the node
     * @spec.requires n != null
     * @return true if the node n is present within this.nodes
     */
    public boolean containsNode(String n) {
        checkRep();

        if (n == null)
            throw new IllegalArgumentException();

        checkRep();
        return Graph.containsKey(n);
    }

    /**
     * Return the set of nodes.
     *
     * @return the set of nodes
     */
    public Set<String> getNodes() {
        checkRep();
        return new TreeSet<String>(Graph.keySet());
    }

    /**
     * Returns the number of nodes within the graph
     *
     * @return the number of nodes within the graph
     */
    public int size() {
        checkRep();
        return Graph.size();
    }

    /**
     * Returns true if graph is empty
     *
     * @return true if the graph is empty
     */
    public boolean isEmpty() {
        checkRep();
        return Graph.isEmpty();
    }

    /**
     * Returns a set of outgoing edges of the node n.
     *
     * @param n, the node
     * @spec.requires n != null
     * @return a set of outgoing edges of the node n
     * @throws IllegalArgumentException if the node n isn't present in this.nodes
     */
    public Set<labelEdge> getChildren(String n) {
        checkRep();

        //throws IllegalArgumentException if node is null
        if (n == null)
            throw new IllegalArgumentException();

        //throws IllegalArgumentException if node doesn't exist in the graph
        if (!(containsNode(n)))
            throw new IllegalArgumentException();

        TreeSet<labelEdge> edgesOfN = Graph.get(n);
        checkRep();
        return new TreeSet<labelEdge>(edgesOfN);
    }

    /**
     * Returns the number of edges from two nodes.
     *
     * @param node1 the edges starting point
     * @param node2 the edges destination
     * @spec.requires node1, node2 != null
     * @throws IllegalArgumentException if either node "node1" or "node2" isn't present in this.nodes
     * @return number of edges from "node1" to "node2"
     */
    public int numberOfEdges(String node1, String node2) {
        checkRep();

        //throws IllegalArgumentException if node is null
        if (node1 == null || node2 == null)
            throw new IllegalArgumentException();

        // check if nodes exist in the graph
        if (!(Graph.containsKey(node1)))
            throw new IllegalArgumentException();

        if (!(Graph.containsKey(node2)))
            throw new IllegalArgumentException();

        Set<labelEdge> children = Graph.get(node1);
        int numEdges = 0;
        for (labelEdge e : children) {
            if (e.getDest().equals(node2))
                numEdges++;
        }

        checkRep();
        return numEdges;
    }

    /**
     * Removes an edge from "start" to "end"
     * with the label "label" from the graph and returns
     * the edge. Returns null if the edge does not exist.
     *
     * @param start, the origin of the edge
     * @param end, the destination of the edge
     * @param label, the label of the edge
     * @spec.requires start, end, label != null
     * @spec.modifies this.outgoingEdges
     * @spec.effects removes the edge from this.outgoingEdges
     * @throws IllegalArgumentException if either nodes "start" or "end" isn't present in this.nodes
     * @return either the edge removed from the graph or null if the edge doesn't exist
     */
    public labelEdge removeEdge(String start, String end, String label) {
        checkRep();

        //throws IllegalArgumentException if the nodes or labels are null
        if (start == null || end == null || label == null)
            throw new IllegalArgumentException();

        // check if nodes exist in the graph
        if (!(Graph.containsKey(start)))
            throw new IllegalArgumentException();

        if (!(Graph.containsKey(end)))
            throw new IllegalArgumentException();

        TreeSet<labelEdge> startingNodesEdges = Graph.get(start);
        labelEdge e = new labelEdge(end, label);

        // check if the edge exists in the graph
        if (!(startingNodesEdges.contains(e)))
            return null;

        startingNodesEdges.remove(e);
        checkRep();
        return e;
    }



    /**
     * Returns the string representation of the graph.
     *
     * @return string representation of the graph
     */
    public java.lang.String toString() {
        checkRep();
        return Graph.toString();
    }

    /**
     * Checks if rep invariant holds.
     */
    private void checkRep(){
        if (check) {
            // check if the graph is null
            if (Graph == null)
                throw new RuntimeException();

            Set<String> nodes = Graph.keySet();

            // check if any nodes are null
            for (String n : nodes) {
                if (n == null)
                    throw new RuntimeException();

                TreeSet<labelEdge> nodesEdges = Graph.get(n);
                // check if any edges are null
                for (labelEdge le : nodesEdges) {
                    if (le == null)
                        throw new RuntimeException();

                    // check if any destination nodes don't exist
                    if (!(Graph.containsKey(le.getDest())))
                        throw new RuntimeException();
                }
            }
        }
    }

}