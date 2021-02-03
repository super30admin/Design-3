class LRUCache {
    class Node{
        int key; int value;
        Node prev; Node next;
        public Node(int key,int value){
            this.key=key;
            this.value=value;
        }
    }
    private void addTohead(Node node){
        node.prev=head;
        node.next=head.next;
        head.next=node;
        node.next.prev=node;
        
    }
    private void removeNode(Node node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }
    HashMap<Integer,Node> map;
    Node head;
    Node tail;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity=capacity;
        head=new Node(-1,-1);
        tail=new Node(-1,-1);
        map=new HashMap<>();
        head.next=tail;
        tail.prev=head;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node node=map.get(key);
            removeNode(node);
            addTohead(node);
            return node.value;
        }
        else{
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node=map.get(key);
            node.value=value;
            removeNode(node);
            addTohead(node);
        }
        else{
            if(capacity==map.size()){
                Node tailprev=tail.prev;
                removeNode(tailprev);
                map.remove(tailprev.key);
            }
            Node newNode=new Node(key,value);
            addTohead(newNode);
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