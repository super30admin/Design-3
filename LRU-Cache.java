class LRUCache {
    
    class Node{
        int key;
        int val;
        Node next;
        Node prev;
        
        Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    
    HashMap<Integer,Node> map;
    int capacity;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }
    
    private void remove(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    private void addToHead(Node head, Node node){
        
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
        
    }
    
    public int get(int key) {
        
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        
        remove(node);
        addToHead(head,node);
        
        
        return node.val;
        
    }
    
    public void put(int key, int value) {
        
       // when key is already there
        
        if (map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            
            remove(node);
            addToHead(head,node);
            
        } else {
            // when key is new 
            // capacity is full
            
            Node newNode = new Node(key,value);
            if (map.size() == capacity){
                Node tailPrev = tail.prev;
                remove(tailPrev);
                map.remove(tailPrev.key);
            }
            addToHead(head,newNode);
            map.put(key,newNode);
        
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */