// Time Complexity : O(1)
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

import java.util.*;

class LRUCache {
    
    int capacity;
    Map<Integer, DLLNode> map;
    DLL dll;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        dll = new DLL();
    }
    
    public int get(int key) {
        DLLNode node = map.get(key);
        
        if (node == null) {
            return -1;
        }
        dll.remove(node);
        dll.addLast(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        DLLNode node = map.get(key);
        
        // key is absent
        if (node == null) {
            if (map.size() == capacity) {
                // remove lru node from dll and map
                map.remove(dll.head.key);
                dll.remove(dll.head);
            }
            node = dll.addLast(key, value);
            map.put(key, node);
        }
        else {
            
            dll.remove(node);
            node.value = value; // update value
            dll.addLast(node);
        }
    }
}

// Doubly Linked List class
class DLL {
    
    DLLNode head;
    DLLNode tail;
    
    DLL() {
        head = null;
        tail = null;
    }
    
    public void addLast(DLLNode node) {
        
        if (head == null && tail == null) {
            head = tail = node;
        }
        else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }
    
    public DLLNode addLast(int key, int value) {
        
        DLLNode node = new DLLNode(key, value);
        addLast(node);
        return node;
    }
    
    public void remove(DLLNode node) {
        
        if (head == node && tail == node) {
            head = tail = null;
        } 
        else if (head == node) {
            head = head.next;
            head.prev = null;
            node.next = null;
        }
        else if (tail == node) {
            tail = tail.prev;
            tail.next = null;
            node.prev = null;
        }
        else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
        }
    }
}


class DLLNode {
    
    int key;
    int value;
    
    DLLNode prev;
    DLLNode next;
    
    DLLNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */