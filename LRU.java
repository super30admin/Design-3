class LRUCache {
    class Node{
        int key,val;
        Node prev,next;
        public Node(int key, int val){
            this.key=key;
            this.val=val;
        }
    }
    HashMap<Integer,Node> map;
    Node head,tail;
    int cap;
    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.head=new Node(-1,-1);
        this.tail=new Node(-1,-1);
        this.head.prev=null;
        this.head.next=this.tail;
        this.tail.prev=this.head;
        this.tail.next=null;
        this.cap=capacity;
    }
    private void removeNode(Node curr){
        curr.next.prev=curr.prev;
        curr.prev.next=curr.next;
        curr.prev=null;
        curr.next=null;
    }
    private void addNode(Node curr){
        curr.next=head.next;
        curr.next.prev=curr;
        head.next=curr;
        curr.prev=head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node=map.get(key);
        removeNode(node);
        addNode(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node=map.get(key);
            node.val=value;
            removeNode(node);
            addNode(node);
            
        }
        else{
            if(map.size()>=cap){                
                map.remove(tail.prev.key);
                removeNode(tail.prev);                
            }
            Node nw = new Node(key,value);
            addNode(nw);
            map.put(key,nw);
        };
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
