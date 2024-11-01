class LRUCache {
    HashMap<Integer , Node> map;
    Node head;
    Node tail;
    int capacity;
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
    

    public LRUCache(int capacity) {
        this.capacity=capacity;
        this.map=new HashMap<>();
        this.head=new Node(-1,-1);
        this.tail=new Node(-1,-1);
        head.next=tail;
        tail.prev=head;
        
    }
     private void removeNode(Node node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
        node.next=null;
        node.prev=null;
    }
    
    private void addToHead(Node curr){
        curr.prev=head;
        curr.next=head.next;
        head.next=curr;
        curr.next.prev=curr;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node curr=map.get(key);
        removeNode(curr);
        addToHead(curr);
        return curr.value;
        
    }
    
    public void put(int key, int value) {
         if(map.containsKey(key)){
            Node curr=map.get(key);
            curr.value=value;
            removeNode(curr);
            addToHead(curr);
        }else{
            if(map.size()==capacity){
                Node tailPrev=tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode=new Node(key,value);
            addToHead(newNode);
            map.put(key,newNode);
        }
        
    }
