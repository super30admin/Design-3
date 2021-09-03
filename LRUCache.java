class LRUCache {

    class Node{
        int key, val;
        Node next, prev;
        
        public Node(int key, int val){
            this.key =key;
            this.val = val;
        }
    }

    Node head, tail;
    
    //add new Node always to head 
    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        head.next  = node;
        
    }
    
    //remove the node from the back always.
    private void remove(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    int capacity;
    HashMap<Integer,Node> hashMap;
    
    //Initialize the LRU cache 
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.hashMap = new HashMap();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }
    
    //get the Key from Map. Insert at the front of list.
    public int get(int key) {
        if(hashMap.containsKey(key)) {
           Node node = hashMap.get(key);
            remove(node);
            addToHead(node);
            return node.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        //key already exists. 
        if(hashMap.containsKey(key)) {
            Node node = hashMap.get(key);
            node.val = value;
            remove(node);
            addToHead(node);
        }
        //Key not present
        else{
            //If capacity reached
            if(capacity == hashMap.size()){
                Node tailPrev = tail.prev;
                remove(tailPrev);
                hashMap.remove(tailPrev.key);
            }
            //Add Node into the List and HasMap
            Node newNode = new Node(key,value);
            addToHead(newNode);
            hashMap.put(key,newNode);
        }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */