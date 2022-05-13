class LRUCache {
    
    class Node{
        int key, val;
        Node next, prev;
        
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    
    Node head, tail;
    int capacity;
    HashMap<Integer, Node> map;
    
    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    public void insertToHead(Node node){
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        head.next = node;
    }

    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        insertToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            insertToHead(node);
            return;
        }
        if(capacity == map.size()){
            Node tailPrev = tail.prev;
            removeNode(tailPrev);
            map.remove(tailPrev.key);
        }
        Node node = new Node(key, value);
        map.put(key, node);
        insertToHead(node);
    }
}

// time complexity O(1) for all methods;
// space complexity O(n) as we use hasMap where n is capacity.