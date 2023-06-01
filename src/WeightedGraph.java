import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedGraph<V> {
    private Map<Vertex<V>, List<Edge<V>>> adjacencyList;
    public WeightedGraph() {
        adjacencyList = new HashMap<>();
    }

    public class Edge<V> {
        private Vertex<V> source;
        private Vertex<V> destination;
        private double weight;

        public Edge(Vertex<V> source, Vertex<V> destination, double weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        /**
         * @getSource retrieves the source vertex of the edge
         * @return the source vertex
         */
        public Vertex<V> getSource() {
            return source;
        }

        /**
         * @getDestination retrieves the destination vertex of the edge
         * @return the destination vertex
         */
        public Vertex<V> getDestination() {
            return destination;
        }

        /**
         * @getWeight retrieves the weight of the edge
         * @return the weight of the edge
         */
        public double getWeight() {
            return weight;
        }
    }

    /**
     * @addVertex adds a vertex to the graph with an empty list of edges
     * @param vertex the vertex to be added
     */
    public void addVertex(Vertex<V> vertex) {
        adjacencyList.put(vertex, new ArrayList<>());
    }

    /**
     * @addEdge adds an edge to the graph with the specified source, destination, and weight
     * @param source      the source vertex of the edge
     * @param destination the destination vertex of the edge
     * @param weight      the weight of the edge
     */
    public void addEdge(Vertex<V> source, Vertex<V> destination, double weight) {
        validateVertex(source);
        validateVertex(destination);
        List<Edge<V>> edges = adjacencyList.get(source); // get the list of edges for the source vertex
        edges.add(new Edge<>(source, destination, weight)); // add the new edge to the list of edges
        adjacencyList.put(source, edges); // update the adjacency list with the modified list of edges
    }

    /**
     * @validateVertex validates if the specified vertex is present in the graph
     * @param vertex the vertex to be validated
     * @throws IllegalArgumentException if the vertex is not present in the graph
     */
    public void validateVertex(Vertex<V> vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            throw new IllegalArgumentException("Vertex " + vertex + " is out of range"); // throw an exception if the vertex is not present in the graph
        }
    }

    /**
     * @getVertices retrieves a list of all vertices in the graph
     * @return a list of vertices in the graph
     */
    public List<Vertex<V>> getVertices() {
        return new ArrayList<>(adjacencyList.keySet());
    }

    /**
     * retrieves a list of all edges in the graph
     * @return a list of edges in the graph
     */
    public List<Edge<V>> getEdges() {
        List<Edge<V>> allEdges = new ArrayList<>();
        for (List<Edge<V>> edges : adjacencyList.values()) {
            allEdges.addAll(edges);
        }
        return allEdges;
    }

    /**
     * prints the information of the graph, including vertices and edges with their weights
     */
    public void printGraphInformation() {
        System.out.println("Vertices:");
        for (Vertex<V> vertex : adjacencyList.keySet()) {
            System.out.println(vertex.getData());
        }
        System.out.println();

        System.out.println("Edges with Weights:");
        for (List<Edge<V>> edges : adjacencyList.values()) {
            for (Edge<V> edge : edges) {
                System.out.println("Source: " + edge.getSource().getData());
                System.out.println("Destination: " + edge.getDestination().getData());
                System.out.println("Weight: " + edge.getWeight());
                System.out.println();
            }
        }
    }

    /**
     * retrieves a list of edges connected to the specified vertex
     * @param vertex the vertex for which to retrieve the edges
     * @return a list of edges connected to the vertex
     */
    public List<Edge<V>> getEdges(Vertex<V> vertex) {
        validateVertex(vertex);
        return adjacencyList.get(vertex);
    }
}