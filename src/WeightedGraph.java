import java.util.*;

public class WeightedGraph<V> {
    private final boolean undirected;
    private Map<V,Vertex<V>> map = new HashMap<>();

    public WeightedGraph() {
        this.undirected = true;
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(V v) {
        if(!hasVertex(v)) map.put(v,new Vertex<>(v));
    }

    public void addEdge(V source, V dest, double weight) {
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(dest))
            addVertex(dest);

        if (hasEdge(source, dest)
                || source.equals(dest))
            return; // reject parallels & self-loops

        map.get(source).addAdjacentVertex(map.get(dest),weight);

        if (undirected)
            map.get(dest).addAdjacentVertex(map.get(source),weight);
    }

    public int getVerticesCount() {
        return map.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (V v : map.keySet()) {
            count += map.get(v).size();
        }

        if (undirected)
            count /= 2;

        return count;
    }


    public boolean hasVertex(V v) {return map.containsKey(v);}

    public boolean hasEdge(V source, V dest) {
        if (!hasVertex(source)) return false;
        return map.get(source).containsAdjacentVertex(new Vertex<>(dest));

    }

    public Iterable<V> adjacencyList(V v) {
        if (!hasVertex(v)) return null;
        List<V> vertices = new LinkedList<>();
        for (Vertex<V> e : map.get(v).getAdjacencyVertices().keySet()) {
            vertices.add(e.getData());
        }
        return vertices;
    }

    public Iterable<Edge<V>> getEdges(V v) {
        if (!hasVertex(v)) return null;
        List<Edge<V>> edges = new LinkedList<>();
        for(Vertex<V> vertex : map.get(v).getAdjacencyVertices().keySet()){
            edges.add(new Edge<V>(v,vertex.getData(),map.get(v).getAdjacencyVertices().get(vertex)));
        }
        return edges;
    }
}