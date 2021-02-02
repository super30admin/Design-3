// Time Complexity : 0(1) as all map operations are 0(1)
// Space Complexity : 0(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : 

class LRUCache {

    //Node class
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

    //move least recently used node to head
    private void moveToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    //remove node from cache
    private void removeNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    Node head;
    Node tail;
    int capacity;
    HashMap<Integer, Node> map;

    //initializing variables & dummy head and tail
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }

    //after getting val from map, move that node to head of the map
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        removeNode(node);
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        //if node already present, update value & move to head
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            moveToHead(node);
        }else{
            //if node not present, then create new node
            Node newNode = new Node(key, value);
            //if capacity of map is full, remove last node
            if(capacity == map.size()){
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            //insert new node and move to head
            moveToHead(newNode);
            map.put(key, newNode);
        }
    }
}