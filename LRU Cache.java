TC: get - O(1)
    put-O(1)
SC: get-O(capacity)
    put-O(capacity)

class LRUCache {
    
    class Node{
        int key,val;
        Node prev,next;
        
        public Node(int key, int val){
            this.key = key;
            this.val = val;
            prev = null;
            next = null;
        }
    }
    
    HashMap<Integer,Node> hashMap = new HashMap<>();
    Node head, tail;
    int size, capacity;

    public LRUCache(int capacity) {
        hashMap = new HashMap<>();
        int size = 0;
        this.capacity = capacity;
        
        head = new Node(0,0);
        tail = new Node(0,0);
        
        head.next = tail;
        tail.prev = head;
    }
    
    private void add(Node node){
     Node temp = tail.prev;
        temp.next = node;
        node.next = tail;
        tail.prev = node;
        node.prev = temp;        
    }
    
    private void remove(Node node){
       Node before = node.prev;
        Node after = node.next;
        before.next = after;
        after.prev = before;
    }
    
    private void reorder(Node node){
        remove(node);
        add(node);
    }
    
    public int get(int key) {
        Node curr = hashMap.get(key);
        if(curr == null)
            return -1;
        else{
            reorder(curr);
            return curr.val;
    }
    }
    
    public void put(int key, int value) {
        Node curr = hashMap.get(key);
        if(curr == null){
            curr = new Node(key,value);
            hashMap.put(key, curr);
            size++;
            add(curr);
        }
        else {
            reorder(curr);
            curr.val = value;
        }
        if(size>capacity){
            
            hashMap.remove(head.next.key);
            remove(head.next);
            size--;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */