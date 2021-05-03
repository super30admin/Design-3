class LRUCache {
    class Node{
        int key, value;
        Node prev, next;
        public Node(int key,int value){
            this.key=key;
            this.value=value;
        }
    }
    
    Node head,tail;
    void addToHead(Node node){
        head.next.prev=node;
        node.next=head.next;
        head.next=node;        
        node.prev=head;
       
    }
    void remove(Node node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
        
    }
    HashMap<Integer, Node> map;
    int cap;

    public LRUCache(int capacity) {
       head=new Node(-1,-1); 
       tail=new Node(-1,-1);
        tail.prev=head;
        head.next=tail;
        cap=capacity;
        map=new HashMap<>();
    }
    
    public int get(int key) {
      if(map.containsKey(key)){
          Node val= map.get(key);
          remove(val);
          addToHead(val);
          return val.value;
      }
        return -1;
        
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
          Node val= map.get(key);
          remove(val);
          addToHead(val);
          val.value=value;
      }
        else{
            if(map.size()==cap)
                {
                Node rem=tail.prev;
                remove(rem);
                map.remove(rem.key);
            }
            Node nodeadd= new Node(key,value);
            addToHead(nodeadd);
            map.put(key,nodeadd);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */