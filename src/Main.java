import java.util.*;
public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();
        // add vertices to the graph
        Vertex<String> a = new Vertex<>("Felix");
        Vertex<String> b = new Vertex<>("Jeongin");
        Vertex<String> c = new Vertex<>("Seungmin");
        Vertex<String> d = new Vertex<>("Hyunjin");
        Vertex<String> e = new Vertex<>("BangChan");
        Vertex<String> f = new Vertex<>("Changbin");

        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addVertex(f);
        // add edges to the graph
        graph.addEdge(a, b, 4.0);
        graph.addEdge(a, c, 2.0);
        graph.addEdge(b, c, 1.0);
        graph.addEdge(b, d, 5.0);
        graph.addEdge(c, d, 8.0);
        graph.addEdge(c, e, 10.0);
        graph.addEdge(d, e, 2.0);
        graph.addEdge(d, f, 6.0);
        graph.addEdge(e, f, 3.0);
        // test BFS
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(graph);
        System.out.println("BreadthFirstSearch traversal:");
        List<Vertex<String>> bfsTraversal = bfs.BreadthFirstSearch(a);
        for (Vertex<String> vertex : bfsTraversal) {
            System.out.print(vertex.getData() + " ");
        }
        System.out.println();
        // test Dijkstra's algorithm
        DijkstraSearch<String> dijkstraSearch = new DijkstraSearch<>(graph);
        System.out.println("Shortest distances using Dijkstra's algorithm:");
        Map<Vertex<String>, Double> shortestDistances = dijkstraSearch.dijkstraSearch(a);
        for (Vertex<String> vertex : shortestDistances.keySet()) {
            double distance = shortestDistances.get(vertex);
            String distanceString = (distance == Double.POSITIVE_INFINITY) ? "Infinity" : String.valueOf(distance);
            System.out.println("Vertex: " + vertex.getData() + ", Distance: " + distanceString);
        }
        // test finding the shortest path using Dijkstra's algorithm
        System.out.println("Shortest path from A to E:");
        List<String> shortestPath = dijkstraSearch.findPath(a, e);
        for (String vertexData : shortestPath) {
            System.out.print(vertexData + " ");
        }
        System.out.println();
    }
}
