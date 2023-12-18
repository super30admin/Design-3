class LRUCache {

    int capacity;
    Map<Integer, Node> map;
    Node head;
    Node tail;

    class Node{
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1,-1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        
    }
    
    // O(1)
    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            addNodeToHead(node);
            return node.value;

        }
        return -1;
    }
    
    // O(1)
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addNodeToHead(node);
        }else{
            if(map.size() == capacity){
                Node prev = tail.prev;
                removeNode(prev);
                map.remove(prev.key);           
            }
            Node node = new Node(key, value);
            addNodeToHead(node);
            map.put(key, node);
        }
    }

    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addNodeToHead(Node node){
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