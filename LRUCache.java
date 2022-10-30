// Time Complexity: get -> O(1), put -> O(1)
// Space Complexity: O(1), as capacity never exceeds the value defined.
class LRUCache {

    class Node {
        int key;
        int value;
        Node next;
        Node prev;

        public Node (int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    int capacity;
    Node head, tail;
    HashMap<Integer, Node> map;

    private void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        head.next = node;
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }

    public int get(int key) {
        // key not present
        if (!map.containsKey(key)) return -1;
        // else key is present in the hashmap
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        // if key exists, don't worry about capacity but just update the value
        if (map.containsKey(key)) {
            // remove from old location and add node to hte head of hte linked list
            Node temp = map.get(key);
            removeNode(temp);
            addToHead(temp);
            temp.value = value;
            return;
        }
        // Key does not exist, check if capcity is reached or not

        if (capacity == map.size()) {
            // If capacity is reach, remove node at the tail
            Node prevNode = tail.prev;
            removeNode(prevNode);
            map.remove(prevNode.key);
        }
        // add node irrespective
        Node newNode = new Node(key, value);
        addToHead(newNode);
        map.put(key, newNode);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
