// Time Complexity : O(1)
// Space Complexity : O(c) --> where c is the capacity
// Did this code successfully run on Leetcode (146): Yes 
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

class LRUCache {
    
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
    
    public void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    public void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;
    
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;        
        
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            removeNode(node);
            addToHead(node);
            return node.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        // case 1: node already present
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }
        
        // case 2: new node comming in
        else {
            Node newNode = new Node(key, value);  
            // case 1: cache is full
            if (map.size() == capacity) {
                Node tailNode = tail.prev;
                removeNode(tailNode);
                map.remove(tailNode.key);
            }
            // case 2: cache is not full
            map.put(key, newNode);
            addToHead(newNode);
        }
    }
}