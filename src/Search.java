import java.util.List;

/**
 * search interface finds a path between two vertices in a graph
 * @param <V> the type of data stored in the vertices
 */
public interface Search<V> {

    /**
     * @findPath finds a path between the source vertex and the destination vertex in a graph
     * @param source      the source vertex
     * @param destination the destination vertex
     * @return a list of vertices representing the path from the source to the destination
     */
    List<V> findPath(Vertex<V> source, Vertex<V> destination);
}
