import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyGraph<Vertex> {
    private Map<Vertex, List<Vertex>> map;
    private boolean undirected;

    public MyGraph(){this(true);}
    public MyGraph(boolean undirected){
        this.undirected = undirected;
        map=new HashMap<>();
    }

    public void addVertex(Vertex vertex){
        map.put(vertex,new LinkedList<>());
    }
    public void addEdge(Vertex source,Vertex dest){
        if(!hasVertex(source)) addVertex(source);
        if(!hasVertex(dest)) addVertex(dest);

        if(hasEdge(source, dest) || source.equals(dest)) return;

        map.get(source).add(dest);

        if(undirected) map.get(dest).add(source);
    }
    public boolean hasVertex(Vertex vertex){
        return map.containsKey(vertex);
    }
    public boolean hasEdge(Vertex source,Vertex dest){
        List<Vertex> list = map.get(source);
        return list != null && list.contains(dest);
    }
    public int getVerticesCount(){return map.size();}
    public int getEdgesCount(){
        int count=0;
        for(Vertex v : map.keySet()){
            count+=map.get(v).size();
        }
        if(undirected) count/=2;
        return count;
    }
    public List<Vertex> adjacencyList(Vertex vertex){
        if(!hasVertex(vertex)) return null;
        return map.get(vertex);
    }
}