class LRUCache {
    /*
        TC - O(1) for both get and put operations.
        SC - O(capacity) for storing the cache entries.
        hashmap maps each key to its corresponding node in the doubly linked list and allows O(1) lookup.
        Doubly Linked List maintains the usage order of cache entries.
        The head represents the most recently used (MRU) item.
        The tail represents the least recently used (LRU) item.

     */
    class Node {
        int key, val;
        Node next, prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    private Node head, tail;
    private int capacity;
    private HashMap<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = tail;
        this.tail.prev = head;
        this.map = new HashMap<>();
    }
    
    public int get(int key) {
        // If the key is not present, return -1.
        if (!map.containsKey(key)) {
            return -1;
        }
        // Otherwise, retrieve the node from the HashMap.
        Node node = map.get(key);
        // Remove it from its current position in the list.
        removeNode(node);
        // Move it to the front (head), marking it as the most recently used.
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        // If the key already exists update its value.
        // and move the node to the front since it was just accessed.
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        } else {
            // If the map is full, remove the node just before the tail 
            // (the least recently used node) and delete it from the HashMap.
            Node node = new Node(key, value);
            if (map.size() == capacity) {
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
                tailPrev.next = null;
                tailPrev.prev = null;
            }
            // create a new node, insert it at the front, and add it to the HashMap.
            addToHead(node);
            map.put(key, node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */