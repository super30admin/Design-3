

//Time Complexity: O(1) for both get and put operations
//Space Complexity: O(capacity) where capacity is the maximum number of items that can be stored in the cache

// We use a HashMap to store the key-node pairs for O(1) access.
// We use a doubly linked list to maintain the order of usage of the nodes. 
// The most recently used node is at the head and the least recently used node is at the tail.


class LRUCache {
    HashMap<Integer, Node> map;
    int capacity;
    Node head;
    Node tail;

    class Node {
        int key;
        int value;
        Node next, prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
   
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
    }

    //we check if the key exists in the map. If it does, we move the corresponding node to the 
    // head of the linked list and return its value. If it doesn't, we return -1.

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
    }

    //In put(), we check if the key exists in the map. If it does,we update its value and move the node to the head. 
    // If it doesn't, we create a new node and add it to the head. If the cache exceeds its capacity, 
    // we remove the least recently used node from both the linked list and the map.      
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
        } else {
            if (map.size() == capacity) {
                Node lastNode = tail.prev;
                removeNode(lastNode);
                map.remove(lastNode.key);
            }
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
        }
    }
}
