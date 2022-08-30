// Time Complexity : O(1)
// Space Complexity : O(n) //n is the capacity
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach

import java.util.*;

class LRUCache {
    class Node {
        int key, value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    private void remove(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    Node head;
    Node tail;
    Map<Integer, Node> map;
    int capacity;

    public LRUCache(int capacity) {
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        this.map = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) { // move it to the recently accessed.
        if (!map.containsKey(key))
            return -1;
        // get the node
        Node node = map.get(key);
        // remove from current position
        remove(node);
        // add to the start of the list
        addToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            if (capacity == map.size()) {
                // remove from end LRU
                Node tailPrev = tail.prev;
                remove(tailPrev);
                map.remove(tailPrev.key);
            }
            // create new node
            Node newNode = new Node(key, value);
            // add to head
            addToHead(newNode);
            // add to Map
            map.put(key, newNode);
        } else {
            // update the value
            Node node = map.get(key);
            node.value = value;
            // remove the node from position
            remove(node);
            // add to head
            addToHead(node);
        }
    }
}