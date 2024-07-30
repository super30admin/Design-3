// Time Complexity:
// - get: O(1)
// - put: O(1)
// Space Complexity: O(capacity)

class Node {
    Node previous, next;
    int key, value;
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache {
    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);
    Map<Integer, Node> hashMap = new HashMap<>();
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.previous = head;
    }

    public int get(int key) {
        if (hashMap.containsKey(key)) {
            Node node = hashMap.get(key);
            remove(node);
            insert(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (hashMap.containsKey(key)) {
            remove(hashMap.get(key));
        }
        if (hashMap.size() == capacity) {
            remove(tail.previous);
        }
        insert(new Node(key, value));
    }

    private void insert(Node node) {
        hashMap.put(node.key, node);
        node.next = head.next;
        node.next.previous = node;
        node.previous = head;
        head.next = node;
    }

    private void remove(Node node) {
        hashMap.remove(node.key);
        node.previous.next = node.next;
        node.next.previous = node.previous;
    }
}
