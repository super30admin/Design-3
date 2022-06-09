// Time Complexity : remove, add, get, put - O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
class LRUCache {
    
    class Node{
        Node next;
        Node prev;
        int key;
        int val;
        
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    
    Node head;
    Node tail;
    
    HashMap<Integer, Node> map;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
       
        head.next = tail;
        tail.prev = head;
    }
    
    private void remove(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    private void add(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        remove(node);
        add(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        //not fresh node
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            remove(node);
            add(node);
        }else{//fresh node
            if(capacity==map.size()){
                Node tailPrev = tail.prev;
                remove(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key, value);
            add(newNode);
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