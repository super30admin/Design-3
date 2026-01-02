//TC - get() - O(1) put() - O(1)
//SC - O(c) where c is the capacity


/*
Use HashMap<key, node> for O(1) access and a doubly-linked list to maintain recency order.
On get/put, move the node to the head (most recently used); the tail.prev is always the least recently used.
When capacity is full, evict tail.prev from both the list and the map, then insert the new node at head.
*/

import java.util.HashMap;

public class Problem2 {

    class ListNode {
    int key;
    int value;
    ListNode prev;
    ListNode next;

    public ListNode(int key, int value, ListNode prev, ListNode next) {
        this.key = key;
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}

class LRUCache {
    HashMap<Integer, ListNode> hmap;
    int capacity;
    ListNode head;
    ListNode tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        hmap = new HashMap<>();
        head = new ListNode(-1,-1, null, null);
        tail = new ListNode(-1,-1, head, null);
        head.next = tail; 
    }
    
    public int get(int key) {
        if(hmap.containsKey(key)) {
            ListNode res = hmap.get(key);
            remove(res);
            addToHead(res);
            return res.value;
        }

        return -1;
    }
    
    public void put(int key, int value) {
        int size = hmap.size();
        if(hmap.containsKey(key)) {
                ListNode temp = hmap.get(key);
                hmap.remove(key);
                temp.value = value;
                hmap.put(key, temp);
                remove(temp);
                addToHead(temp);
        } else {
            if(size < capacity) {
                ListNode newTemp = new ListNode(key, value, null, null);
                hmap.put(key, newTemp);
                addToHead(newTemp);
            } else {
                ListNode lru = tail.prev;
                hmap.remove(lru.key);
                remove(tail.prev);
                ListNode newTemp = new ListNode(key, value, null, null);
                hmap.put(key, newTemp);
                addToHead(newTemp);
            }
        }
        
    }

    private void addToHead(ListNode node) {
        ListNode nextNode = head.next;
        node.next = nextNode;
        nextNode.prev = node;
        head.next = node;
        node.prev = head;
    }

    private void remove(ListNode node) {
        ListNode prevNode = node.prev;
        prevNode.next = node.next;
        node.next.prev = prevNode;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
