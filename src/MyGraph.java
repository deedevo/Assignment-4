import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyGraph <Vertex>{
    private boolean bidirectional;
    private Map<Vertex, List<Vertex>> map;
    public MyGraph(){
        this(false);
    }
    public MyGraph(boolean bidirectional){
        this.bidirectional = bidirectional;
        map = new HashMap<>();
    }
    public void addVertex(Vertex vertex){
        map.put(vertex, new LinkedList<>());
    }
    public void addEdge(Vertex a, Vertex b){
        if(!hasVertex(a))
            addVertex(a);
        if(!hasVertex(b))
            addVertex(b);
        if(hasEdge(a,b) || a.equals(b)){
            return;
        }
        map.get(a).add(b);

        if(bidirectional)
            map.get(b).add(a);
    }
    public boolean hasVertex(Vertex a){
        return map.containsKey(a);
    }
    public boolean hasEdge(Vertex a,Vertex b){
        List<Vertex> list = map.get(a);
        return list != null && list.contains(b);
    }

}
