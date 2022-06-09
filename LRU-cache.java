import java.util.*;

class LRUCache {
    // All methods have a time complexity of O(1)
    // Space can be neglected if we not considering software engineering hashmap and linkedlist
    // This methods is submitted on leetcode with no errors
    class Node {
        Node prev;
        Node next;
        int key;
        int val;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    
    // Keep map, head, tail and capacity in global
    private HashMap<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;
    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        // Initialize the default dummy head and tail linkedlist
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }
    
    public void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node temp = map.get(key);
            remove(temp);
            addToHead(temp);
            return temp.val;
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        // Check if it is fresh node or not
        if(map.containsKey(key)){
            Node update = map.get(key);
            update.val = value;
            remove(update);
            addToHead(update);
        } else {
            if(capacity == map.size()){
                Node tailPrev = tail.prev;
                remove(tailPrev);
                // Also remove from the map
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key,value);
            addToHead(newNode);
            // Also add to the map
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