class LRUCache {
    
    Node head = new Node(0, 0), tail = new Node(0, 0);
    int capacity = 0;
    HashMap<Integer, Node> cacheLookup = new HashMap();

    public LRUCache(int _capacity) {
        
        
        head.next = tail;
        tail.next = head;
        capacity = _capacity;
    }
    
    public int get(int key) {
        
        //Check if the lookup table contains the requested value.
        
        if(cacheLookup.containsKey(key)){
            Node temp = cacheLookup.get(key);
            
            //keep this node next to head, since it is the recently accessed node.
            
            remove(temp);
            insert(temp);
            
            return temp.value;
        }
        
        else{
             //page fault
            
            return -1;
        }
    }
    
    
    
    
    public void put(int key, int value) {
        
        if(cacheLookup.containsKey(key)){
            
            //only remove the value
            remove(cacheLookup.get(key));
            
        }
        
        //Eviction
        
        if(cacheLookup.size() == capacity){
            remove(tail.prev);
        }
        
        insert(new Node(key, value));
        
    }
    
    //insert() accepts a ready-made node that is properly initialized.
    
    void insert(Node node){
        
        //Make a record of the node in the hashmap, so that we can search in constant time.
        
        cacheLookup.put(node.key, node);
        
        //Do the appropriate connections.
        
        Node temp = head.next;
        head.next = node;
        node.prev = head;
        node.next = temp;
        temp.prev = node;
        
        
    }
    
    void remove(Node node){
        
        //First remove the entry from the map.
        
        cacheLookup.remove(node.key);
        
        Node temp = node.next;
        node.prev.next = temp;
        node.next.prev = node.prev;
        
        
    }
    
    class Node {
        
        Node next;
        Node prev;
        int key = 0;
        int value = 0;
        
        public Node(int k, int v){
            
            this.key = k;
            this.value = v;
        }
    }
    
   
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//We will require a hashmap to remember all the values that are present in the LRU cache. 
//How to initializa a blank linked list?
//We need to put some values in it.
//I saw a video to understand the question, In that they have implemented a DLL. Why DLL though?