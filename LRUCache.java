// Time Complexity : O(1) for get and put
// Space Complexity : O(n) n is capacity
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
// Double LinkedList + HashMap
//
class LRUCache {
    
    private class Node {
        int key, val;
        Node prev, next;
        
        Node(int key, int val){
            this.key = key;
            this.val=val;
            prev = null;
            next = null;
        }  
    }
    
    private Node head;
    private Node tail;
    private Map<Integer, Node> map = new HashMap<>();
    private int size;
    private int capacity;
    
    public LRUCache(int capacity) {
        head = new Node(0,0);
        tail = new Node(0,0);
        
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        int size=0;  
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) 
            return -1;
        update(node);
        
        return node.val;
    }
    
    public void put(int key, int value) {
        Node node = map.get(key);
        
        if(node==null){
            node = new Node(key, value);
            map.put(key, node);
            size++;
            add(node);

        }else{
            node.val = value;
            update(node);
        }
        
        if(size > capacity){
            Node temp = head.next;
            remove(temp);
            size--;
            map.remove(temp.key);
        }  
    }
    
    private void add(Node node){
        Node temp = tail.prev;
        tail.prev = node;
        node.prev = temp;
        temp.next = node;
        node.next = tail;
    }
    
    private void remove(Node node){
        Node before = node.prev;
        Node after = node.next;

        before.next = after;
        after.prev = before;
    }
    
    private void update(Node node){
        remove(node);
        add(node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */