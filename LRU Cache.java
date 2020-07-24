// Time Complexity : O(1)
// Space Complexity : O(n), n is capacity of Cache
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :



class LRUCache {
    //create a class Node with key and value
    //node next and node prev
    class Node{
        int key; 
        int value;
        Node next;
        Node prev;
        
        //create a constructor and initialize value for key and value 
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    //globally declare map O(1) get and put , 
    //cachecapacity to initialize with LRU Cache capacity
    //Node head and tail dummy nodes 
    //(first and last node in Doubly LL) (initialize to -1,-1) 
    HashMap<Integer, Node> map; 
    int cachecapacity;
    Node head;
    Node tail;
    
    //add recently accessed (get/put) node to head
    public void addNodeToHead(Node node) {
        //add the node between head and first node
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;        
    }
    
    //when reached capacity
    //to add new Node, we remove Least recently node (before tail)
    public void removeNodeFromLRU(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    //initialize the global variables
    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        //DLL is empty in start, so head and tail are only nodes in Cache
        head.next = tail;
        tail.prev = head;
        //initialize the cachecapacity
        cachecapacity = capacity;
    }
    
    //to get node from cache 
    public int get(int key) {
        //check if map cantains the key
        //if not, return -1
        if(map.containsKey(key)) {
            //if map contains key
            //get the node wrt the key
            //remove the node from its original position
            //and add it after head
            //and return the value of the node
            Node node = map.get(key);
            removeNodeFromLRU(node);
            addNodeToHead(node);
            return node.value;         
        } return -1;       
    }
    
    //to add new Node to cache
    public void put(int key, int value) {
        //check if node with key value already exists
        if(map.containsKey(key)) {
            //get the node wrt the key
            Node node = map.get(key);
            //set value equal to new value
            node.value = value;
            //remove the node from original position
            //and add it after head
            removeNodeFromLRU(node);
            addNodeToHead(node);
        }
        else {
            //if node with key not present
            //create a new node with given key and value
            Node newNode = new Node(key, value);
            //check if capacity not full
            //if capacity full
            if(map.size() == cachecapacity) {
                //remove the last node (before tail)
                //remove from map as well
                Node removeLast = tail.prev;
                removeNodeFromLRU(removeLast);  
                map.remove(removeLast.key);
            }
            //after checking size condition
            //add the new node after head
            //and add it to the map
            addNodeToHead(newNode);
            map.put(key, newNode);
        }        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


