//Leetcode 146. LRU Cache
//Time Complexity: O(1) for get and put method both
//Space Complexity; O(n) // Size of the doubly linked list
class LRUCache {
    class Node {
        Node prev;
        Node next;
        int key;
        int val;
        Node(int key, int val){
            this.key = key;
            this.val= val;
        }
    }
    
   
    HashMap<Integer, Node> hm;
    int capacity=0;
    Node head;
    Node tail;
    int currSize;
    public LRUCache(int capacity) {
        hm = new HashMap<>();
        head= new Node(-1,-1);
        tail=new Node(-1,-1);
        head.next= tail;
        tail.prev= head;
        this.currSize=0;
        this.capacity = capacity;
    }
     public void addToCache(Node n){
        n.next = head.next;
        n.next.prev = n;
        head.next = n;
        n.prev = head;
        currSize = currSize + 1;  
        if (currSize > capacity){
            hm.remove(tail.prev.key);
            removeNode(tail.prev);
            
        }
    }
    public void removeNode(Node n){
            currSize--;
            Node pre= n.prev;
            Node nex= n.next;
            pre.next=nex;
            nex.prev=pre;
           
  
    }
    public int get(int key) {
        if(hm.containsKey(key)){
           Node n= hm.get(key);
            removeNode(n);
           addToCache(n);
           return n.val;
       } 
        else{
           return -1;
       }
    }
    
    public void put(int key, int value) {
        if(hm.containsKey(key)){
            Node n= hm.get(key);
            n.val=value;
            hm.put(key, n);
            removeNode(n);
            addToCache(n);
            return;
       } 
        else{
            Node n= new Node(key, value);
            hm.put(key, n);
            addToCache(n);
            
       }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */