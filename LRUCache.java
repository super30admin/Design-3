//TC: O(1)
//SC: O(capacity)

class LRUCache {
    class Node{
        int key; int val;
        Node prev; Node next;
        
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    Map<Integer, Node> map;
    Node head = new Node(-1,-1); Node tail = new Node(-1,-1);
    int capacity;
    
    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    public void addToHead(Node node){
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }
    
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if(node!=null){
            int result = node.val;
            removeNode(node);
            addToHead(node);
            return result;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            node.val = value;
            addToHead(node);
            map.put(key, node);
        }
        else {
            if(map.size() < this.capacity)
                {
                Node node = new Node(key, value);
                addToHead(node);
                map.put(key, node);
                }
            else {
                Node prevTail = tail.prev;
                removeNode(prevTail);
                map.remove(prevTail.key);
                Node node = new Node(key, value);
                addToHead(node);
                map.put(key, node);
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
