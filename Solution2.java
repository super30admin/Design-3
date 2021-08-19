//Time complexity:
//put() - O(1)
//get() - O(1)
//Space complexity: 
//O(capacity)

import java.util.*;

class LRUCache {
    class ListNode {
        int key;
        int value;
        ListNode prev;
        ListNode next;
        
        public ListNode() {}
        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private void addToHead(ListNode node) {
        ListNode next = head.next;
        head.next = node;
        node.prev = head;
        node.next = next;
        next.prev = node;
    }

    private void removeNode(ListNode node) {
        ListNode prev = node.prev;
        ListNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private void moveToHead(ListNode node) {
        removeNode(node);
        addToHead(node);
    }

    private ListNode removeFromTail() {
        ListNode node = tail.prev;
        removeNode(node);
        return node;
    }

    int size;
    int capacity;
    ListNode head, tail; 
    HashMap<Integer, ListNode> map; 
    
    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new ListNode(); 
        tail = new ListNode();
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if(map.containsKey(key)) {
            ListNode node = map.get(key); 
            moveToHead(node); 
            return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            ListNode node = map.get(key); 
            node.value = value;
            moveToHead(node); 
        } else {
            ListNode node = new ListNode(key, value); 
            map.put(key, node);
            addToHead(node); 
            
            if(++size > capacity) {
                ListNode removeNode = removeFromTail(); 
                map.remove(removeNode.key);
                --size;
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