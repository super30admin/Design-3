import java.util.*;

// Approach: We use Doubly LinkedList and a HashMap of <Integer, Node> and implement
// additional void helper functions addToHead(node) and removeNode(node)
// Time: O(1) for put and get
// Space: O(capacity)

public class Lru_Cache {

    class Node {
        int key, val;
        Node prev, next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    private void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    private Node head, tail;
    private int capacity;
    private Map<Integer, Node> map;         // IMP: Map of <Integer, Node>

    public Lru_Cache(int capacity) {
        this.map = new HashMap();
        this.capacity = capacity;
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;           // update the value
            removeNode(node);
            addToHead(node);
        }
        else {
            if (map.size() == capacity) {
                // remove from tail side
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key, value);
            addToHead(newNode);         // add Node to LL
            map.put(key, newNode);      // add Node to Map
        }
    }
}