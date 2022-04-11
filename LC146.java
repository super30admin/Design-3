class LRUCache {

    class Node 
    {
        int key;
        int val;
        
        Node prev;
        Node next;
        
        public Node(int key,int val)
        {
            this.key = key;
            this.val = val;
        }
    }
    
    private void addtoHead(Node node)
    {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    private void removeNode(Node node)
    {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    Node head;
    Node tail;
    int capacity;
    HashMap<Integer,Node> map;
    
    public LRUCache(int capacity) {
        
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next = tail;
        this.tail.prev = head;
        this.capacity = capacity;
        map = new HashMap<>();
        
    }
    
    public int get(int key) {
        
        if(!map.containsKey(key))
        {
            return -1;
        }
        
        Node node = map.get(key);
        
        removeNode(node);
        addtoHead(node);
        
        return node.val;
        
    }
    
    public void put(int key, int value) {
        
        if(map.containsKey(key))
        {
            Node node = map.get(key);
            node.val = value;
            
            removeNode(node);
            addtoHead(node);
        }
        else
        {
            if(capacity == map.size())
            {   
                Node tailPrev = tail.prev;
                
                removeNode(tail.prev);
                map.remove(tailPrev.key);
            }
            
            Node newNode = new Node(key,value);
            addtoHead(newNode);
            map.put(key,newNode);
        }
        
        return;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */