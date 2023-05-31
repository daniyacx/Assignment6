import java.util.HashMap;
import java.util.Map;

public class Vertex<V> {
    private V data;
    private Map<Vertex<V>, Double> adjacentVertices;

    public Vertex(V data) {
        this.data = data;
        this.adjacentVertices = new HashMap<>();
    }

    public V getData() {
        return data;
    }

    public void addAdjacentVertex(Vertex<V> destination, double weight) {
        adjacentVertices.put(destination, weight);
    }

    public Map<Vertex<V>, Double> getAdjacentVertices() {
        return adjacentVertices;
    }

    public void removeAdjacentVertex(Vertex<V> vertex) {
        if (!adjacentVertices.containsKey(vertex)) {
            throw new IllegalArgumentException("Vertex " + vertex + " isn't adjacent to this vertex");
        }
        adjacentVertices.remove(vertex);
    }

    public double getWeight(Vertex<V> vertex) {
        if (!adjacentVertices.containsKey(vertex)) {
            throw new IllegalArgumentException("Vertex " + vertex + " isn't adjacent to this vertex");
        }
        return adjacentVertices.get(vertex);
    }

    public boolean containsAdjacentVertex(Vertex<V> vertex) {
        return adjacentVertices.containsKey(vertex);
    }

    public void clearAdjacentVertices() {
        adjacentVertices.clear();
    }

    public int getDegree() {
        return adjacentVertices.size();
    }
}