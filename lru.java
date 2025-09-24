class LRUCache {
    HashMap<Integer,Node>m;
    int capacity;
    Node head;
    Node tail;
    class Node{
        int key;
        int val;
        Node prev,next;
        public Node(int key,int val){
            this.key=key;
            this.val=val;
        }
    }
    public LRUCache(int capacity) {
        this.m=new HashMap<>();
        this.capacity=capacity;
        this.head=new Node(-1,-1);
        this.tail=new Node(-1,-1);
        head.next=tail;
        tail.prev=head;
    }
    public void remove(Node node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }
    public void add(Node node){
        node.next=head.next;
        node.next.prev=node;
        head.next=node;
        node.prev=head;
    }
    public int get(int key) {
        if(!m.containsKey(key)){
            return -1;
        }
        Node node=m.get(key);
        remove(node);
        add(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(m.containsKey(key)){
            Node node=m.get(key);
            node.val=value;
            remove(node);
            add(node);
        }
        else{
            if(m.size()==capacity){
                Node tailprev=tail.prev;
                remove(tailprev);
                m.remove(tailprev.key);
            }
            Node newn=new Node(key,value);
            add(newn);
            m.put(key,newn);
        }
        
    }
}