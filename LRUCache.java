//Time Complexity:  O(1)
//Space Complexity: O(1)
import java.util.HashMap;
import java.util.Map;

class Node {
    Node prev;
    Node next;
    int key;
    int val;
    public Node(int key, int val){
        this.key=key;
        this.val=val;
    }
}
public class LRUCache {
    Node head = new Node(0,0);
    Node tail = new Node(0,0);
    Map<Integer, Node> map = new HashMap<>();
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        else{
            Node node = map.get(key);
            remove(node);
            insert(node);
            return node.val;
        }

    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            remove(map.get(key));
        }
        if(map.size()==capacity){
            remove(tail.prev);
        }
        insert(new Node(key,value));
    }

    private void remove(Node node){ //O(1)
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private void insert(Node node){ //O(1)
        map.put(node.key, node);
        Node headNext = head.next;
        head.next = node;
        node.prev = head;
        headNext.prev = node;
        node.next = headNext;
    }
}
