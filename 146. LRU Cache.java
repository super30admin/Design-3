class LRUCache { // time and space constant O(1)
    
    class Node{
        int key;
        int val;
        Node prev;
        Node next;
    }
    
    private void addNode(Node node){
        // Add node next to head
        node.prev = head;
        node.next = head.next;  
        head.next.prev = node ;
        head.next = node;
    }
    private void removeNode(Node node){
        // Delete node prev to tail
        Node prev = node.prev ;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }
    private void moveToHead(Node node){
        removeNode(node);
        addNode(node);
    }
    private Node removeTail(){
        Node temp = tail.prev ;
        removeNode(temp);
        return temp;
    }
    
    private Map<Integer,Node> cache = new HashMap<>();
    private int size;
    private int capacity;
    private Node head,tail;

    public LRUCache(int capacity) {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Node node = cache.get(key);
        // if key doesnt exist
        if(node == null)
            return -1;
        moveToHead(node);// maintain recency
        return node.val;
    }
    
    public void put(int key, int value) {
        //key already exist update the value
        Node node = cache.get(key);
        // if key doesnt exist
        if(node == null){
            //if its full
            if(size == capacity){
                Node tail = removeTail();
                cache.remove(tail.key);
                --size;
            }
            Node new_node = new Node();
            new_node.key = key;
            new_node.val = value;
            cache.put(key,new_node);
            addNode(new_node);
            ++size;
        }else{
            node.val = value;
            moveToHead(node);
        }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */