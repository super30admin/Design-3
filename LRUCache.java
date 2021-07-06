// Time Complexity : put and get - O(1)
// Space Complexity : O(C) - C = capacity
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Nope


// We need to maintain a LRU cache i.e discard the least recelty used element when adding a new element and the capacity is full
// we use a Hashmap of integer and ListNode pair along with a doubly linked list
// everytime we update a value we move it to the begining of the list.
// if the capacity is reached we remove the last element of the DLL and also from the hashmap and then add the new element

class LRUCache {
Node head,tail;
    Map<Integer,Node> map;
    int cache_capacity;

    public LRUCache(int capacity) {
        this.cache_capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        head.prev = null;
        tail.next = null;
        tail.prev = head;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        Node n = map.getOrDefault(key,null);
        if(n==null)
            return -1;
        remove(n);
        add(n);
        return n.value;
    }
    
    public void put(int key, int value) {
        Node n = map.getOrDefault(key,null); 
        if(map.size()==cache_capacity) {
            if(n==null) {
                Node lastNode = tail.prev;
                remove(lastNode);
                map.remove(lastNode.key);
                n = new Node(key,value);
            } else {
                n.value=value;
                remove(n);
            }
        } else {
                 if(n==null) {
                    n = new Node(key,value);
                } else {
                    n.value=value;
                    remove(n);
                } 
        }
        add(n);
        map.put(key,n);
    }
    
    
    public void add(Node node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }
    
    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }
    
    class Node{
        int key;
        int value;
        Node next;
        Node prev;
        
        public Node(){}
        
        public Node(int key,int val){
            this.key=key;
            this.value=val;
        }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
