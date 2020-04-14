// Time Complexity : O(1) for all functions
// Space Complexity :
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : After class solution


// Your code here along with comments explaining your approach
//used doubly linked list to keep track of LRU logic
//used hashmap for O(1) lookup

// DoublyLinkedList Node
class Node {
    int key, value;
    Node prev, next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class LRUCache {

    int capacity;
    Map<Integer, Node> map;
    Node head, tail;

    // constructor
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        
        head = new Node(0, 0);
        tail = new Node(0, 0);
        
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node cursor = map.get(key);
        if (cursor != null) {
            remove(cursor);
            insertFirst(cursor);
            return cursor.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node cursor = map.get(key);
        if (cursor != null) {
            cursor.value = value;
            remove(cursor);
            insertFirst(cursor);
        }else{
            cursor = new Node(key, value);
            insertFirst(cursor);
            map.put(key, cursor);

            if (map.size() > capacity) {
                cursor = tail.prev;
                remove(cursor);
                map.remove(cursor.key);
            } 
        }

        
    }

    // helper methods
    public void insertFirst(Node cursor) {
        Node first = head.next;
        cursor.prev = head;
        cursor.next = first;
        first.prev = cursor;
        head.next = cursor;
    }

    public void remove(Node cursor) {
        Node prevNode = cursor.prev;
        Node nextNode = cursor.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }
}