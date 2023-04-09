//TC-O(1)
class LRUCache {
    class Node{
        // node object with key,val,prev and next pointers,we need doubly
        // linked list to get access of prev and next objects and maintain order of indices
        int key;
        int val;
        Node prev;
        Node next;
        Node(int key,int val){
            this.key=key;
            this.val=val;
        }
    }
    // class members contain head,tail,capacity,map to store node references
    // to get them in O(1)
    private Node head;
    private Node tail;
    private HashMap<Integer,Node> map;
    private int capacity;
    
    private void addNode(Node node){
        //add to head
        node.next=head.next;
        head.next=node;
        node.prev=head;
        node.next.prev=node;
    }

    private void removeNode(Node node){
        //remove the node
        node.next.prev= node.prev;
        node.prev.next=node.next;
    }

    public LRUCache(int capacity) {
        // initialize head,tail,capacity,map, join head and tail
        this.head =new Node(-1,-1);
        this.tail =new Node(-1,-1);
        this.capacity = capacity;
        map = new HashMap<>();
        this.head.next = this.tail;
        this.tail.prev=this.head;
    }
    
    public int get(int key) {
        // if not present return -1,else use get , remove and add it.
        if (!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        removeNode(node);
        addNode(node);
        return node.val;
        
    }
    
    public void put(int key, int value) {
        // if already present,get node, change its value, remove and add again
        if (map.containsKey(key)){
            Node node = map.get(key);
            node.val=value;
            removeNode(node);
            addNode(node);
        }
         else{
             // if capacity reached,remove prevnode of tail
            if (map.size()==capacity){
                Node node=tail.prev;
                removeNode(node);
                map.remove(node.key);
            }
            //create new node and add using map.put into hashmap and add to cache using addNode
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addNode(newNode);
         }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */