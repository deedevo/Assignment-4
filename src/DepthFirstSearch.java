import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch<V> extends Search<V> {
    public DepthFirstSearch(MyGraph<V> graph, V source) {
        super(source);
        dfs(graph, source);
    }
    private void dfs(MyGraph<V> graph, V current) {
        marked.add(current);
        count++;
        if((graph != null) && (current != null)) {
            Stack<V> stack = new Stack<>();
            List<V> list = new ArrayList<>();
            stack.push(current);
            list.add(current);
            while(!stack.empty()) {
                V temp = stack.pop();
                for(V i : graph.adjacencyList(temp)) {
                    if(!list.contains(i)) {
                        list.add(i);
                        stack.push(i);
                    }
                }
            }

        }
    }


}