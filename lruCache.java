    /*  Explanation
    # Leetcode problem link : https://leetcode.com/problems/lru-cache/
    Time Complexity for operators : o(1) 
    Extra Space Complexity for operators : o(1) 
    Did this code successfully run on Leetcode : Yes
    Any problem you faced while coding this : No
# Your code here along with comments explaining your approach
        # Basic approach : 

        # Optimized approach:
                              
             approach   
                    A. In order to solve this problem, in o(1) time complexity of put and get operation,
                       we need to use hashmap wirh node added as a doubly linkedlist.
                    B. We will first create a doubly linkedlist as mentioed in the below.
                    C. then add operator where we willa dd our element to the last in the linkedlist.
                    D. In remove, we will just  point  out the before and after node to each other.
                    E. In update, we will remove the element first  and then add it to the end.
                    F. in put method, first check if element is present in hashmap or not.
                       If not, then simply put it into hashmap and add to the linkedlist.
                       If yes, then update() methoda gets invoked.
                    G. in  get  method, first check if element is present in hashmap or not.
                       If not, then simply return -1;
                       If yes, move element to the end as it is recently used. so update() operator and 
                       return the value of that updated node.

       */


class LRUCache {
    
    class DoublyNode{
        int key;
        int val;
        DoublyNode next, prev;
        
        public DoublyNode(int key, int val){
            this.key = key;
            this.val = val;
            
            prev = null;
            next = null;
        }
        
    }
    
    private DoublyNode head, tail;
    private HashMap<Integer,DoublyNode> hm;
    private int capacity;
    private int size;

    private void add(DoublyNode node){
        DoublyNode temp = tail.prev;
        tail.prev = node;
        node.prev = temp;
        temp.next = node;
        node.next = tail;   
    }
    
    private void remove(DoublyNode node){
        DoublyNode before = node.prev;
        DoublyNode after = node.next;
        
        before.next = after;
        after.prev = before;
    }
    
    public LRUCache(int capacity) {
        head = new DoublyNode(0,0);
        tail = new DoublyNode(0,0);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        
        hm = new HashMap<>();
        size = 0;
        
    }
    
    private void update(DoublyNode node){
        remove(node);
        add(node);
    }
    
    public int get(int key) {
        DoublyNode node = hm.get(key);

        if(node == null)
            return -1;
        else{
            update(node);
        }
        
        return node.val;
    }
    
    public void put(int key, int value) {
        DoublyNode node = hm.get(key);
        
        if(node == null){
            
            node = new DoublyNode(key,value);
            
            hm.put(key,node);
            size +=1;
            
            add(node);
            
        }else{
            node.val = value;
            update(node);
        }
        
        if(size>capacity){
           DoublyNode temp = head.next;
            remove(temp);
            size -= 1;
            hm.remove(temp.key);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */