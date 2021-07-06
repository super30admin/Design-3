import java.util.HashMap;


/*
TC : O(1) for get and put operations
SC : O(N) for the hashmap and the Doubly LinkedList
 */

/**
 * We store a hashmap with key as the key and the value as the address of the Node for O(1) access of the node.
 * Also, we maintain a doubly linked list so that the removal of any node is O(1).
 * We also maintain the head and tail of the doubly linked list so that we can add the most recent node at the start of the DLL
 * We need the tail so that we can remove tail.prev Node when we hit the capacity of the cache.
 */
public class LRUCache {

    class Node {
        int key;
        int val;
        Node next;
        Node prev;

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
        node.prev.next = node.next;
        node.next.prev = node.prev;

    }

    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
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
            node.val = value;
            removeNode(node);
            addToHead(node);

        } else {
            if (capacity == map.size()) {
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }

            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);

        }
    }
}
