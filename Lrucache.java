//Time o(1)
//space O(1)
class LRUCache {
    class Node{
        int key; int val;
        Node prev; Node next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    Node head;
    Node tail;
    HashMap<Integer,Node> map;
    int capacity;
    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }
    
    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void insertNode(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev=node;
        
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
          Node node =  map.get(key);
          removeNode(node);
          insertNode(node);  
          return node.val;  
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            insertNode(node);
        
        }else{
            if(map.size() == capacity){
                Node tailPrev = tail.prev; 
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
             Node newNode = new Node(key,value);
             map.put(key, newNode);
             insertNode(newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */