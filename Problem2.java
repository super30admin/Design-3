//146 LRU cache

class LRUCache {
    
    class Node{
        int key;
        int value;
        Node next;
        Node prev;
        
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    
    private void addToHead(Node curr){
        
        curr.prev = head;
        curr.next = head.next;
        head.next = curr;
        curr.next.prev = curr;
    }
    
    private void removeNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    HashMap<Integer, Node> map;
    int capacity;
    Node head;
    Node tail;
    
    public LRUCache(int capacity) {
        map = new HashMap();
        this.capacity = capacity;
        
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
            
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }else{
            Node node = map.get(key);
            removeNode(node);
            addToHead(node);
            
            return node.value;
        }
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
        }else{
            if(capacity == map.size()){
                Node toRemove = tail.prev;
                removeNode(toRemove);
                map.remove(toRemove.key);
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