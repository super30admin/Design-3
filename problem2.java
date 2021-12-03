class LRUCache {
    
    class Node {
        int key;
        int val;
        Node prev;
        Node next;
        public Node(int key,int val){
            this.key=key;
            this.val=val;
        }
    }
    
    Node head; Node tail;
    HashMap <Integer,Node> map;
    int capacity;
    
    public LRUCache(int capacity) {
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
        map = new HashMap();
    }
    
    private void add(Node curr){
        curr.prev = head;
        curr.next = head.next;
        head.next.prev = curr;
        head.next = curr;
    }
    
    private void remove(Node curr){
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
    }
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        
        Node toRemove = map.get(key);
        
        remove(toRemove);
        add(toRemove);
        return toRemove.val;
    }
    
    public void put(int key, int value) {
        
        if(!map.containsKey(key)){
            
            if(map.size() == capacity){
                Node toRemove = tail.prev;
                remove(toRemove);
                map.remove(toRemove.key);
                
            }
                Node curr = new Node(key,value);
                add(curr);
                map.put(key,curr);
        }else {
            
            Node curr = map.get(key);
            curr.val = value;
            remove(curr);
            add(curr);
            
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */