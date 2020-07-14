// Time Complexity : O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No



class LRUCache {

    class Node{
        int key,value;
        Node next,prev;
        public Node(int key,int value){
            this.key=key;
            this.value=value;
        }
    }
    
    public void removeNode(Node node){
        node.prev.next=node.next;
        node.next.prev = node.prev;
    }
    
    public void addNodeToHead(Node node){
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev=node;
    }
    
    HashMap<Integer,Node> map;
    Node head,tail;
    int capacity;
    
    public LRUCache(int capacity) {
        
        this.capacity = capacity;
        map = new HashMap();
        
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        
        head.next=tail;
        tail.prev=head;
        
    }
    
    public int get(int key) {
        
        
        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            addNodeToHead(node);
            return node.value;
        }
        return -1;
        
    }
    
    public void put(int key, int value) {
        
        //if key is already present
        if(map.containsKey(key)){
            
            Node node = map.get(key);
            node.value = value;
            
            removeNode(node);
            addNodeToHead(node);
            
            map.put(key,node);    
        }
        //if key is not present
        else{
            //new node
            
            //if size is equals capacity
            Node newNode = new Node(key,value);
            if(map.size()==capacity){
                
                
                Node tailPreviousNode = tail.prev;
                removeNode(tailPreviousNode);
                map.remove(tailPreviousNode.key);
            }
            addNodeToHead(newNode);
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
