
//Time Complexity: Put - O(1) , Get - O(1)

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

    private void addToHead(Node node){
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;

    }

    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private HashMap<Integer, Node> map;
    private Node head; 
    private Node tail;
    private int capacity;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1,-1);
        this.tail = new Node(-1, -1);
        this.head.next = tail;
        this.tail.prev = head;
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
        //isFresh?
        if(map.containsKey(key)){
            //existing node
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }else{
            //fresh node
            if(capacity == map.size()){
                //cache is full
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            //cache is not full 
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToHead(newNode);
        }
    }
}