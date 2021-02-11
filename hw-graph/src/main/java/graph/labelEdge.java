package graph;

/**
 * <b>labelEdge</b> is an outgoing edge with the label of a node and the destination of the edge.
 * <p>
 * @spec.specfield destination : String
 * @spec.specfield label : String
 *
 */

public class labelEdge {

    // Rep invariant:
    //     dest != null && label != null

    // Abstract function:
    //     AF(this) = a labeled edge without the starting point le such that le.destination = this.destination
    //     and le.label = this.label

    private final String destination;   // destination of this edge
    private final String label;  // label of this edge


    /**
     * Creates a labeled edge.
     *
     * @param d, the destination of the edge
     * @param l, the label of the edge
     * @spec.requires d, l != null
     * @spec.effects constructs a labeled edge with the destination d and the label l
     */
    public labelEdge(String d, String l) {

        //checks if destination or label is null
        if (d == null || l == null)
            throw new IllegalArgumentException();

        destination = d;
        label = l;
        checkRep();
    }


    /**
     * Returns true if the object o represent the same edge
     *
     * @param o, the object to be compared
     * @return true if o represents the same destination and label as the edge
     */
    @Override
    public boolean equals(Object o) {
        checkRep();
        if (!(o instanceof labelEdge))
            return false;

        labelEdge le = (labelEdge) o;
        checkRep();
        return destination.equals(le.destination) && label.equals(le.label);
    }


    /**
     * Returns the destination of the edge.
     *
     * @return the destination of the edge
     */
    public String getDest() {
        checkRep();
        return destination;
    }

    /**
     * Returns the label of the edge.
     *
     * @return the label of the edge
     */
    public String getLabel() {
        checkRep();
        return label;
    }

    /**
     * Returns the string representation of the edge.
     *
     * @return the string representation of the edge
     */
    @Override
    public java.lang.String toString() {
        checkRep();
        java.lang.String result = destination.toString() + "(" + label.toString() + ")";
        checkRep();
        return result;
    }

    /**
     * Return the hash code of this edge.
     *
     * @return the hash code of this edge
     */
    @Override
    public int hashCode() {
        checkRep();
        return destination.hashCode() + label.hashCode();
    }


    /**
     * Checks if rep invariant holds.
     */
    private void checkRep(){
        if (destination == null)
            throw new RuntimeException("Destination cannot be null.");

        if (label == null)
            throw new RuntimeException("Label cannot be null.");
    }
}