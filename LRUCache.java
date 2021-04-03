//Time complexity: O(1) for put(),get()
//space complexity: O(capactity) for hashMap



/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

class LRUCache {
    
    class Node{
        int key, val;
        Node prev, next;
        
        public Node(int key, int val){
            this.key = key;
            this.val = val;
            prev = null;
            next = null;
        }
    }
    
    HashMap<Integer, Node> hm;
    Node h, t;
    int size, capacity;
    
    public LRUCache(int capacity) {
        
        hm = new HashMap<>();
        this.capacity = capacity;
        size = 0;
        h = new Node(0,0);
        t = new Node(0,0);
        
        //link h and t
        h.next = t;
        t.prev = h;      
        
    }
    
    //add and remove
    
    private void add(Node node){
        Node temp = t.prev;
        temp.next = node;
        node.next = t;
        t.prev = node;
        node.prev=temp;
    }
    
    private void remove(Node node){
        Node before = node.prev;
        Node after = node.next;
        before.next = after;
        after.prev=before;
    }
    //reordering includes add and remove
    private void update(Node node){
        remove(node);
        add(node);
    }
    
    public int get(int key) {
        Node curr = hm.get(key);
        
        if(curr == null){
            return -1;
        }
        
        update(curr);
        return curr.val;
    }
    
    public void put(int key, int value) {
        Node curr = hm.get(key);
        if(curr == null){
            curr = new Node(key,value);
            hm.put(key,curr);
            size++;
            add(curr); //add to dll
        }
        else{
            update(curr);
            curr.val = value;
        }
        
        if(size > capacity){
            hm.remove(h.next.key);
            remove(h.next);
            size--;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */