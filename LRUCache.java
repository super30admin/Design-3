// Time Complexity : O(1) for operations
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

public class LRUCache {

    class Node {
        int key, val;
        Node next, prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    Node head, tail;
    int capacity;
    HashMap<Integer, Node> map;

    public void removeNode(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    private void addToHead(Node n) {
        n.next = head.next;
        n.prev = head;
        n.next.prev = n;
        head.next = n;
    }

    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Node n = map.get(key);
        removeNode(n);
        addToHead(n);
        return n.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            n.val = value;
            removeNode(n);
            addToHead(n);
            return;
        }
        if (capacity == map.size()) {
            map.remove(tail.prev.key);
            removeNode(tail.prev);

        }
        Node newNode = new Node(key, value);
        map.put(key, newNode);
        addToHead(newNode);
    }
}
