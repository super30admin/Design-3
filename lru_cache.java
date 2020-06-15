//Time Complexity: O(1)
//Space Complexity: O(capacity)


class LRUCache {
    class Node{
        int key;
        int val;
        Node prev;
        Node next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    
    public void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    public void removeNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    HashMap<Integer,Node> map = new HashMap<>();
    Node head;
    Node tail;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);        
        return node.val;
    }
    
    public void put(int key, int value) {
        //Case 1: If the key is already present in the map;
        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            addToHead(node);            
            node.val = value;
        }
        
        //Case 2: If the key is not already present and we are inserting a new key
        else{
            
            Node newnode = new Node(key,value);             
            //Case 1: if the map is full
            if(map.size() == capacity){
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            //case 2: if there is still space;
            
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