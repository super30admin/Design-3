class LRUCache {
    class Node
    {
        int key;
        int value;
        Node prev;
        Node next;
        Node(int k, int v)
        {
            this.key = k;
            this.value = v;
        }
    }
    
    Node head;
    Node tail;
    int capacity;
    HashMap<Integer, Node> map;
    
    public LRUCache(int capacity)  //constructor
    {
       map = new HashMap<Integer, Node>();
       head = new Node(-1, -1);
       tail = new Node(-1, -1);
       head.next = tail;
       tail.prev = head;
       this.capacity = capacity;
       
    }
    
    private void addNodeToHead(Node node)
    {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
        
    }
    private void removeNode(Node node)
    {
         node.prev.next = node.next;
         node.next.prev = node.prev;
    }
   
    
    public int get(int key)
    {    
         if (!map.containsKey(key)) 
            return -1; 
        
         Node node = map.get(key);
         removeNode(node);
         addNodeToHead(node);
         return node.value;
    }
    
    public void put(int key, int value) 
    {
        if (map.containsKey(key))
        {
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addNodeToHead(node);
        }
        else
        {
            Node node = new Node(key, value);   
            if (map.size() == capacity)
            {
                //remove a node from tail and map 
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);    
            }    
                // add new node at head and also in the map 
                addNodeToHead(node);
                map.put(key, node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
