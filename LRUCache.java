//Time Complexity: O(1)
//Space Complexity: O(capacity)

class LRUCache {

    class Node {
        int key, value;
        Node prev, next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Node head, tail;
    HashMap<Integer, Node> map;
    int capacity;

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

    public LRUCache(int capacity) {
        map = new HashMap<>(); // initialising the Hashmap
        this.capacity = capacity;
        head = new Node(-1, -1); // initialising DLL with dummy head and tail nodes
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;

    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        removeNode(node); // removing node from current position in DLL & adding it to the head as it is
                          // accessed.
        addToHead(node); // most recently used --> head, least recently used-->tail
        return node.value;

    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value; // updating the value of the key that is already existing.
            removeNode(node);// removing node from current position in DLL & adding it to the head as it is
                             // accessed.
            addToHead(node); // most recently used --> head, least recently used-->tail
            return;
        }
        if (map.size() == capacity) { // if key isnt present check for capacity, if exceeded, remove node at tail.
            Node tailPrev = tail.prev; // least recently used-->tail
            removeNode(tailPrev); // removing the least recently used node from the DLL
            map.remove(tailPrev.key); // removing the least recently used node entry from the HashMap
        }
        Node node = new Node(key, value); // adding the new key,value to the DLL and HashMap.
        addToHead(node);
        map.put(key, node);

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */