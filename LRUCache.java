//TC - O(1);
//SC - O(capacity);
class LRUCache {
    
    class Node {
        int key,value;
        Node prev,next;
        
        public Node(int key,int value){
            this.value=value;
            this.key=key;
        }
    }
    
    HashMap<Integer,Node> map;
    Node head,tail;
    int capacity;
    
    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    public void add(Node node){
        node.next=head.next;
        node.prev=head;
        head.next=node;        
        node.next.prev=node;
        

    }

    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node curr = map.get(key);
        remove(curr);
        add(curr);
        
        return curr.value;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node curr = map.get(key);
            curr.value=value;
            remove(curr);
            add(curr);
            return;
        }
        if(map.size()==capacity){
            Node last = tail.prev;
            remove(last);
            map.remove(last.key);
        }
        
        Node newNode = new Node(key,value);  
        map.put(key,newNode);
        add(newNode);
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */