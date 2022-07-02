class LRUCache {
  
  //TC: O(1) SC:O(n)
class Node{
    Node prev;
    Node next;
    int key;
    int value;
    public Node(int key, int value){
        this.key=key;
        this.value=value;
    }
}
    Node head;
    Node tail; 
    HashMap<Integer, Node> map;
    int capacity;
    public LRUCache(int capacity) {
        
        this.head=new Node(-1,-1);
        this.tail=new Node(-1,-1);
        this.head.next=this.tail;
        this.tail.prev=this.head;
        this.capacity = capacity;
        this.map=new HashMap<>();
        
        
    }
    
    void removeNode(Node node){
        node.next.prev=node.prev;
        node.prev.next=node.next;
    }
     void addToHead(Node node){
        node.next=head.next;
        node.prev=head;
        head.next=node;
        node.next.prev=node;
    }
    public int get(int key) {
        
        if(!map.containsKey(key)) 
            return -1;
        //if map contains key, then remove and add to head as it will be recently used 
        else{
        Node node=map.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
        
    }
    }
    public void put(int key, int value) {
        
        if(map.containsKey(key)){
            Node node=map.get(key);
            node.value=value;
            //replace existing value
            //remove the node from it existing position and add to head
            removeNode(node); 
            addToHead(node);
    
        }
        else //doesnt exist in map         
        {
            if(capacity==map.size()){
                Node tailprev=tail.prev;
                removeNode(tailprev);
                 map.remove(tailprev.key);
            }
                
                    Node newNode=new Node(key,value);
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
