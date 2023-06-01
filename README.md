# Assignment6 - Edge-Weighted Graph with Vertex

An edge-weighted graph is a graph where each edge has an associated weight. In this implementation, the graph is represented using vertices and their connections.

## Usage

To use the graph classes, follow these steps:

1. Import the necessary package:
```
import java.util.*;
```

2. Create an instance of the `WeightedGraph` class:
```
WeightedGraph<String> graph = new WeightedGraph<>();
```

3. Add vertices to the graph:
```
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
```

4. Add edges to the graph:
```
graph.addEdge(a, b, 4.0);
graph.addEdge(a, c, 2.0);
graph.addEdge(b, c, 1.0);
graph.addEdge(b, d, 5.0);
graph.addEdge(c, d, 8.0);
graph.addEdge(c, e, 10.0);
graph.addEdge(d, e, 2.0);
graph.addEdge(d, f, 6.0);
graph.addEdge(e, f, 3.0);
```

5. Test Breadth-First Search (BFS):
```
BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(graph);
System.out.println("Breadth-First Search traversal:");
List<Vertex<String>> bfsTraversal = bfs.breadthFirstSearch(a);
for (Vertex<String> vertex : bfsTraversal) {
    System.out.print(vertex.getData() + " ");
}
System.out.println();
```

6. Test Dijkstra's algorithm:
```
DijkstraSearch<String> dijkstraSearch = new DijkstraSearch<>(graph);
System.out.println("Shortest distances using Dijkstra's algorithm:");
Map<Vertex<String>, Double> shortestDistances = dijkstraSearch.dijkstraSearch(a);
for (Vertex<String> vertex : shortestDistances.keySet()) {
    double distance = shortestDistances.get(vertex);
    String distanceString = (distance == Double.POSITIVE_INFINITY) ? "Infinity" : String.valueOf(distance);
    System.out.println("Vertex: " + vertex.getData() + ", Distance: " + distanceString);
}

System.out.println("Shortest path from A to E:");
List<String> shortestPath = dijkstraSearch.findPath(a, e);
for (String vertexData : shortestPath) {
    System.out.print(vertexData + " ");
}
System.out.println();
```

## Class Details

### WeightedGraph Class

The `WeightedGraph` class represents the edge-weighted graph.

**Methods:**
- `addVertex(Vertex<V> vertex)`: Adds a vertex to the graph.
- `addEdge(Vertex<V> source, Vertex<V> destination, double weight)`: Adds an edge between two vertices with the specified weight.

### Vertex Class

The `Vertex` class represents a vertex in the graph.

**Constructor:**
- `Vertex(V data)`: Creates a vertex with the given data.

**Methods:**
- `getData()`: Returns the data associated with the vertex.

### BreadthFirstSearch Class

The `BreadthFirstSearch` class performs breadth-first search on the graph.

**Constructor:**
- `BreadthFirstSearch(WeightedGraph<V> graph)`: Creates

 a BFS instance with the given graph.

**Methods:**
- `breadthFirstSearch(Vertex<V> startVertex)`: Performs breadth-first search starting from the specified vertex and returns the traversal path as a list of vertices.

### DijkstraSearch Class

The `DijkstraSearch` class performs Dijkstra's algorithm on the graph.

**Constructor:**
- `DijkstraSearch(WeightedGraph<V> graph)`: Creates a DijkstraSearch instance with the given graph.

**Methods:**
- `dijkstraSearch(Vertex<V> startVertex)`: Performs Dijkstra's algorithm starting from the specified vertex and returns the shortest distances to all other vertices as a map of vertices and distances.
- `findPath(Vertex<V> startVertex, Vertex<V> endVertex)`: Finds the shortest path from the start vertex to the end vertex using Dijkstra's algorithm and returns the path as a list of vertex data.

## Example

```
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
System.out.println("Breadth-First Search traversal:");
List<Vertex<String>> bfsTraversal = bfs.breadthFirstSearch(a);
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
```
