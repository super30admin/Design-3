class LRUCache {
    class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public void addToHead(Node node) { // O(1)
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    public void removeNode(Node node) { // O(1)
        node.prev.next = node.next;
        node.next.prev = node.prev;

    }
    // estabilish doubly linked list
    Node head;
    Node tail;
    // HashMap
    HashMap<Integer, Node> map;
    int capacity;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;

        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node != null) {
            int result = node.val;
            removeNode(node);
            addToHead(node); // MRU -> most recently used
            return result;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            // Remove the node from current position
            // Add the node to Head
            removeNode(node);
            addToHead(node);
        } else {
            if (capacity == map.size()) {
                // full - remove LRU node
                Node tailPrev = tail.prev;

                // remove node from DLL and map
                removeNode(tail.prev);
                map.remove(tailPrev.key);
            }
            // Add node to head and map
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