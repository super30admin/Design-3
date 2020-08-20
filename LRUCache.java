//time complexity O(1)
//space complexity O(capacity)

class LRUCache {
    class Node {
        Node next; Node prev; 
        int key; int val;
        public Node(int key, int value){
            this.key = key;
            this.val = value; 
        }

    }
    //this map will hold the reference to the node wrt to its key
    HashMap<Integer, Node> map;
    Node head; Node tail;
    int capacity;
    //constructor
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    public void addtoHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
        map.put(node.key, node);
    }
    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addtoHead(node);
        return node.val;            
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addtoHead(node);
            map.put(key, node);
        } else {
            Node newNode = new Node(key,value);  
            if(map.size() == capacity){
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            addtoHead(newNode);
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
