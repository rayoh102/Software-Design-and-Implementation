package marvel;

import graph.*;
import java.util.*;

public class MarvelPaths {

    // Not an ADT so no RI/AF needed

    /**
     *
     * Creates a graph using the given filename, then allows the user to enter two items to find a path between. After
     * being prompted for the start of the path, the user can instead choose to quit the program by responding with
     * 'quit'. Once a search has been completed, a path is printed (if found), and the user is reprompted for another
     * starting item or to quit instead.
     *
     */
    public static void main(String args[]) {
        Graph<String, String> marvelConnections = createGraph("marvel.tsv");
        Scanner scan = new Scanner(System.in);
        boolean exit;
        do {
            exit = false;
            System.out.println("Enter the hero name you want to see a path from: ");
            System.out.println("Or type 'exit' to leave.");
            String start = scan.nextLine();
            if(start.equals("exit")) {
                exit = true;
            }
            if(!exit) {
                System.out.println("Enter the hero name you want to see the path go to: ");
                String end = scan.nextLine();
                if(!marvelConnections.containsNode(start) || !marvelConnections.containsNode(end)) {
                    System.out.println("hero name was not found.");
                }
                else {
                    String output = "Here's the path from " + start + " to " + end + ":";
                    List<labelEdge<String, String>> shortestPath = searchPath(marvelConnections, start, end);
                    if(shortestPath == null) {
                        output += "No path exists.";
                    }
                    else {
                        for(labelEdge<String, String> e : shortestPath) {
                            output += "\nfrom " + e.getStart() + " to " + e.getDest() + " through book " + e.getLabel();
                        }
                    }
                    System.out.println(output);
                }
            }
        } while(!exit);
    }

    /**
     * Creates a graph with the nodes and connections in the given file
     *
     * @param filename - the file which we wish to create a graph with
     * @throws IllegalArgumentException if filename is null
     * @return a graph containing heroes as nodes and books connecting the heroes as edges
     */
    public static Graph<String, String> createGraph(String filename) {
        if (filename != null) {
            Graph<String, String> marvelGraph = new Graph<>();
            Map<String, Set<String>> parsedData = MarvelParser.parseData(filename);
            for(String book : parsedData.keySet()) {
                for(String firstHero : parsedData.get(book)) {
                    if(!marvelGraph.containsNode(firstHero)) {
                        marvelGraph.addNode(firstHero);
                    }
                    for(String otherHero : parsedData.get(book)) {
                        if(!marvelGraph.containsNode((otherHero))) {
                            marvelGraph.addNode(otherHero);
                        }
                        if(!firstHero.equals(otherHero)) {
                            // Since we iterate through twice, we should get the edges both ways
                            // as the spec says. We don't need to make both edges at the same time.
                            marvelGraph.addEdge(firstHero, otherHero, book);
                        }
                    }
                }
            }
            return marvelGraph;
        }
        throw new IllegalArgumentException("Passed filename must not be null");
    }

    /**
     * This method gives us the shortest path that exists from the given start hero to the given end hero
     * (if any path exists).
     *
     * @param graph - the graph of nodes and edges we've created
     * @param startHero - the hero we want a path to come from
     * @param endHero - the hero we want a path to go to
     * @throws IllegalArgumentException if any input is null
     * @return a list of edges between nodes that make up the shortest path from the start node to the end node. If
     * no path is found, this method returns null
     */
    public static List<labelEdge<String, String>> searchPath(Graph<String, String> graph, String startHero, String endHero) {
        if(endHero != null && startHero != null && graph != null) {
            // Using BFS algorithm from instructions: Create queue and storage for paths
            Queue<String> notSeen = new LinkedList<>();
            Map<String, List<labelEdge<String, String>>> pathsToNodes = new HashMap<>();
            // Push start node onto a queue
            notSeen.add(startHero);
            // Add start to Map (mapped to empty list)
            pathsToNodes.put(startHero, new ArrayList<>());
            // Loop Invariant: Every key in the Map paths is a node that has been visited and is mapped to a series of
            // edges that lead to it. Nodes in the queue have not been visited yet.
            while(!notSeen.isEmpty()) {
                // Dequeue next node
                String node = notSeen.remove();
                // If node is desired end, return path associated with it
                if(node.equals(endHero)) {
                    List<labelEdge<String, String>> path = pathsToNodes.get(node);
                    return new ArrayList<>(path);
                }
                List<labelEdge<String, String>> nodeEdges = graph.getEdges(node);
                // Implementing ordering property in HW Directions
                Collections.sort(nodeEdges, new Comparator<labelEdge<String, String>>() {
                    @Override
                    public int compare(labelEdge<String, String> o1, labelEdge<String, String> o2) {
                        // First sort by increasing order of edge's character name (end node)
                        if(!o1.getDest().equals(o2.getDest())) {
                            return o1.getDest().compareTo(o2.getDest());
                        }
                        // Then sort by book name (label)
                        if(!o1.getLabel().equals(o2.getLabel())) {
                            return o1.getLabel().compareTo(o2.getLabel());
                        }
                        return o1.getStart().compareTo(o2.getStart());
                    }
                });
                for(labelEdge<String, String> e : nodeEdges) {
                    String end = e.getDest();
                    // If node that edge leads to has not been visited, explore it
                    if(!pathsToNodes.containsKey(end)) {
                        // Update path to current node with new node, then insert mapping from
                        // new node to new path into map.
                        List<labelEdge<String, String>> prev = pathsToNodes.get(node);
                        List<labelEdge<String, String>> updated = new ArrayList<>(prev);
                        updated.add(e);
                        pathsToNodes.put(end, updated);
                        // Add new node to queue to check for desired end node
                        notSeen.add(end);
                    }
                }
            }
            return null;
        }
        throw new IllegalArgumentException("No input can be null");
    }

}
