package graph;

/**
 * <b>labelEdge</b> is an outgoing edge with the label of a node and the destination of the edge.
 * <p>
 * @spec.specfield destination : String
 * @spec.specfield label : String
 *
 */

public class labelEdge <T, E extends Comparable<E>> implements Comparable<labelEdge<T, E>> {

    private final T dest;   // destination of this edge
    private final E label;  // label of this edge


    /**
     * Creates a labeled edge.
     *
     * @param d, the destination of the edge
     * @param l, the label of the edge
     * @spec.requires d, l != null
     * @spec.effects constructs a labeled edge with the destination <var>d</var> and the label <var>l</var>
     */
    public labelEdge(T d, E l) {
        if (d == null || l == null)
            throw new IllegalArgumentException("Arguments cannot be null.");

        dest = d;
        label = l;
        checkRep();
    }


    /**
     * Compares the order of the object with the specified object. It will produce negative, zero, or positive value
     * if the object is less than, equal to, or greater than the specified object.
     *
     * @param le, the object to be compared
     * @return a negative, zero, or a positive value if the object is less than, equal to, or greater than
     * the specified object
     */
    @Override
    public int compareTo(labelEdge<T, E> le) {
        throw new RuntimeException();
    }


    /**
     * Returns true if the object o represent the same edge
     *
     * @param o, the object to be compared
     * @return true if o represents the same destination and label as the edge
     */
    @Override
    public boolean equals(Object o) {
        throw new RuntimeException();
    }


    /**
     * Returns the destination of the edge.
     *
     * @return the destination of the edge
     */
    public T getDest() {
        throw new RuntimeException();
    }

    /**
     * Returns the label of the edge.
     *
     * @return the label of the edge
     */
    public E getLabel() {
        throw new RuntimeException();
    }

    /**
     * Returns the string representation of the edge.
     *
     * @return the string representation of the edge
     */
    @Override
    public String toString() {
        throw new RuntimeException();
    }

    /**
     * Return the hash code of this edge.
     *
     * @return the hash code of this edge
     */
    @Override
    public int hashCode() {
        throw new RuntimeException();
    }


    /**
     * Checks if rep invariant holds.
     */
    private void checkRep(){
        throw new RuntimeException();
    }
}