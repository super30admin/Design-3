class LRUCache {
//Double LL used because whenever a node is removed the order is lost.
   //To keep track of previous and next node we need doubly LL 
    class Node{
        int val;
        int key;
        Node prev;
        Node next;
        
        public Node(int key,int val){
            this.key = key;
            this.val = val;
        }
    } 
        //to remove least recently accessed node from the LL
        private void removeNode(Node node){
            node.next.prev = node.prev;
            node.prev.next  = node.next;
        }
        
        //to add the latest node near the head because head contains lastest frequently accessed data
        private void addToHead(Node node){
            node.next =head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;
        }
    
    HashMap<Integer,Node> map;
    Node head; Node tail;
    int capacity;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }
    
    //whenever get means remove the old ie least accessed value position and place it at near the head which //recently accessed position
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node  = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        //case 1 : Node already present then update it to latest value and remove from old position in the Doubly        //LL and move it near the head most recently added 
       if(map.containsKey(key)){
           Node node = map.get(key);
           node.val = value;
           removeNode(node);
           addToHead(node);
       } 
        else{
            //new node 
            //check the capacity and remove node from tail's previous position to fit within the capacity
            if(capacity == map.size()){
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key,value);
            map.put(key,newNode);
            addToHead(newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
 /*
 TimeComplexity : O(1)
 Space Complexity : O(n) for n nodes in hashmap
 */