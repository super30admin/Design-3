// Time Complexity : O(1)
// Space Complexity : O(capacity)

class LRUCache {
    class Node{
        int key;
        int val;
        Node next;
        Node prev;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    Map<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;
    
    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }
    
    private void removeNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    private void addNodeToHead(Node node){
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            addNodeToHead(node);
            return node.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addNodeToHead(node);
        }
        else {
            if(map.size() == capacity){
                Node node = tail.prev;
                removeNode(node);
                map.remove(node.key);
            }
            Node node = new Node(key, value);
            addNodeToHead(node);
            map.put(key, node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */