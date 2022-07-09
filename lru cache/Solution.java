// Time Complexity: O(1)
// Space Complexity: O(n)
// Single Linked List didnt work. Why? -> 1 test case passed but for others I got TLE error
// Copied DLL + HashMap sol.
class LRUCache {
    
    private class DNode{
        int key;
        int val;
        DNode prev;
        DNode next;
        
    }
    
    private Map<Integer, DNode> hashtable = new HashMap<Integer, DNode>();
    private DNode head, tail;
    private int totalItemsInCache;
    private int capacity;

    public LRUCache(int capacity) {
        totalItemsInCache = 0;
        this.capacity = capacity;
        
        head = new DNode();
        head.prev = null;
        
        tail = new DNode();
        tail.next = null;
        
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        DNode node = hashtable.get(key);
        if(node == null)
            return -1;
        
        moveToHead(node); //the most recent node move to the head
        
        return node.val;
    }
    
    public void put(int key, int value) {
        DNode node = hashtable.get(key);
        if(node == null){
            DNode newNode = new DNode();
            newNode.key = key;
            newNode.val = value;
            
            hashtable.put(key, newNode);
            addNode(newNode); //add newNode to the doubly linked list
            
            totalItemsInCache++;
            
            if(totalItemsInCache > capacity)
                removeLRUItem(); //remove LRU item from hashtable & linked list
        }else{
            node.val = value;
            moveToHead(node);
        }
    }
    
    private void removeLRUItem(){
        DNode tail = popTail();
        hashtable.remove(tail.key);
        totalItemsInCache--;
    }
    
    private void moveToHead(DNode node){
        removeNode(node);
        addNode(node);
    }
    
    private void addNode(DNode node){
        node.prev = head;
        node.next = head.next;
        
        head.next.prev = node;
        
        head.next = node;
    }
    
    private void removeNode(DNode node){
        DNode savePrev = node.prev;
        DNode saveNext = node.next;
        
        savePrev.next = saveNext;
        saveNext.prev = savePrev;
    }
    
    private DNode popTail(){
        DNode itemBeingRemoved = tail.prev;
        removeNode(itemBeingRemoved);
        return itemBeingRemoved;
    }
}
