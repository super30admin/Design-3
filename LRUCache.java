public class LRUCache {
    class Node{
        int key;
        int value;
        Node prev;
        Node next;
        private Node(int key, int val){
            this.key = key;
            this.value = val;
        }
    }

    Node head;
    Node tail;
    HashMap<Integer, Node> map = new HashMap<>();
    int cap;
    public LRUCache(int capacity) {
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        cap = capacity;
    }
    
    private void addToBeginning(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    private void removeFromList(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private void removeFromTail(){
        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;
    }
    
    // TC is O(1)
    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            removeFromList(node);
            addToBeginning(node);
            return node.value;
        }
        return -1;
    }
    
    // TC is O(1)
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            removeFromList(node);
            addToBeginning(node);
        }else if(map.size() == cap){
            map.remove(tail.prev.key);
            removeFromTail();
            Node node = new Node(key, value);
            addToBeginning(node);
            map.put(key, node);
        }else{
            Node node = new Node(key, value);
            addToBeginning(node);
            map.put(key, node);
        }
    }
}
