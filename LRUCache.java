// Time Complexity : O(1); 
// Space Complexity : O(n) ; n - > no. of keys in hashmap (same as no. of values) 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Approach:
// least recently used cache implementation requires a datastructure in which we can perform fast searches, therefore using a map.
// To maintain the order, a data structure is required which can hold reference to previous and next element. therefore, using a double linked list.

class LRUCache {
    
    class DNode {
        int key;
        int val;
        DNode prev;
        DNode next;
        
        public DNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    private DNode head;
    private DNode tail;
    private int capacity;
    private Map<Integer, DNode> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        
        head = new DNode(-1,-1);
        tail = new DNode(-1,-1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(map.containsKey(key)) {
            DNode curr = map.get(key);
            removeNode(curr);
            addInFront(curr);
            return curr.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        DNode curr = new DNode(key, value);
        if(!map.containsKey(key)) {
            if(map.size() == capacity) {
                removeFromEnd();
            }
        }
        else {
            removeNode(map.get(key));
        }
        addInFront(curr);
        map.put(key, curr);
    }
    
    private void removeNode(DNode node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    private void addInFront(DNode node) {
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        head.next = node;
    }
    
    private void removeFromEnd() {
        int key = tail.prev.key;
        removeNode(tail.prev);
        map.remove(key);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

