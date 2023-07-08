// Time Complexity : get(key): O(1)     put(key,value):O(1)
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach: 
// The logic of the code revolves around using a combination of a HashMap and a doubly linked list to efficiently store and manage the cache.The HashMap provides constant-time lookup for key-value pairs,while the doubly linked list keeps track of the order of access.
// When retrieving a value(get operation),the code first checks if the key exists in the cache.If it does,it retrieves the corresponding node from the HashMap and moves it to the front of the linked list.By moving it to the front,the node becomes the most recently accessed item,ensuring that the least recently used items remain towards the end of the list.Finally,it returns the value associated with the key.
// When inserting a new key-value pair(put operation),the code checks if the key already exists in the cache.If it does,it updates the value and moves the corresponding node to the front of the linked list,following the same logic as the get operation.If the key does not exist,the code creates a new node with the given key and value,adds it to the front of the linked list,and updates the HashMap to include the new key-value pair.If the cache is already at its maximum capacity,the code removes the least recently used item from the end of the linked list and the corresponding entry from the HashMap before inserting the new item.

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    class Node {
        Node prev;
        Node next;
        int key, val;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node head;
    private Node tail;
    Map<Integer, Node> map = new HashMap<>();
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        remove(node);
        addToHead(node);
        return node.val;
    }

    private void remove(Node curr) {
        curr.next.prev = curr.prev;
        curr.prev.next = curr.next;
    }

    private void addToHead(Node curr) {
        curr.prev = head;
        curr.next = head.next;
        head.next = curr;
        curr.next.prev = curr;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            remove(node);
            addToHead(node);
        } else {
            if (map.size() == capacity) {
                Node toRemove = tail.prev;
                remove(toRemove);
                map.remove(toRemove.key);
            }
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        // Perform cache operations
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // Output: 1
        cache.put(3, 3);
        System.out.println(cache.get(2)); // Output: -1
        cache.put(4, 4);
        System.out.println(cache.get(1)); // Output: -1
        System.out.println(cache.get(3)); // Output: 3
        System.out.println(cache.get(4)); // Output: 4
    }
}
