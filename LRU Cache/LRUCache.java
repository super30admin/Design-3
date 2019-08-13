class LRUCache {
    
    class Node {
        int key;
        int val;
        
        Node prev;
        Node next;
        
        Node (int key, int val){
            this.key = key;
            this.val = val;
            this.prev = this.next = null;
        }
    }
    
    int capacity;
    Node head;
    Node tail;
    int count = 0;
    HashMap<Integer, Node> hmap = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        head.prev = null;
        tail.prev = head;
        tail.next = null;
    }
    
    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;        
    }
    
    public void addToHead(Node node){
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }
    
    public int get(int key) {
        
        if(hmap.get(key) != null) {
            Node node = hmap.get(key);
            removeNode(node);
            addToHead(node);
            return node.val;
        }
        
        return -1;
        
    }
    
    public void put(int key, int value) {
        
        if(hmap.get(key) != null){
        
            Node node = hmap.get(key);
            node.val = value;
            //hmap.put(key, node);
            removeNode(node);
            addToHead(node);
        }
        else {
            Node node = new Node(key, value);
            //node.val = value;
           hmap.put(key, node);
            // capacity is not full
            if(count < capacity){
                count++;                
                addToHead(node);
            }
            // capacity full
            else {
                hmap.remove(tail.prev.key);
                removeNode(tail.prev);
                
                addToHead(node);
            }
        }
        
                
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */