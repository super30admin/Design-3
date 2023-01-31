//Time Complexity: O(1)  
//Space Complexity: O(1)

/*
 * In this approach we design a linked hashmap. we add the key as key and node as the value in the hashmap. When we want to get we get the node corresponding to that key
 * and we remove the node from its location and add it to the most recently used node. If we want to put and the map doesnt have that key and the map is at all full capacity 
   then we remove the least recent used node and add this node to the most recent. If the capacity is not full then we just add this to the map and at the head or most recent.
   If the node to be put is already there then we just update the value of the node.
 * 

*/
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
    private Node head;
    private Node tail;
    int capacity;
    private HashMap<Integer, Node> map;

    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev= node.prev;
    }

    public void addToHead(Node node){
        node.next= head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
     
    public LRUCache(int capacity) {
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next = tail;
        this.tail.prev = head;
        this.capacity = capacity;
        this.map = new HashMap<>();

    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        Node curr = map.get(key);
        removeNode(curr);
        addToHead(curr);
        return curr.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node curr = map.get(key);
            curr.val = value;
            removeNode(curr);
            addToHead(curr);
        }else{
            if(map.size()==capacity){
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key,value);
            addToHead(newNode);
            map.put(key,newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */