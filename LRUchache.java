class LRUCache {
    
    
   
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
    
    private void removeNode(Node node){ //O(1)
        node.next.prev = node.prev;      
        node.prev.next = node.next;
        
        
        
    }
    
    private void addToHead(Node node){ //O(1)
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;      
    }
    
    //HashMap for Key value==> Node pairs
    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;
    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        
        this.head.next = tail;
        this.tail.prev = head;
        this.capacity = capacity;
            
    }
    
    public int get(int key) {
        
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        
        
        removeNode(node);
        
        addToHead(node); 
        System.out.println(node.val);
        
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }else{
           
            if(map.size() == capacity){
                 //capacity is full
                Node tailPrevNode = tail.prev;
                removeNode(tailPrevNode);
                map.remove(tailPrevNode.key);               
            }
            Node newNode = new Node(key, value);
            addToHead(newNode);
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
