/*
Time Complexity : O(1)
Space Complexity : O(Capacity)
*/

class LRUCache {
    
    class Node{
        int key;
        int val;
        Node next;
        Node prev;
        public Node(){
            
        }
        public Node(int key,int val){
            this.key = key;
            this.val = val;
        }
    }
    Map<Integer,Node> cache;
    Node head;
    Node tail;
    int capacity;
    public LRUCache(int capacity) {
        this.cache = new HashMap<>();
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
       if(!cache.containsKey(key)){
           return -1;
       }
       Node node = cache.get(key);
       removeNode(node);
       addToHead(node);
       return node.val;
    }
    
    public void put(int key, int value) {
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
            return;
        }
        if(cache.size() == capacity){
            cache.remove(tail.prev.key);
            removeNode(tail.prev);
        }
        Node node = new Node(key,value);
        cache.put(key,node);
        addToHead(node);
    }
    
    public void removeNode(Node node){
         node.prev.next = node.next;
         node.next.prev = node.prev;
    }
    
    public void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */