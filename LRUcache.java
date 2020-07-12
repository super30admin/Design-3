//Time complexity - get=O(1) set=O(1)

class LRUCache {
    class Node{
        int key;
        int val;
        Node next;
        Node prev;
        public Node(int key,int val){
            this.key=key;
            this.val=val;
        }
    }
    
    private void removeNode(Node node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }
    private void addNode(Node node){
        node.prev=head;
        node.next=head.next;
        head.next=node;
        node.next.prev=node;
    }
    Node head;
    Node tail;
    Map<Integer,Node> map;
    int capacity;
    public LRUCache(int capacity) {
        map=new HashMap();
        head=new Node(-1,-1);
        tail=new Node(-1,-1);
        head.next=tail;
        tail.prev=head;
        this.capacity=capacity;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node node=map.get(key);
            removeNode(node);
            addNode(node);
            return node.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node=map.get(key);
            node.val=value;
            removeNode(node);
            addNode(node);
        }
        else{
            Node newNode=new Node(key,value);
            if(map.size()==capacity){
                Node tailprev=tail.prev;
                removeNode(tailprev);
                map.remove(tailprev.key);
            }
            addNode(newNode);
            map.put(key,newNode);
            
        }
    }
}