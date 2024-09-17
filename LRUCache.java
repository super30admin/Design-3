import java.util.HashMap;

class LRUCache {
    class Node{
        int key; int val;
        Node next; Node prev;
        Node(int x, int y){
            this.key = x;
            this.val = y;
        }
    }
    
    private void removeNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    private void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    HashMap<Integer, Node> map;
    Node head; Node tail;
    int capacity;
    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        } else {
            Node newNode = new Node(key, value);
            
            if(map.size() < capacity){
                addToHead(newNode);
            } else {
                Node lru = tail.prev;
                removeNode(lru);
                map.remove(lru.key);
                addToHead(newNode);
            }
            map.put(key, newNode);
        }
    }
}
