// Time Complexity : O(1) for all operations
// Space Complexity : O(c) c is size of hashmap
// Did this code successfully run on Leetcode : Yes

class LRUCache {
    class Node{
        int key;
        int val;
        Node prev; Node next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    
    private void addtoHead(Node node){
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        head.next = node;
    }
    
    private void removeNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    Node head;
    Node tail;
    int capacity;
    HashMap<Integer, Node> map;
    
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addtoHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addtoHead(node);
        }
        else{
            Node newNode = new Node(key, value);
            if(capacity == map.size()){
                Node tailPrevious = tail.prev;
                removeNode(tail.prev);
                map.remove(tailPrevious.key);
            }
            addtoHead(newNode);
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