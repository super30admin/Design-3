//Time = O(1)
//Space = O(n)

class LRUCache {
    // A map to store the key-value pairs in the cache
    private Map<Integer, Node> map;
    // The head of the doubly linked list, representing the most recently accessed key
    private Node head;
    // The tail of the doubly linked list, representing the least recently accessed key
    private Node tail;
    // The maximum capacity of the cache
    private int capacity;

    public LRUCache(int capacity) {
        // Initialize the map, head, and tail
        this.map = new HashMap<>();
        this.head = new Node();
        this.tail = new Node();
        // Link the head and tail nodes together
        this.head.next = this.tail;
        this.tail.prev = this.head;
        // Initialize the capacity
        this.capacity = capacity;
    }
    
    public int get(int key) {
        // If the key is not in the map, return -1
        if (!map.containsKey(key)) {
            return -1;
        }
        // Otherwise, retrieve the node from the map
        Node node = map.get(key);
        // Move the node to the front of the list
        removeNode(node);
        addNodeToHead(node);
        // Return the value associated with the key
        return node.value;
    }
    
    public void put(int key, int value) {
        // If the key is already in the map, update its value and move it to the front of the list
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addNodeToHead(node);
        } else {
            // If the map is at capacity, remove the least recently used item from the map and list
            if (map.size() == capacity) {
                map.remove(tail.prev.key);
                removeNode(tail.prev);
            }
            // Add the new item to the map and list
            Node node = new Node(key, value);
            map.put(key, node);
            addNodeToHead(node);
        }
    }
    
    // Removes a node from the list
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    // Adds a node to the front of the list
    private void addNodeToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
    
    // A class to represent a node in the doubly linked list
    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        
        public Node() {}
        
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
