import java.util.*;
public class DijkstraSearch<V> implements Search<V> {
    private WeightedGraph<V> graph;

    /**
     * constructs a new DijkstraSearch object with the specified weighted graph
     * @param graph the weighted graph to be searched
     */
    public DijkstraSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    /**
     * performs Dijkstra's algorithm starting from the specified start vertex and returns the distances to all vertices
     * @param startVertex the start vertex for the search
     * @return a map containing the distances from the start vertex to all other vertices
     */
    public Map<Vertex<V>, Double> dijkstraSearch(Vertex<V> startVertex) {
        Map<Vertex<V>, Double> distances = new HashMap<>();
        PriorityQueue<DijkstraNode<V>> priorityQueue = new PriorityQueue<>();

        // initialize all distances as positive infinity except the start vertex
        for (Vertex<V> vertex : graph.getVertices()) {
            distances.put(vertex, vertex.equals(startVertex) ? 0.0 : Double.POSITIVE_INFINITY);
        }

        priorityQueue.offer(new DijkstraNode<>(startVertex, 0.0));

        while (!priorityQueue.isEmpty()) {
            DijkstraNode<V> currentNode = priorityQueue.poll();
            Vertex<V> currentVertex = currentNode.getVertex();
            double currentDistance = currentNode.getDistance();

            // if the current distance is greater than the stored distance, skip processing
            if (currentDistance > distances.get(currentVertex)) {
                continue;
            }

            List<WeightedGraph<V>.Edge<V>> edges = graph.getEdges(currentVertex);
            for (WeightedGraph<V>.Edge<V> edge : edges) {
                Vertex<V> neighborVertex = edge.getDestination();
                double edgeWeight = edge.getWeight();
                double distanceThroughCurrent = currentDistance + edgeWeight;

                // if the calculated distance is smaller than the stored distance, update the distance and enqueue the neighbor vertex
                if (distanceThroughCurrent < distances.get(neighborVertex)) {
                    distances.put(neighborVertex, distanceThroughCurrent);
                    priorityQueue.offer(new DijkstraNode<>(neighborVertex, distanceThroughCurrent));
                }
            }
        }

        return distances;
    }

    /**
     * @printDijkstra prints the result of Dijkstra's algorithm, including the distances from the start vertex to all other vertices
     * @param startVertex the start vertex for the search
     */
    public void printDijkstra(Vertex<V> startVertex) {
        Map<Vertex<V>, Double> distances = dijkstraSearch(startVertex);

        System.out.println("Dijkstra's Algorithm Results:");
        for (Vertex<V> vertex : distances.keySet()) {
            Double distance = distances.get(vertex);
            String distanceString = (distance == Double.POSITIVE_INFINITY) ? "Infinity" : String.valueOf(distance);
            System.out.println("Vertex: " + vertex.getData() + ", Distance: " + distanceString);
        }
    }

    /**
     * @findPath finds the shortest path from the source vertex to the destination vertex using Dijkstra's algorithm
     * @param source      the source vertex
     * @param destination the destination vertex
     * @return a list of vertices representing the shortest path from the source to the destination
     */
    public List<V> findPath(Vertex<V> source, Vertex<V> destination) {
        Map<Vertex<V>, Double> distances = new HashMap<>();
        Map<Vertex<V>, Vertex<V>> parentMap = new HashMap<>();
        PriorityQueue<DijkstraNode<V>> priorityQueue = new PriorityQueue<>();

        // initialize all distances as positive infinity except the source vertex
        for (Vertex<V> vertex : graph.getVertices()) {
            distances.put(vertex, vertex.equals(source) ? 0.0 : Double.POSITIVE_INFINITY);
        }

        priorityQueue.offer(new DijkstraNode<>(source, 0.0));

        while (!priorityQueue.isEmpty()) {
            DijkstraNode<V> currentNode = priorityQueue.poll();
            Vertex<V> currentVertex = currentNode.getVertex();
            double currentDistance = currentNode.getDistance();

            // if the current distance is greater than the stored distance, skip processing
            if (currentDistance > distances.get(currentVertex)) {
                continue;
            }

            List<WeightedGraph<V>.Edge<V>> edges = graph.getEdges(currentVertex);
            for (WeightedGraph<V>.Edge<V> edge : edges) {
                Vertex<V> neighborVertex = edge.getDestination();
                double edgeWeight = edge.getWeight();
                double distanceThroughCurrent = currentDistance + edgeWeight;

                // if the calculated distance is smaller than the stored distance, update the distance and parentMap, and enqueue the neighbor vertex
                if (distanceThroughCurrent < distances.get(neighborVertex)) {
                    distances.put(neighborVertex, distanceThroughCurrent);
                    parentMap.put(neighborVertex, currentVertex);
                    priorityQueue.offer(new DijkstraNode<>(neighborVertex, distanceThroughCurrent));
                }
            }
        }

        return buildPath(parentMap, destination);
    }

    /**
     * @buildPath builds the shortest path from the source to the destination vertex using the parentMap
     * @param parentMap   a map containing the parent vertices for each vertex in the path
     * @param destination the destination vertex
     * @return a list of vertices representing the shortest path from the source to the destination
     */
    private List<V> buildPath(Map<Vertex<V>, Vertex<V>> parentMap, Vertex<V> destination) {
        List<V> path = new ArrayList<>();
        Vertex<V> currentVertex = destination;

        while (currentVertex != null) {
            path.add(0, currentVertex.getData());
            currentVertex = parentMap.get(currentVertex);
        }

        return path;
    }

    /**
     * this inner class represents a node in Dijkstra's algorithm with a vertex and a distance
     * @param <V> the type of the vertex
     */
    private static class DijkstraNode<V> implements Comparable<DijkstraNode<V>> {
        private Vertex<V> vertex;
        private double distance;

        /**
         * constructs a DijkstraNode with the specified vertex and distance
         * @param vertex   the vertex associated with this node
         * @param distance the distance associated with this node
         */
        public DijkstraNode(Vertex<V> vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        /**
         * @getVertex returns the vertex associated with this node
         * @return the vertex associated with this node
         */
        public Vertex<V> getVertex() {
            return vertex;
        }

        /**
         * @getDistance returns the distance associated with this node
         * @return the distance associated with this node
         */
        public double getDistance() {
            return distance;
        }

        @Override
        public int compareTo(DijkstraNode<V> other) {
            return Double.compare(distance, other.distance);
        }
    }
}