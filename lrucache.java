/*
Time Complexity:O(1)
Space Complexity:O(capacity+1)
*/
class LRUCache {
    
    class DLinkedNode {
        int key;
        int val;
        DLinkedNode prev;
        DLinkedNode next;
		
        DLinkedNode(){}
        DLinkedNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    Map<Integer, DLinkedNode> cache = new HashMap<>();
    final int CAPACITY;
    int size;
    DLinkedNode head;
    DLinkedNode tail;
    
    public LRUCache(int capacity) {
        CAPACITY = capacity;
        size = 0;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.val;
    }
    
    public void put(int key, int val) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            if (size == CAPACITY) {
                deleteFromTail();
            } 
            node = new DLinkedNode(key, val);
            addToHead(node);
        } else {
            node.val = val;
            moveToHead(node);
        }
    }
    
    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        cache.remove(node.key);
        size--;
    }
    
    private void addToHead(DLinkedNode node) {
        head.next.prev = node;
        node.next = head.next;
        node.prev = head;
        head.next = node;
        cache.put(node.key, node);
        size++;
    }
    
    private void deleteFromTail() {
        DLinkedNode last = tail.prev;
        removeNode(last);
    }
    
    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }
    
}