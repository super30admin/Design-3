
//   Time Complexity: O(1)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes



class LRUCache {
    class Node{
        int key; 
        int val;
        Node prev; 
        Node next;
        public Node (int key, int val){
            this.key = key;
            this.val = val;
        }   
    }
    
    private void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    private void remove (Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    HashMap <Integer, Node> map;
    int capacity;
    Node head;
    Node tail;
    
    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        remove(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            remove(node);
            addToHead(node);
        }   else {
            if(capacity == map.size()){
                //remove
                Node tailPrev = tail.prev;
                remove(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
        }
            
            
    }
    
    
    
}