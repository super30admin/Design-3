// Time Complexity : O(1)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class LRUCache {
    HashMap<Integer, Node> map;
    
    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }  
    
    Node head;
    Node tail;
    int capacity;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.head.prev = null;
        this.tail.next = null;
        this.capacity = capacity;
    }
    
    private void addToHead(Node node){
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
        node.prev = head;
    }
    
    private void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        remove(node);
        addToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            remove(node);
            addToHead(node);
        }else{
            Node newNode = new Node(key,value);
            if(map.size() < capacity){
                addToHead(newNode);
                map.put(key, newNode);
            }else{
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
                addToHead(newNode);
                map.put(key, newNode);
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