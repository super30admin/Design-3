// Time Complexity : O(1) for both get() and put()
// Space Complexity : O(n) for map and O(n) for DLL n :capacity
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

import java.util.HashMap;

public class LRU {

    class Node{
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    // HashMap for O(1) for get(), DLL for LRU, add, remove O(1)

    HashMap<Integer, Node> map;

    Node head;
    Node tail;

    int capacity;
    int size;

    public LRU(int capacity) {

        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(0,0);
        tail = new Node(0,0);

        head.prev = null;
        head.next = tail;

        tail.prev = head;
        tail.next = null;

        size = 0;
    }

    public void deleteNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void addToHead(Node node){
        node.next = head.next;
        node.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    public int get(int key) {
        if(map.get(key) != null){
            Node node = map.get(key);
            int output = node.value;
            deleteNode(node);
            addToHead(node);
            return output;
        }

        return -1;
    }

    public void put(int key, int value) {
        // check if key is present in the map
        if(map.get(key) != null){
            Node node = map.get(key);
            node.value = value;
            deleteNode(node);
            addToHead(node);
        } else {
            Node node = new Node(key, value);
            map.put(key, node);
            if(size < capacity){
                size++;
                addToHead(node);
            } else {
                map.remove(tail.prev.key);
                deleteNode(tail.prev);
                addToHead(node);
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */