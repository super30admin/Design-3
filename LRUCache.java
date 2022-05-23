// Time Complexity : O(1)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class LRUCache {

    class ListNode {
        ListNode prev;
        ListNode next;
        int key, value;
        
        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    Map<Integer, ListNode> map;
    int capacity;
    ListNode head, tail;
    public LRUCache(int capacity) {
        map = new HashMap();
        this.capacity = capacity;
        
        head = new ListNode(-1, -1);
        
        tail = new ListNode(-1, -1);
        
        head.next = tail;
        tail.prev = head;
    }
    
    private void insert(ListNode node) {
        
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;

    }
    
    private ListNode removeTail() {
        
        ListNode prev = tail.prev;
        
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
        
        return prev;
        
    }
    
    private void delete(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void moveToHead(ListNode node) {
        delete(node);
        insert(node);
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
            
            insert(node);
            
            map.put(key, node);
            
            if(capacity < map.size()) {
                ListNode tail = removeTail();
                map.remove(tail.key);
            }
        }
    }
}