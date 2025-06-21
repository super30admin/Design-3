//first check the HashMap for the key; if found, removes the node from its current position in the doubly linked list and move it to the front (marking it most recently used). 
//If inserting a new key and the cache is full, remove the least recently used node (just before tail) from both the list and map, then insert the new node at the front and maps the key to it.
//Time Complexity: O(1) 
//Space Complexity: O(capacity)

class LRUCache {
    class Node {
        int key; int val;
        Node next; Node prev;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private HashMap<Integer, Node> map;
    private Node head;
    private Node tail;
    private int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = tail;
        this.tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        //is fresh
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        } else {
            //fresh
            if(capacity == map.size()) {
                //remove lru
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToHead(newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */