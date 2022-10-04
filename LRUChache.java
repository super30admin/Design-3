class LRUCache {
    //sc-o(n)
    class Node{
        int key;
        int val;
        Node prev;
        Node next;
        
        public Node(int key,int value){
            this.key = key;
            this.val = value;
            
        }
    }
//addtohead
    public void addToHead(Node node)
    {
       node.prev = head;
       node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    public void remove(Node node)
    {      
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    Node head;
    Node tail;
    HashMap<Integer,Node> map;
    int capacity;
    
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next = tail;
        this.tail.prev = head;
        this.capacity = capacity;
    }
    
    public int get(int key) { //o(1)
        //get the value and put in in recent node
        
        if(!map.containsKey(key)) return -1;
        //contains key
        Node node = map.get(key);
        remove(node);
        addToHead(node);       
        return node.val;
    }
    
    public void put(int key, int value) {//o(1)
        //existing
        if(map.containsKey(key))
        {
            Node node = map.get(key);
            node.val = value;
            remove(node);
            addToHead(node);
        }
        else
        {
            //fresh
            if(capacity == map.size())
            {
                //remove LRU node
                Node tailPrev = tail.prev;
                remove(tailPrev);
                //remove from map
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