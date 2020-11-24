class LRUCache {
    class Node {
        int key; int val;
        Node prev; Node next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
        
    }
    Node head;
    Node tail;
    private void addTohead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
        
    }
    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    HashMap<Integer, Node> map;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap();
        Node head = new Node(-1,-1);
        Node tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
        
    }
    
    public void put(int key, int value) {
        if(map.conatinsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }
        else {
            Node newNode = new Node(key, value);
            if(capacity == map.size()){
                Node tailPrev = tail.prev;
                //remove from linkedlist
                removeNode(tailPrev);
                //remove from map
                map.remove(tailPrev.key);
            }
            // add the new Node to list
            addToHead(newNode);
            // add to map
            map.put(key, newNode);
        }
    }
}
