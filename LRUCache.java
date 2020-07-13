class LRUCache {

// Time Complexity : O(1)  for both put,get operations
// Space Complexity : O(capacity)  
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


 /*
 1. Use HashMap with (key,Node) Node- is the reference for (key,value)
        DoublyLinkedList with nodes as (key,value)
 2. Use HashMap to keep track of pages already present and to point the Node directly to DLL.
 3. In DLL, maintain LRU at tail.
 
 */
    
    
    class Node{
        int key; int value;
        Node prev; Node next;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    
    Node head; Node tail;
    int capacity;
    HashMap<Integer,Node> hm;

    public LRUCache(int capacity) {
        hm = new HashMap<>();
        this.capacity = capacity;
        tail = new Node(-1,-1);
        head = new Node(-1,-1);
        tail.prev = head;
        head.next = tail;   
    }
    
    private void removeNode(Node n){
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }
    
    private void addToHead(Node n){
        n.prev = head;
        n.next = head.next;
        n.next.prev = n;
        head.next = n;
    }
    
    public int get(int key) {
        if(hm.containsKey(key)){
            Node n = hm.get(key);
            removeNode(n);
            addToHead(n);
            return n.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(hm.containsKey(key)){
            Node  n = hm.get(key);
            removeNode(n);
            addToHead(n);
            n.value = value;
        }
        else{
            Node n = new Node(key,value);
                if(hm.size() == capacity){
                    Node tailNode = tail.prev;
                    removeNode(tailNode);
                    hm.remove(tailNode.key);
                }
               hm.put(key,n);
               addToHead(n);
        }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */