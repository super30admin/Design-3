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
                    A. We will use queue to store the data.
                    B. Do the recusion on the nestedList. if it is a integer then add to the queue
                    C. If not the do the recusion call with that list.
                    D. For next(), just poll the queue.
                    E. for hasNext, return is queue is not empty.

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