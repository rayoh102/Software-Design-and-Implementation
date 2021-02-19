package graph;

import java.util.*;

/**
 * <b>Graph</b> represents a mutable, directed, labeled graph.
 * <p>
 * @spec.specfield nodes : set // nodes in the graph
 * @spec.specfield set // edges in the graph
 */

public class Graph <E, T> {
    // Rep invariant:
    //     In Map graph we have a mapping from Node -> edges, where edges contains edges the node points to.
    //     graph != null && all Node in graph != null && all edges in graph != null && all e in edges != null

    // Abstract function:
    //     graph = {} for an empty graph
    //     Each node N in graph.keySet() is a node in the graph
    //     The set of edges each N maps to tells us the nodes that N connects to and the labels of these edges.
    //     For instance, say we have a node N0 whose eSet is {E1}, then there exists an edge E1 with startNode = N0.


    // checkRep variable
    private final static boolean check = false;

    // Graph
    private Map<E, Set<labelEdge<E, T>>> Graph;


    /**
     * Creates an empty graph.
     *
     * @spec.effects constructs an empty graph
     */
    public Graph() {
        Graph = new HashMap<>();
        checkRep();
    }



    /**
     * Adds the node passed in to the graph if it doesn't exist
     *
     * @spec.requires an identical node doesn't already exist
     * @param n - data in new node
     * @throws IllegalArgumentException if node is null
     * @spec.modifies graph
     * @spec.effects creates a new node and adds it to the graph
     */
    public void addNode(E n) {
        if(n == null) {
            throw new IllegalArgumentException("Node can't be null");
        }
        Graph.put(n, new HashSet<>());
        checkRep();
    }


    /**
     * Adds an edge between nodes to the graph if it doesn't already exist
     *
     * @spec.requires an edge e with e.startNode = startNode, e.endNode = endNode, and e.label = label doesn't
     * already exist in the graph
     * @param start, the edges starting point
     * @param end, the edges ending point
     * @param label, the edges label
     * @throws IllegalArgumentException if any parameter is null or a node doesn't exist in the graph
     * @spec.modifies graph
     * @spec.effects creates a new edge and adds it to the graph
     */
    public void addEdge(E start, E end, T label) {
        if(start == null || end == null || label == null) {
            throw new IllegalArgumentException("No input can be null");
        }
        if(!containsNode(start)) {
            throw new IllegalArgumentException("Start Node must exist in graph");
        }
        if(!containsNode(end)) {
            throw new IllegalArgumentException("End Node must exist in graph");
        }
        labelEdge<E, T> newEdge = new labelEdge<>(start, end, label);
        if(!Graph.get(start).contains(newEdge)) {
            Graph.get(start).add(newEdge);
        }
        checkRep();
    }



    /**
     * Removes the node from the graph as well as any edges that were associated the node
     *
     * @param node - node to be removed
     * @throws IllegalArgumentException if node is null or node doesn't exist in graph
     * @spec.modifies graph
     * @spec.effects removes specified node from graph
     */
    public void removeNode(String node) {
        if(node == null) {
            throw new IllegalArgumentException("Node can't be null");
        }
        if(!Graph.containsKey(node)) {
            throw new IllegalArgumentException("Node doesn't exist in graph");
        }
        checkRep();
        for(E n : Graph.keySet()) {
            Set<labelEdge<E, T>> edgeSet = Graph.get(n);
            for(labelEdge<E, T> e : edgeSet) {
                if(e.getDest().equals(node)) {
                    edgeSet.remove(e);
                }
            }
        }
        Graph.remove(node);
        checkRep();
    }



    /**
     * Return true if the node n is present within the graph.
     *
     * @param n, the node
     * @spec.requires n != null
     * @return true if the node n is present within this.nodes
     */
    public boolean containsNode(E n) {
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
    public Set<E> getNodes() {
        checkRep();
        return new HashSet<>(Graph.keySet());
    }


    /**
     * Gets the edges from a specific node in the graph
     *
     * @param node - the node whose edges we want to get
     * @return the collection of edges in the graph from a node
     */
    public List<labelEdge<E, T>> getEdges(E node) {
        checkRep();
        if(node == null) {
            throw new IllegalArgumentException("Node can't be null");
        }
        if(!containsNode(node)) {
            throw new IllegalArgumentException("Node must exist in graph");
        }
        return new ArrayList<>(Graph.get(node));
    }


    /**
     * Returns a set of outgoing edges of the node n.
     *
     * @param n, the node
     * @spec.requires n != null
     * @return a set of outgoing edges of the node n
     * @throws IllegalArgumentException if the node n isn't present in this.nodes
     */
    public Set<E> getChildren(E n) {
        checkRep();

        //throws IllegalArgumentException if node is null
        if (n == null)
            throw new IllegalArgumentException();

        //throws IllegalArgumentException if node doesn't exist in the graph
        if (!(containsNode(n)))
            throw new IllegalArgumentException();

        Set<E> children = new HashSet<>();
        for(labelEdge<E, T> e : Graph.get(n)) {
            children.add(e.getDest());
        }
        checkRep();
        return children;


    }

    /**
     * Tells us if the edge exists in the graph
     *
     * @param startNode - starting node of edge whose existence we are checking
     * @param endNode - ending node of edge whose existence we are checking
     * @param label - label of edge whose existence we are checking
     * @throws IllegalArgumentException if any parameter is null or any node doesn't exist in the graph
     * @return
     */
    public boolean containsEdge(E startNode, E endNode, T label) {
        checkRep();
        if(startNode == null || endNode == null || label == null) {
            throw new IllegalArgumentException("No input can be null");
        }
        if(!containsNode(startNode)) {
            throw new IllegalArgumentException("Start Node must exist in graph");
        }
        if(!containsNode(endNode)) {
            throw new IllegalArgumentException("End Node must exist in graph");
        }
        labelEdge<E, T> contains = new labelEdge<>(startNode, endNode, label);
        Set<labelEdge<E, T>> edges = Graph.get(startNode);
        for(labelEdge<E, T> e : edges) {
            if(e.equals(contains)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the specified edge from a given "start" and "end" with the label "label"
     *
     * @param start - origin of the edge
     * @param end - destination of the edge
     * @param label - label of the edge
     * @throws IllegalArgumentException if any parameter is null or a node doesn't exist in graph
     * @spec.modifies graph
     * @spec.effects removes specified edge from graph
     */
    public void removeEdge(E start, E end, T label) {
        boolean removed = false;
        if(start == null || end == null || label == null) {
            throw new IllegalArgumentException("No input can be null");
        }
        if(!containsNode(start)) {
            throw new IllegalArgumentException("Start Node must exist in graph");
        }
        if(!containsNode(end)) {
            throw new IllegalArgumentException("End Node must exist in graph");
        }
        Set<labelEdge<E, T>> edges = Graph.get(start);
        labelEdge<E, T> edge = new labelEdge<E, T>(start, end, label);
        for(labelEdge<E, T> e : edges) {
            if(e.equals(edge)) {
                edges.remove(e);
                removed = true;
            }
        }
        if(!removed) {
            throw new IllegalArgumentException("Edge doesn't exist");
        }
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
    private void checkRep() {
        assert Graph != null;
        if(check) {
            for(E node : Graph.keySet()) {
                assert node != null;
                assert Graph.get(node) != null;
                for(labelEdge<E, T> e : Graph.get(node)) {
                    assert e != null;
                }
            }
        }
    }

}