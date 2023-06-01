import java.util.HashMap;
import java.util.Map;
public class Vertex<V> {
    private V data;
    private Map<Vertex<V>, Double> adjacentVertices;
    public Vertex(V data) {
        this.data = data; // initialize the vertex with the provided data
        this.adjacentVertices = new HashMap<>(); // initialize the map for adjacent vertices
    }

    /**
     * @getData retrieves the data stored in the vertex
     * @return the data stored in the vertex
     */
    public V getData() {
        return data;
    }

    /**
     * @addAdjacentVertex adds an adjacent vertex with the specified weight
     * @param destination the adjacent vertex
     * @param weight      the weight of the edge connecting the vertices
     */
    public void addAdjacentVertex(Vertex<V> destination, double weight) {
        adjacentVertices.put(destination, weight);
    }

    /**
     * retrieves a map of the adjacent vertices and their corresponding edge weights
     * @return a map of adjacent vertices and their edge weights
     */
    public Map<Vertex<V>, Double> getAdjacentVertices() {
        return adjacentVertices;
    }

    /**
     * @removeAdjacentVertex removes the specified adjacent vertex from the vertex's adjacency list
     * @param vertex the adjacent vertex to be removed
     * @throws IllegalArgumentException if the specified vertex is not adjacent to this vertex
     */
    public void removeAdjacentVertex(Vertex<V> vertex) {
        if (!adjacentVertices.containsKey(vertex)) { // check if the vertex is adjacent to this vertex
            throw new IllegalArgumentException("Vertex " + vertex + " isn't adjacent to this vertex");
        }
        adjacentVertices.remove(vertex);
    }

    /**
     * @getWeight retrieves the weight of the edge connecting this vertex to the specified adjacent vertex
     * @param vertex the adjacent vertex
     * @return the weight of the edge
     * @throws IllegalArgumentException if the specified vertex is not adjacent to this vertex
     */
    public double getWeight(Vertex<V> vertex) {
        if (!adjacentVertices.containsKey(vertex)) { // check if the vertex is adjacent to this vertex
            throw new IllegalArgumentException("Vertex " + vertex + " isn't adjacent to this vertex");
        }
        return adjacentVertices.get(vertex);
    }

    /**
     * @containsAdjacentVertex checks if this vertex has an adjacent vertex with the specified vertex
     * @param vertex the vertex to check for adjacency
     * @return true if the vertex is adjacent, false otherwise
     */
    public boolean containsAdjacentVertex(Vertex<V> vertex) {
        return adjacentVertices.containsKey(vertex);
    }

    /**
     * @clearAdjacentVertices clears all adjacent vertices from the vertex
     */
    public void clearAdjacentVertices() {
        adjacentVertices.clear();
    }

    /**
     * @getDegree retrieves the degree of the vertex, which represents the number of adjacent vertices
     * @return the degree of the vertex
     */
    public int getDegree() {
        return adjacentVertices.size();
    }
}
