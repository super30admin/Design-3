// Time Complexity : O(1) where m is the no of valid combinations 
// Space Complexity : O(n) n is the capacity
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class LRUCache {
    private class Node{
        int key, val;
        Node prev, next;
        public Node(int key, int val){
            this.key=key;
            this.val=val;
            prev=null;
            next=null;
        }
    }
    private Node head, tail;
    private HashMap<Integer, Node> hm;
    private int capacity;
    private int size;
    
    private void add(Node n){
        Node temp=tail.prev;
        n.next=tail;
        tail.prev=n;
        temp.next=n;
        n.prev=temp;
    }
    private void remove (Node n){
        Node bef=n.prev;
        Node aft=n.next;
        bef.next=aft;
        aft.prev=bef;
    }
    private void update(Node n){
        remove(n);
        add(n);
    }
    public LRUCache(int capacity) {
        head=new Node(0,0);
        tail=new Node(0,0);
        head.next=tail;
        tail.prev=head;
        
        this.capacity=capacity;
        size=0;
        hm=new HashMap<>();
    }
 
    public int get(int key) {
       Node n= hm.get(key);
        if(n==null){
           return -1; 
        }
        else{
            update(n);
            return n.val;
        }
        
    }
    
    public void put(int key, int value) {
        Node n=hm.get(key);
        if(n==null){
            n=new Node(key,value);
            hm.put(key,n);
            size+=1;
            add(n);
        }
        else{
            n.val=value;
            update(n);
        }
        if(size>capacity){
            Node temp=head.next;
            remove(temp);
            size-=1;
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