//Time Complexity: O(1)
//Space Complexity: O(n) where n is the capacity of cache.
// Did this code successfully run on Leetcode :Yes

class LRUCache {

    class Node{
        Node next; Node prev;
        int key; int value;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    
    public void addNode(Node node){
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
    Node head; Node tail;
    int mapCap;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        this.mapCap = capacity;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            addNode(node);
            return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node  = map.get(key);
            node.value = value;
            removeNode(node);
            addNode(node);
        }
        else{
            Node node = new Node(key,value);
            if(mapCap == map.size()){
                Node tailNode = tail.prev;
                removeNode(tailNode);
                map.remove(tailNode.key);
            }
            
            addNode(node);
            map.put(key,node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */