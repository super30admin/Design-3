// Time Complexity: get() -> O(1); put() -> O(1)
// Space Complexity: O(k) where k is the capacity of the LRU cache
// Run on Leetcode: Yes
// Issues: None

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
    
    Map<Integer, Node> map;
    int capacity;
    Node head = new Node(-1,-1);
    Node tail = new Node(-1,-1);
    
    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }
    
    public void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    public void removeNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node temp = map.get(key);
            removeNode(temp);
            addToHead(temp);
            return temp.val;
        }
        else{
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node temp = map.get(key);
            removeNode(temp);
            temp.val = value;
            addToHead(temp);
            map.put(key, temp);
        }
        else{
            Node newNode = new Node(key, value);
            if(map.size() >= capacity){
                map.remove(tail.prev.key);
                removeNode(tail.prev);
            }
            addToHead(newNode);
            map.put(key, newNode);
        }
    }
}
