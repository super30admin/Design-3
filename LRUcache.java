
//Time Complexity: O(1)
//Space Complexity: O(N);

import java.util.*;

class LRUCache {
    class ListNode{
        int key, val;
        ListNode next, prev;
        
        public ListNode(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    Map<Integer, ListNode> map;
    int capacity;
    ListNode head, tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    private void removeNode(ListNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void addToHead(ListNode node){
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        else{
            ListNode node = map.get(key);
            removeNode(node);
            addToHead(node);
            return node.val;
        }
    }
    
    public void put(int key, int value) {
        //key present
        if(map.containsKey(key)){
            ListNode node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }
        
        //not present
        else{
            if(capacity == map.size()){
                ListNode remNode = tail.prev;
                removeNode(remNode);
                map.remove(remNode.key);
            }
            ListNode newNode = new ListNode(key, value);
            map.put(key, newNode);
            addToHead(newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */