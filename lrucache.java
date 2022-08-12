// Time Complexity : o(1) 
// Space Complexity : o(capacity)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no




// Your code here along with comments explaining your approach

class LRUCache {
  
    
    class Node{
        int key;
        int value;
        Node prev;
        Node next;
        
        public Node(int key,int value){
            this.key=key;
            this.value=value;
        }
    }
    HashMap<Integer,Node> map;
    int capacity;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity=capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next=tail;
        tail.prev= head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);           // as we are calling we are making it recently used
        addToHead(node);            // removing from tail adding to head 
        return node.value;
        
    }
    
    private void addToHead(Node node){
        node.next=head.next;
        node.prev=head;
        head.next=node;
        node.next.prev=node;
    }
    
    private void removeNode(Node node){
        node.next.prev=node.prev;
        node.prev.next=node.next;
    }
    
    public void put(int key, int value) {
        
        if(map.containsKey(key)){
            Node node= map.get(key);
            removeNode(node);
            addToHead(node);
            node.value=value;
            return;
        }
        if(map.size()==capacity){
            Node node = tail.prev;  // least recently used node
            map.remove(node.key);
            removeNode(node);  
        }
        Node node= new Node(key,value);
        addToHead(node);     // making node recently used node
        map.put(key,node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */