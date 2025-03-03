//To implement LRUCache, we make use of a doubly linked list with nodes as key and values given and a hashmap with keys and given keys and values as nodes
//For implementing the get() method, we just retrieve the object, if it exists from the hashmap and we make that node as most recent 
//For implementing the put() method, we check if the node already exists or not. If it exists, we update the value and make it most recent. 
//If the given key, value doesn't exist, we create a node, add it to the map and make it most recent, if there is enough capacity. If capacity is full, we remove the least recently used one and then add the current node to the list and make it most recent.
//Time complexity: O(1) for all methods
//Space Complexity: O(n) where n is the capacity of the cache
import java.util.HashMap;

class LRUCache {
    class Node{
        int key; int val;
        Node next; Node prev;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    private Node head;
    private Node tail;
    private int cap;
    HashMap<Integer, Node> map;

    public LRUCache(int capacity) {
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.cap = capacity;
        this.map = new HashMap<>();
        this.head.next = this.tail;
        this.tail.prev = this.head;
        
    }

    private void removeNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
        
    }

    private void addToHead(Node node){
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        } else {
            //fresh
            if(map.size() == cap){
                //full
                //remove the tailprev
                Node tailPrev = this.tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
        }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */