// Time Complexity : O(1)
// Space Complexity : O(n) where n is the capacity
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// Create a doubly linked list, implement its methods to add and remove nodes
// We will also create a HashMap, of key as int and value as node type
// We will have extra nodes head and tail for convenience
// Our logic would be when we get an item, it will be moved to the start of linked list
// When we add an item we will change its position to beginning if it exists
// If it doesn't exit we will create a new node and add it the linke list
// If the capacity is full then we will an element before tail.
class LRUCache {
    DLLNode head, tail;
    HashMap<Integer, DLLNode> hm = new HashMap<>();
    int count;
    int capacity;
    
    public void addNode(DLLNode node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    public void removeNode(DLLNode node){
        DLLNode prevN = node.prev;
        DLLNode nextN = node.next;
        prevN.next = nextN;
        nextN.prev = prevN;
    }
    public LRUCache(int capacity) {
        this.head = new DLLNode();
        this.tail = new DLLNode();
        this.count = 0;
        this.capacity = capacity;
        head.prev = null;
        head.next = tail;
        tail.next = null;
        tail.prev = head;
    }
    
    public int get(int key) {
        DLLNode node = hm.get(key);
        if(node == null)
            return -1;
        this.removeNode(node);
        this.addNode(node);
        return node.value;
        
    }
    public void put(int key, int value) {
        DLLNode node = hm.get(key);
        if(node == null){ 
            count++;
            DLLNode newNode = new DLLNode();
            newNode.key = key;
            newNode.value = value;
            hm.put(key,newNode);
            if(count > this.capacity){
                DLLNode toRemove = tail.prev;
                toRemove.prev.next = tail;
                tail.prev = toRemove.prev;
                hm.remove(toRemove.key);
                count--;
            }
            this.addNode(newNode);
        }
        else{
            node.value = value;
            hm.put(key,node); 
            this.removeNode(node);
            this.addNode(node);
        }   
    }
}
class DLLNode{
    DLLNode next;
    DLLNode prev;
    int value;
    int key;
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */