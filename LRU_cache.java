import java.util.HashMap;

//Time Complexity : O(1) -> get and put
//Space Complexity : O(capacity)
class LRUCache {
    class Node{
        int key; int val;
        Node next; Node prev;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    Node head; Node tail;
    int capacity;
    HashMap<Integer, Node> map;
    public LRUCache(int capacity) {
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        this.map = new HashMap<>();
        this.capacity = capacity;
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
        }
        else{
            if(capacity == map.size()){
                Node tailNode = tail.prev;
                removeNode(tailNode);
                map.remove(tailNode.key);
            }
            Node newnode = new Node(key,value);
            addToHead(newnode);
            map.put(key, newnode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */