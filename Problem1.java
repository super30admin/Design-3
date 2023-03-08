import java.util.HashMap;
import java.util.Map;
/*
LRU Cache
approach: use map and a doubly linked list
 */
public class Problem1 {
    class Node {
        int val;
        int key;
        Node next;
        Node prev;

        public Node(int key, int val) {
            this.val = val;
            this.key = key;
            this.next = null;
            this.prev = null;
        }
    }

    int capacity;
    Node head, tail;
    Map<Integer, Node> map;
    public Problem1(int capacity) {
        this.capacity = capacity;
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next = tail;
        this.tail.prev = head;
        map = new HashMap<>();
    }

    private void remove(Node node) {
        if(node==null) return;
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node) {
        if(node==null) return;
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    //O(1)
    public int get(int key) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            remove(node);
            addToHead(node);
            return node.val;
        }
        return -1;
    }

    //O(1)
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            remove(node);
            addToHead(node);
        }
        else{
            Node node = new Node(key, value);
            if(map.size()==capacity) {
                map.remove(tail.prev.key);
                remove(tail.prev);
            }
            addToHead(node);
            map.put(key, node);
        }
    }
}
