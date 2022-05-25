import java.util.Stack;

public class BST <K extends Comparable<K>, V>{
    private Node root;
    private class Node{
        private K key;
        private V value;
        private Node left , right;
        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }
    public V get(Node node, K key){
        if(node == null) return null;
        int compareResult = key.compareTo(node.key);
        if(compareResult == 0){
            return node.value;
        }else if(compareResult > 0){
            return get(node.right, key);
        }else{
            return get(node.left, key);
        }
    }
    public void put(K key, V value) {
        if (key == null) return;
        if (value == null) {
            delete(root,key);
            return;
        }
        put(key, value);
    }
    public void deleteKey(K key) {
        root = delete(root, key);
    }
    public Node delete(Node root, K key)
    {
        if (root == null) return null;
        int compareResult = key.compareTo(root.key);
        if (compareResult < 0)
            root.left = delete(root.left, key);
        else if (compareResult > 0)
            root.right = delete(root.right, key);
        else {

            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;


            Node result = minValue(root.right);
            root.key = result.key;
            root.value = result.value;
            delete(root.right, root.key);
            return root;
        }
        return root;
    }
    private Node minValue(Node node){
        if(node.left == null) return node;
        return minValue(node.left);
    }
    Stack<Node> stack;
    public void iterator(Node root) {
        stack = new Stack<Node>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
    public Stack<Node> keys(){
        stack = new Stack<Node>();
        iterator(root);
        return stack;
    }
}
