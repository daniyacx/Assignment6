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

        public Vertex<V> getSource() {
            return source;
        }

        public Vertex<V> getDestination() {
            return destination;
        }

        public double getWeight() {
            return weight;
        }
    }

    public void addVertex(Vertex<V> vertex) {
        adjacencyList.put(vertex, new ArrayList<>());
    }

    public void addEdge(Vertex<V> source, Vertex<V> destination, double weight) {
        validateVertex(source);
        validateVertex(destination);
        List<Edge<V>> edges = adjacencyList.get(source);
        edges.add(new Edge<>(source, destination, weight));
        adjacencyList.put(source, edges);
    }

    public void validateVertex(Vertex<V> vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            throw new IllegalArgumentException("Vertex " + vertex + " is out of range");
        }
    }

    public List<Vertex<V>> getVertices() {
        return new ArrayList<>(adjacencyList.keySet());
    }

    public List<Edge<V>> getEdges() {
        List<Edge<V>> allEdges = new ArrayList<>();
        for (List<Edge<V>> edges : adjacencyList.values()) {
            allEdges.addAll(edges);
        }
        return allEdges;
    }

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

    public List<Edge<V>> getEdges(Vertex<V> vertex) {
        validateVertex(vertex);
        return adjacencyList.get(vertex);
    }
}