
/**
Leet Code Submitted : YES
Space Complexity : O(Capacity)
Time Complexity : O(1)

The idea is to use Doubly linked list and HashMAP to store key of the LRU cache and reference to the list as value in the hashmap. Upon reaching the capacity of the map remove the tail node and any element will be added at the head node.

**/
class LRUCache {
    int capacity;
    int size;
    Node head;
    Node tail;
    HashMap<Integer,Node> hm;
    
    class Node {
        int key;
        int val;
        Node next;
        Node prev;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
}
    
    public LRUCache(int capacity) {
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        
        hm       = new HashMap<>();
        this.capacity =  capacity;
        //this.size     = 0;
    }
    
    private void removeNode(Node node){
        node.prev.next =  node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }
    
    private void addNode(Node node){
        node.next = head.next;
        head.next = node;
        node.prev = head;
        node.next.prev =  node;
    }
    
    public int get(int key) {
        if(!hm.containsKey(key))
            return -1;
        Node node = hm.get(key); 
        removeNode(node);
        addNode(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        Node temp = new Node(key,value); 
        if(hm.containsKey(key)){
            removeNode(hm.get(key));
            addNode(temp);
            hm.put(key,temp);
        }else{
            if(capacity == size)
            {   Node rem = tail.prev;
                removeNode(rem);
                hm.remove(rem.key);
                addNode(temp);
                hm.put(key,temp);
            }else{
                addNode(temp);
                hm.put(key,temp);
                size++;
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
