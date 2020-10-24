// Time Complexity : O(1) for get() and put() operations 
// Space Complexity : O(capacity) for cache HashMap  
// Did this code successfully run on Leetcode : yes 
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
// this would be a hashmap with doubly linkedlist implementation

class LRUCache {
    class DoublyLinkedNode{
        int key;
        int value;
        DoublyLinkedNode next = null;
        DoublyLinkedNode prev = null;

        public DoublyLinkedNode(int key, int value){
            this.key = key;
            this.value = value;
        }

        public DoublyLinkedNode(){
        }
    }
    
    DoublyLinkedNode head;
    DoublyLinkedNode tail;
    HashMap<Integer, DoublyLinkedNode> cache;
    int capacity;
    
    private void removeNode(DoublyLinkedNode node){
        DoublyLinkedNode prev = node.prev;
        DoublyLinkedNode next = node.next;
        
        prev.next = next;
        next.prev = prev;
        
        node.prev = null;
        node.next = null;
    }
    
    private void addToHead(DoublyLinkedNode node){
        DoublyLinkedNode cur = head.next;
        head.next = node;
        node.prev = head;
        
        node.next = cur;
        cur.prev = node;
    }
    
    public LRUCache(int capacity) {
        if(capacity>0){
            cache = new HashMap<Integer, DoublyLinkedNode>(capacity);
            head = new DoublyLinkedNode();
            tail = new DoublyLinkedNode();
            head.prev = null;
            head.next = tail;
            tail.prev = head;
            tail.next = null;
            this.capacity = capacity;
        }
    }
    
    public int get(int key) {
        DoublyLinkedNode node = cache.get(key);
        if(node!=null){
            removeNode(node);
            addToHead(node);
            return node.value;
        }
        else{
            return -1;
        }
    }
    
    public void put(int key, int value) {
        DoublyLinkedNode node = cache.get(key);
        if(node!=null){
            node.value = value;
            removeNode(node);
            addToHead(node);
        }
        else{
            if(cache.size()==capacity){
                int eldestItemKey = tail.prev.key;
                removeNode(tail.prev);
                cache.remove(eldestItemKey);
            }
            
            DoublyLinkedNode newEntry = new DoublyLinkedNode(key, value);
            addToHead(newEntry);
            cache.put(key, newEntry);
        }
        
    }
}