import java.util.HashMap;
import java.util.Map;

class LRUCache {
    class Node {
        int key, val;
        Node prev, next;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    Map<Integer, Node> map;
    Node head, tail;
    int capacity;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    private void addToHead(Node node) {
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
        node.prev = head;
    }

    private void removeLRU() {
        Node node = tail.prev;
        map.remove(node.key);
        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
            map.put(key, node);
            return;
        }
        if(capacity == map.size()) {
            removeLRU();
        }
        Node node = new Node(key, value);
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
