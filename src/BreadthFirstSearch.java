import java.util.*;
public class BreadthFirstSearch<V> implements Search<V> {
    private WeightedGraph<V> graph;

    /**
     * constructs a BreadthFirstSearch object with the specified graph
     * @param graph the graph to be searched
     */
    public BreadthFirstSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    /**
     * performs breadth first search starting from the specified startVertex and returns the visited vertices in the order they were visited
     * @param startVertex the starting vertex for the breadth-first search
     * @return a list of visited vertices in the order they were visited
     */
    public List<Vertex<V>> BreadthFirstSearch(Vertex<V> startVertex) {
        List<Vertex<V>> visited = new ArrayList<>();
        Queue<Vertex<V>> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            Vertex<V> currentVertex = queue.poll();
            List<WeightedGraph<V>.Edge<V>> edges = graph.getEdges(currentVertex);

            for (WeightedGraph<V>.Edge<V> edge : edges) {
                Vertex<V> neighborVertex = edge.getDestination();
                if (!visited.contains(neighborVertex)) {
                    visited.add(neighborVertex);
                    queue.add(neighborVertex);
                }
            }
        }

        return visited;
    }

    /**
     * @printBFS performs breadth first search starting from the specified startVertex and prints the visited vertices
     * @param startVertex the starting vertex for the breadth-first search
     */
    public void printBFS(Vertex<V> startVertex) {
        Set<Vertex<V>> visited = new HashSet<>();
        Queue<Vertex<V>> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.offer(startVertex);

        while (!queue.isEmpty()) {
            Vertex<V> currentVertex = queue.poll();
            System.out.print(currentVertex.getData() + " ");

            List<WeightedGraph<V>.Edge<V>> edges = graph.getEdges(currentVertex);
            for (WeightedGraph<V>.Edge<V> edge : edges) {
                Vertex<V> adjacentVertex = edge.getDestination();
                if (!visited.contains(adjacentVertex)) {
                    visited.add(adjacentVertex);
                    queue.offer(adjacentVertex);
                }
            }
        }

        System.out.println();
    }

    @Override
    public List<V> findPath(Vertex<V> source, Vertex<V> destination) {
        List<Vertex<V>> path = new ArrayList<>();
        Queue<Vertex<V>> queue = new LinkedList<>();
        Map<Vertex<V>, Vertex<V>> parentMap = new HashMap<>();

        queue.offer(source);
        parentMap.put(source, null);

        while (!queue.isEmpty()) {
            Vertex<V> currentVertex = queue.poll();
            if (currentVertex.equals(destination)) {
                path.add(destination);
                Vertex<V> parent = parentMap.get(destination);
                while (parent != null) {
                    path.add(0, parent);
                    parent = parentMap.get(parent);
                }
                break;
            }

            List<WeightedGraph<V>.Edge<V>> edges = graph.getEdges(currentVertex);
            for (WeightedGraph<V>.Edge<V> edge : edges) {
                Vertex<V> neighborVertex = edge.getDestination();
                if (!parentMap.containsKey(neighborVertex)) {
                    queue.offer(neighborVertex);
                    parentMap.put(neighborVertex, currentVertex);
                }
            }
        }

        return (List<V>) path;
    }
}