import java.util.HashMap;
import java.util.Map;

/**
 * Time Complexity: O(1)
 * Space Complexity: O(capacity)
 * Did this code successfully run on Leetcode: Yes
 * Any problem you faced while coding this: None

 * Approach:
 * (HashMap + doubly linked list)
 * Using hashmap for fast lookup
 * Using doubly linked list so that the least recently can be removed and most recently used can be added
 * Not using singly linked list - so that do not have to traverse the whole list
 */

public class LRUCache {
    // SC: O(Capacity)
    class Node {
        int key, value;
        Node prev, next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Node head, tail;

    private void addToHead(Node node) {
        Node temp = head.next;
        head.next = node;
        node.next = temp;
        node.prev = temp.prev;
        temp.prev = node;
    }

    // TC: O(1)
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // TC: O(1)
    Map<Integer, Node> cache;
    int capacity;
    public LRUCache(int capacity) {
        this.cache = new HashMap<>();
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    // TC: O(1)
    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            int value = node.value;
            removeNode(node);
            addToHead(node);
            return value;
        } 
        return -1;
    }
    
    // TC: O(1)
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            removeNode(node);
            node.value = value;
            addToHead(node);
        } else {
            if (cache.size() == capacity) {
                Node node = tail.prev;
                removeNode(node);
                cache.remove(node.key);
            }

            Node node = new Node(key, value);
            addToHead(node);
            cache.put(node.key, node);
        }
    }

    public static void main(String[] args) {
            // Your code here
            LRUCache obj = new LRUCache(3);
        obj.put(1,1);
        obj.put(2,1);
        obj.put(3,1);
        obj.put(4,1);
        obj.put(5,1);
        int param_1 = obj.get(5);
        System.out.println(param_1);
        assert(param_1 == 1);
        obj.put(6,1);
        param_1 = obj.get(1);
        System.out.println(param_1);
        assert(param_1 == -1);
    }
}