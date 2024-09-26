/*
 * Time Complexity : O(1) for all operations
 * Space Complexity : O(capacity) -> for storing hashMap and doubly linkedlist
 * 
 */
class LRUCache {

    class ListNode{
        int key;
        int value;
        ListNode next;
        ListNode prev;
        
        ListNode(int key, int value){
            this.key = key;
            this.value = value;
        }
        
    }
    
    public void removeNode(ListNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    public void insertNode(ListNode node){
        node.next = head.next;
        head.next.prev = node;
        
        head.next = node;
        node.prev = head;
    }
    
    
    Map<Integer, ListNode> hashMap;
    ListNode head;
    ListNode tail;
    int capacity;
    
    public LRUCache(int capacity) {
        hashMap = new HashMap<>();
        this.head = new ListNode(-1, -1);
        this.tail = new ListNode(-1, -1);
        
        this.head.next = this.tail;
        this.tail.prev = this.head;
        
        this.capacity = capacity;
    }
    
    public int get(int key) {
        
        if(!hashMap.containsKey(key)){
            return -1;
        }
        
        removeNode(hashMap.get(key));
        insertNode(hashMap.get(key));
        return hashMap.get(key).value;
        
    }
    
    public void put(int key, int value) {
        
        if(hashMap.containsKey(key)){
            ListNode node = hashMap.get(key);
            node.value = value;
            removeNode(node);
            insertNode(node);
        }else{
           if(hashMap.size() == this.capacity){
               ListNode leastUsed = tail.prev;
                removeNode(leastUsed);
                hashMap.remove(leastUsed.key);
            }
        
            ListNode temp = new ListNode(key, value);
            hashMap.put(key, temp);
            insertNode(temp);
            
        }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */