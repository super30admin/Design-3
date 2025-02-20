class LRUCache {
    
    class Node{
        int key;
        int val;
        Node next;
        Node prev;
        public Node(int key,int value ){
            this.key = key;
            this.val = value;
        }
    }
    private HashMap<Integer,Node> map;
    private Node head;
    private Node tail;
    int capacity;
    public LRUCache(int capacity) {
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.capacity = capacity;
        this.map = new HashMap<>();
        
    }
    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node){
        node.next = this.head.next;
        node.prev = this.head;
        node.next.prev = node;
        this.head.next = node;
    }
    
    public int get(int key) {

        if(!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        
        removeNode(node);
        addToHead(node);

        return node.val;

    }
    
    public void put(int key, int value) {

        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            node.val = value;
            addToHead(node);

        }else{
            if(map.size() == capacity){
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node curr = new Node(key,value);
            addToHead(curr);
            map.put(key, curr);
        }

        
    }
}