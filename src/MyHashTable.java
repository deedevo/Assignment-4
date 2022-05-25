public class MyHashTable <K,V>{
    private class HashNode<K,V>{
        private K key;
        private V value;
        private HashNode<K,V> next;
        public HashNode(K key,V value){
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString(){
            return "{" + key + " " + value + "}";
        }
    }
    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;
    public MyHashTable() {
        chainArray = new HashNode[64];
    }

    public MyHashTable(int M) {
        chainArray = new HashNode[M];
    }
    private int hash(K key){return (Math.abs(key.hashCode())) % chainArray.length;}

    public V get(K key) {
        int bucket = hash(key);
        HashNode<K, V> list = chainArray[bucket];
        while (list != null) {
            if (list.key.equals(key))
                return (V) list.value;
            list = list.next;
        }
        return null;
    }
    public void put(K key, V value) {
        int bucket = hash(key);
        HashNode<K,V> list = chainArray[bucket];
        while (list != null) {
            if (list.key.equals(key))
                break;
            list = list.next;
        }
        if (list != null) {
            list.value = value;
        }
        else {
            if (size >= 0.75*chainArray.length) {
                resize();
            }
            HashNode<K,V> newNode = new HashNode(key,value);
            newNode.key = key;
            newNode.value = value;
            newNode.next = chainArray[bucket];
            chainArray[bucket] = newNode;
            size++;
        }
    }

    private void resize() {
        HashNode<K,V>[] newChainArray = new HashNode[chainArray.length*2];
        for (int i = 0; i < chainArray.length; i++) {
            HashNode<K,V> list = chainArray[i];
            while (list != null) {
                HashNode<K,V> next = list.next;
                int hash = (Math.abs(list.key.hashCode())) % newChainArray.length;
                list.next = newChainArray[hash];
                newChainArray[hash] = list;
                list = next;
            }
        }
        chainArray = newChainArray;
    }
    public void remove(K key) {
        int bucket = hash(key);
        if (chainArray[bucket] == null) {
            return;
        }
        if (chainArray[bucket].key.equals(key)) {
            chainArray[bucket] = chainArray[bucket].next;
            size--;
            return;
        }
        HashNode<K, V> previous = chainArray[bucket];
        HashNode<K, V> current = previous.next;
        while (current != null && ! current.key.equals(key)) {
            current = current.next;
            previous = current;
        }
        if (current != null) {
            previous.next = current.next;
            size--;
        }
    }
    public boolean getKey(K key) {
        int bucket = hash(key);
        HashNode<K,V> list = chainArray[bucket];
        while (list != null) {
            if (list.key.equals(key))
                return true;
            list = list.next;
        }
        return false;
    }

    public boolean contains (V value){
        if (value == null) {
            throw new NullPointerException();
        }
        HashNode<K,V>[] chainArray1 = chainArray;
        for (int i = chainArray1.length ; i-- > 0 ;) {
            for (HashNode<K,V> e = chainArray1[i] ; e != null ; e = e.next) {
                if (e.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }
}
