// Time Complexity : O(1)
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode : Yes
// Three line explanation of solution in plain english

// Your code here along with comments explaining your approach

class LRUCache {
    //Doubly linked list cause removing and insertong oper are less costly
    class Node{
        int key; int value;
        Node prev; Node next;
        public Node(int key, int val){
            this.key=key;
            this.value=val;
        }
    }
    //for removing a node from doubly linked list
    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    //adding a node to the head
    public void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    HashMap<Integer,Node> hmap;
    Node head; Node tail;
    int capacity;
        
    public LRUCache(int capacity) {
        hmap = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev=head;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!hmap.containsKey(key)){
            return -1;
        }
        Node node = hmap.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if(hmap.containsKey(key)){
            Node node = hmap.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
        }
        else{
            if(capacity == hmap.size()){
                Node tailprev = tail.prev;
                removeNode(tailprev);
                hmap.remove(tailprev.key);
            }
            Node newNode = new Node(key,value);
            addToHead(newNode);
            hmap.put(key,newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
