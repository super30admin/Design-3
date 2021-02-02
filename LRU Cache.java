// Time Complexity : O(1)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach

class LRUCache {
    //create the doubly linked list node
    class Node{
        int key; 
        int val; 
        Node prev;
        Node next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    
    //add to the head function
    private void addToHead(Node node){
        //change the current node's next to to the dummy head's next
        node.next = head.next;
        //change the current node's prev to the dummy head
        node.prev = head;
        //change the dummy head's next to the current node
        head.next = node;
        //change current node's next's prev to node
        node.next.prev = node;
    }
    
    //remove node
    private void removeNode(Node node){
        //change the current node's next's prev to current node's prev
        node.next.prev = node.prev;
        //change the current node's prev's next to current node's next
        node.prev.next = node.next;
    }
    
    //maintains head,tail, capacity, and a hashmpa for global scope
    HashMap<Integer, Node> hash;
    Node head;
    Node tail;
    int cap;
    public LRUCache(int capacity) {
        //intiate global variables
        hash = new HashMap<>();
        this.cap = capacity;
        //dummy head and dummy tail
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        //connect the dummy head and tails
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        //check if the hashmap contains the key
        if(!hash.containsKey(key)) return -1;
        //get the node if it is in the map
        Node node = hash.get(key);
        //remove the node
        removeNode(node);
        //add the node to the head
        addToHead(node);
        //also return the value of the node
        return node.val;
    }
    
    public void put(int key, int value) {
        //check if the hash containst he key
        if(hash.containsKey(key)){
            //get the node 
            Node node = hash.get(key);
            //change the value
            node.val = value;
            //remove the node
             removeNode(node);
            //add the node to the head
            addToHead(node);
        }
        else{
            //the new node coming in
            Node newNode = new Node(key, value);
            //check if the cap is full
            if(cap == hash.size()){
                //remove the tail prev;
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                hash.remove(tailPrev.key);
            }
            //add to the head
            addToHead(newNode);
            //put into the hashmap
            hash.put(key, newNode);
            
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


