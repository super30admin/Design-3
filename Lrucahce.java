import java.util.*;

class LRUCache {
    class Node{
        int key; 
        int val;
        Node prev;
        Node next;
        public Node(int key, int val){
            this.val = val;
            this.key = key;
        }    
    }

    //Adding to the head of doubly linked list
    private void addtoHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    //removing the node from the linked list
    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    HashMap<Integer, Node> hmap;
    int capacity;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        hmap = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        
    }
    
    public int get(int key) {
        if(! hmap.containsKey(key)){
            return -1;
        }
        Node node = hmap.get(key);
        removeNode(node);
        addtoHead(node);
        return node.val;
        
    }
    
    public void put(int key, int value) {
        if(hmap.containsKey(key)){
            Node node = hmap.get(key);
            node.val = value;
            removeNode(node);
            addtoHead(node);
        }else{
            Node newNode = new Node(key,value);
            if(capacity == hmap.size()){
                Node tailprev = tail.prev;
                removeNode(tailprev);
                hmap.remove(tailprev.key);
            }
            
            //add new node
            addtoHead(newNode);
            hmap.put(key,newNode);
        }
        
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */