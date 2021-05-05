class LRUCache {
    
    class Node{
        int key; 
        int val;
        Node prev;
        Node next;
        
        public Node(){}
        
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    
    int k;
    Map<Integer,Node> map;
    Node head, tail;
    public LRUCache(int capacity) {
        this.k = capacity;
        map = new HashMap(capacity);
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        //System.out.println("Get " + key );
        if(map.containsKey(key)){
            Node n = map.get(key);
            moveToHead(n);
            return n.val;    
        }
        return -1;
    }
    
    public void put(int key, int value) {
        Node n;
        if(map.containsKey(key)){
            n = map.get(key);
            removeNode(n);
            map.remove(key);
        }
        if(map.size() == k) popTail();
        n = new Node(key,value);
        moveToHead(n);
        map.put(key,n);
        //System.out.println("Put " + key + " , " + value);
    }
    
    private void removeNode(Node n){
        n.prev.next = n.next;
        n.next.prev = n.prev;
        n.prev = null;
        n.next = null;
    }
    
    private void moveToHead(Node n){
        if(map.containsKey(n.key)) removeNode(n);
        n.next = head.next;
        n.prev = head;
        head.next.prev = n;
        head.next = n;
    }
    
    private void popTail(){
        Node n = tail.prev;
        //System.out.println("Removing " + n.key + " , " + n.val);
        removeNode(n);
        map.remove(n.key);
    }
    
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */