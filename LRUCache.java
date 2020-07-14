// Time Complexity :O(nl) n is the number of words and l is the length of the words;
// Space Complexity :O(l) 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class LRUCache {
    class Node{
        Node next;
        Node prev;
        int key;
        int value;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    
    public void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    
    
    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            addToHead(node);
            return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        //case1
        if(map.containsKey(key)){
            
            Node rNode = map.get(key);
            rNode.value = value;
            removeNode(rNode);
            addToHead(rNode);
            
        }
        else{
            //case2 new node
            
            Node newNode = new Node(key, value);
            if(capacity == map.size()){
                Node tailP = tail.prev;
                removeNode(tailP);
                map.remove(tailP.key);
            }
            
            addToHead(newNode);
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