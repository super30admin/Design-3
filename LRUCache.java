import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    class Node {
        int key, value;
        Node next, prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    int capacity;
    Map<Integer, Node> map;

    Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;

        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);

        head.next = tail;
        tail.prev = head;
    }

    private void addToHead(Node node) {
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // TC : O(1)
    // SC : O(1)
    public int get(int key) {
        if(map.containsKey(key)) {
            Node temp = map.get(key);

            removeNode(temp);
            addToHead(temp);
            return temp.value;
        }
        return -1;
    }

    // TC : O(1)
    // SC : O(1)
    public void put(int key, int value) {

        if(map.containsKey(key)) {
            Node currentNode = map.get(key);
            currentNode.value = value;
            removeNode(currentNode);
            addToHead(currentNode);
        }else {

            // The Cache reached its upper bound. So, need to remove the least used node
            if(this.capacity == map.size()) {
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
