//Time Complexity  : O(1)

//Space Complexity : O(1)

//Solution


class LRUCache {
    
    class Node{
        int key,value;
        Node next,prev;
        public Node(int key,int value){
            this.key = key;
            this.value = value;
            this.next=this.prev =null;
        }
    }
    HashMap<Integer,Node> map ;
    Node head,tail;
    int capacity;
    
    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.head.prev = null;
        this.tail.next = null;
        this.capacity = capacity;
        
    }

    public void remove(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
        
    }
    
    public void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    
    
    
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        remove(node);
        addToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        //If element already exists
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            remove(node);
            addToHead(node);
        }else{
            Node newnode = new Node(key,value);
            
            if(map.size() >= capacity){
                Node lrused = tail.prev;
                remove(lrused);
                map.remove(lrused.key);
                addToHead(newnode);
                map.put(key,newnode);
                
            }else{ 
                addToHead(newnode);
                map.put(key,newnode);
            }
        
        }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */