import java.util.HashMap;
import java.util.Map;

/**
 * @author Vishal Puri
 * // Time Complexity : O(1)
 * // Space Complexity : O(N) where N is the capacity of the LRUCache
 * // Did this code successfully run on Leetcode : Yes
 * // Any problem you faced while coding this :
 */

public class LRUCache {
    class Node {

        int key;
        int val;
        Node prev;
        Node next;

        public Node(){

        }

        public Node(int key){
            this.key = key;
        }

        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    //head and tail node are just to avoid null checks
//Double linked list structure for storing cache data
    Node head = new Node();
    Node tail = new Node();
    int capacity;

    //To search if key exsist in time complexity O(1)
    Map<Integer, Node> map = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            int value = node.val;
            remove(node);
            insert(node);
            return value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            remove(node);
        } else if(map.size() == capacity){
            map.remove(tail.prev.key);
            remove(tail.prev);
        }
        insert(new Node(key, value));
    }

    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void insert(Node node){
        map.put(node.key , node);
        Node headNext = head.next;
        head.next = node;
        node.prev = head;
        headNext.prev = node;
        node.next = headNext;

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */