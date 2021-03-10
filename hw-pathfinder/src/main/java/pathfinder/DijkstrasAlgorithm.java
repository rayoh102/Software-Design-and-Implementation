package pathfinder;

import graph.labelEdge;
import graph.Graph;
import pathfinder.datastructures.Path;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * This class uses DigjkstrasAlgorithm to compute the lowest cost path
 * between two provided nodes in a graph. We require that all edge
 * costs are not negative.
 */

public class DijkstrasAlgorithm {

    // This class does not represent an ADT

    /**
     *
     * @param graph - the graph that we are finding the path on
     * @param start - the node we are finding the path from
     * @param dest - the node we are finding the path to
     * @param <E> - the type of the node in graph
     * @throws IllegalArgumentException if any of graph, start, or dest are null. Also throws the exception
     * if either of the nodes aren't in the graph
     * @return a Path representing the lowest cost path or null if a path doesn't exist
     */
    public static <E> Path<E> minimumCostPath(Graph<E, Double> graph, E start, E dest) {
        if(graph == null || start == null || dest == null) {
            throw new IllegalArgumentException("No input can be null");
        }
        if(!graph.containsNode(start) || !graph.containsNode(dest)) {
            throw new IllegalArgumentException("At least one node doesn't exist in the graph");
        }
        /*
        Here we're just implementing the pseudocode described in the instructions
         */
        // Each element is a path from start to a given node.
        // A path's “priority” in the queue is the total cost of that path.
        // Nodes for which no path is known yet are not in the queue.
        //Set priority queue to minimize path cost
        PriorityQueue<Path<E>> active = new PriorityQueue<>((Path<E> path1, Path<E> path2) ->
                Double.compare(path1.getCost(), path2.getCost()));
        Set<E> finished = new HashSet<>();
        Path<E> startPath = new Path<E>(start);
        active.add(startPath);
        while(!active.isEmpty()) {
            // minPath is the lowest-cost path in active and,
            // if minDest isn't already 'finished,' is the
            // minimum-cost path to the node minDest
            Path<E> minPath = active.remove();
            E minDest = minPath.getEnd();
            if(minDest.equals(dest)) {
                return minPath;
            }
            else if(finished.contains(minDest)) {
                continue;
            }
            for(labelEdge<E, Double> edge : graph.getEdges(minDest)) {
                // If we don't know the minimum-cost path from start to child,
                // examine the path we've just found
                if(!finished.contains(edge.getDest())) {
                    Path<E> addPath = minPath.extend(edge.getDest(), edge.getLabel());
                    active.add(addPath);
                }
            }
            finished.add(minDest);
        }
        //If we reach here, there is no path between the two nodes
        return null;
    }


}
