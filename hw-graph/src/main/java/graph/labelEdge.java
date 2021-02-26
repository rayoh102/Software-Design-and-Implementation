package graph;

/**
 * <b>labelEdge</b> is an outgoing edge with the label of a node and the destination of the edge.
 * <p>
 * @spec.specfield destination : String
 * @spec.specfield label : String
 *
 */

public class labelEdge<E, T>{

    // Rep invariant:
    //     startNode != null && endNode != null && startNode != null

    // Abstract function:
    //     An edge from startNode to endNode containing the information in label

    private E start;
    private E end;   // destination of this edge
    private T label;  // label of this edge

    /**
     * Used to check if RI holds
     */
    private final static boolean check = false;




    /**
     * Creates a labeled edge.
     *
     * @param start - the beginning of the edge
     * @param end - the destination of the edge
     * @param label - the label of the edge
     * @throws IllegalArgumentException if the label or nodes are null
     * @spec.effects constructs a new edge e with e.start = start,
     * e.end = end, and e.label = label
     */
    public labelEdge(E start, E end, T label) {
        if(label == null || start == null || end == null) {
            throw new IllegalArgumentException("No input can be null");
        }
        this.start = start;
        this.end = end;
        this.label = label;
        checkRep();
    }


    /**
     * Test for equality between edges
     *
     * @param o - other edge we are comparing equality with
     * @return boolean determining if other edge o is equal to this.edge
     */
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof labelEdge)) {
            return false;
        }
        labelEdge e = (labelEdge) o;
        return this.start  == e.start && this.end == e.end && this.label == e.label;
    }


    /**
     * returns the start of edge
     *
     * @return the node the edge comes from
     */
    public E getStart() {
        checkRep();
        return this.start;
    }


    /**
     * Returns the destination of the edge.
     *
     * @return the destination of the edge
     */
    public E getDest() {
        checkRep();
        return this.end;
    }

    /**
     * Returns the label of the edge.
     *
     * @return the label of the edge
     */
    public T getLabel() {
        checkRep();
        return this.label;
    }


    /**
     * Hashing function
     *
     * @return an int determining the hashcode of the edge
     */
    @Override
    public int hashCode() {
        return 13 * start.hashCode() + 37 * end.hashCode() + 61 * label.hashCode();
    }


    /**
     * Checks if rep invariant holds.
     */
    private void checkRep() {
        if(check) {
            assert start != null;
            assert end != null;
            assert label != null;
        }
    }
}