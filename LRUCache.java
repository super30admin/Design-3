// Time Complexity : O(1) for operation get and put. 
// Space Complexity : O(N) element in the LinkedList and map;

// Did this code successfully run on Leetcode :yes. 

// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
//the code uses linkedlist and hashmap. Double LinkedList to keep track of Least recently used(tail) and most recently used (head)
// it used  a map with a key and a reference to the node in linkedlist. We use linkedlist to keep order and know what is the last recently
// used and we use map to get the reference in amortized o(1) and delete and put in O(1).
// We use a double linkedlist in order to avoid o(n) in remove/delete operation
//Success
//Details 
//Runtime: 12 ms, faster than 94.95% of Java online submissions for LRU Cache.
//Memory Usage: 47.7 MB, less than 100.00% of Java online submissions for LRU Cache.

class LRUCache {

    class Node{
        int val;
        int key;
        Node next;
        Node prev;
        public Node(int key,int val){
            this.key=key;
            this.val=val;
        }
    }
    
    private int capacity;
    private int count;
    private Map<Integer,Node> map;
    private Node head;
    private Node tail;
    public LRUCache(int capacity) {
       map = new HashMap<>();
       head= new Node(-1,-1);
       tail=new Node(-1,-1);
       this.capacity=capacity;
       head.next=tail;
       tail.prev=head;
    }
         
    private void remove(Node n){
       n.next.prev=n.prev;
       n.prev.next=n.next; 
    }
    
    private void addToHead(Node n){
        n.next=head.next;
        n.prev=head;
        head.next=n;
        n.next.prev=n;  
    }

    public int get(int key) {
       if (!map.containsKey(key))
           return -1;
       Node n= map.get(key);
       remove(n);
       addToHead(n);
       return n.val;
    }

    public void put(int key, int value) {
        //case value exist
        if (map.containsKey(key)){
           Node n= map.get(key);
            n.val=value;
           remove(n);
           addToHead(n);
           
                
        }else{
           if (capacity==map.size()){
               Node nodePrev= tail.prev;
               
               remove(nodePrev);
               map.remove(nodePrev.key);
           } 
           Node n= new Node(key,value); 
           addToHead(n);
           map.put(key,n);    
        }
    }    
 

}

//another implementation less efficient with priorityQueue.

import java.time.Instant;
import java.util.*;
class LRUCache {

    class LRUItem implements Comparable<LRUItem>{
        private int key;
        private int val;
        private long priority;
        public LRUItem(int key, int val, long priority){
            this.key=key;
            this.val=val;
            this.priority=priority;
        }

        public int getKey(){return this.key;}
        public int getVal(){return this.val;}
        public long getPriority(){ return priority;}
        public void setPriority(long priority){this.priority=priority;}

        @Override
        public int hashCode(){
            return Objects.hash(key,val,priority); //import java.utils.Objects; s
        }

        @Override
        public boolean equals(Object o){
            if (this==o) return true;
            if (o==null || this.getClass()!=o.getClass()) return false;
            LRUItem item = (LRUItem) o;
            return (this.key==item.getKey() && this.val==item.getVal() && this.priority==item.priority);

        }

        @Override
        public int compareTo(LRUItem item){
            if (this.priority>item.priority)return 1;
            if (this.priority<item.priority) return -1;
            return 0;
        }
    }
    private Map<Integer, LRUItem> cache;
    private PriorityQueue<LRUItem> pq;//minHeap; O(1) the poll
    int numItems=0;  //AtomicInteger();
    int maxCapacity=0;
    long seq=0;

    public LRUCache(int capacity) {
        this.maxCapacity=capacity;
        cache = new HashMap<>(capacity);
        pq= new PriorityQueue<>();
    }

    public int get(int key) {
        LRUItem item= cache.get(key);
        if (item==null) return -1;
        int val=item.getVal();
        pq.remove(item);
        item.setPriority(seq++);//
        pq.add(item);
        return val;
    }

    public void put(int key, int value) {
        LRUItem cItem= cache.get(key);

        LRUItem item= new LRUItem(key, value, seq++);
        if (numItems==maxCapacity && maxCapacity>0 && cItem==null){
            //remove element with least recentrly used(least timestamp)
            LRUItem iToRemove=pq.poll();
            if (iToRemove!=null)
              cache.remove(iToRemove.getKey());
            pq.remove(iToRemove);
            numItems--;
        }
        if (cItem!=null)
            pq.remove(cItem);
        pq.add(item);
        cache.put(key, item);
        if (cItem==null)
          numItems++;
    }

    
 

}


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
 
