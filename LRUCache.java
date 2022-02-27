import java.util.HashMap;
import java.util.Map;

// TC O(1)
// SC O(Capacity)
class LRUCache {
    class Node {
        int val;
        int key;
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
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    int capacity;
    Node head;
    Node tail;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        tail.prev = head;
        head.next = tail;
        map = new HashMap<Integer, Node>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node ret = map.get(key);
        removeNode(ret);
        addToHead(ret);
        return ret.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node ret = map.get(key);
            removeNode(ret);
            ret.val = value;
            addToHead(ret);
        } else {
            if (map.size() == capacity) {
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node node = new Node(key, value);
            addToHead(node);
            map.put(key, node);
        }
    }
}
