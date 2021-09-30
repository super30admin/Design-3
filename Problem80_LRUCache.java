// Time Complexity : O(1)
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode : Yes
// Three line explanation of solution in plain english

// Your code here along with comments explaining your approach
import java.util.*;
class Node {
    int key;
    int val;
    Node next;
    Node prev;
    Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

class LRUCache {
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    HashMap<Integer, Node> map;
    int capacity;
    Node head; 
    Node tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
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
        } else {
            if(capacity == map.size()) {
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