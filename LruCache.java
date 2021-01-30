// class LRUCache {
//     LinkedHashMap<Integer,Integer> map;
//     int capacity;
//     public LRUCache(int capacity) {
//         this.capacity = capacity;
        
//         this.map = new LinkedHashMap<Integer,Integer>(capacity,0.75F,true)
//         {
//             public boolean removeEldestEntry(Map.Entry entry){
//                 return size() > capacity;
//             }
//         };
//     }
    
//     public int get(int key) {
//        return map.getOrDefault(key,-1);
//     }
    
//     public void put(int key, int value) {
//         map.put(key,value);
//     }
// }
class Node{
    int value;
    int key;
    Node next;
    Node prev;
    Node(){}
    Node(int key,int value){
        this.key = key;
        this.value = value;
        this.next = null;
        this.prev = null;
    }
    
}
//creating doubly linkedlist

class DoublyLinkedList{
    Node head;
    Node tail;
    
    public DoublyLinkedList(){
        this.head = new Node();
        this.tail = new Node();
        
        head.next = tail;
        tail.prev = head;
    }
    
    //method to insert at head
    public void insertHead(Node n){
        n.prev = head;
        n.next = head.next;
        head.next.prev = n;
        head.next = n;
    }
     //this the method to remove from tail
    public int removeTail(){
        Node n = tail.prev;
        int k = n.key;
        
        remove(n);
        return k;
        
    }
    //methis to remove the node
    public void remove(Node n){
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }
   
}

class LRUCache {
    int capacity; //define the capacity
    
    HashMap<Integer, Node> cache;//hashmap of interger, node 
    DoublyLinkedList list; //create a linkedlist

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.list = new DoublyLinkedList();
        
    }
    
    public int get(int key) {
        //if cache does not contain key, return -1.
        if(!cache.containsKey(key)){
            return -1;
        }
        //if contains, the cache will be updated.
        update(key,cache.get(key));
        
        //return the value required
        return cache.get(key).value;
        
    }
    
    /*
    in update, we remove the node,then insert it at front and then put in cache
    */
    public void update(int key, Node n){
        list.remove(n);
        list.insertHead(n);
        cache.put(key,n);
    }
    
    //in this put function
    //create we create new node
    //remove the node from cache if present
    //else if cache . size >= k, remove the node from end of list
    //remove the cache
    //then add node to front of list
    //put the key back in cache
    public void put(int key, int value) {
        Node n = new Node(key,value);
        
        if(cache.containsKey(key)){
            list.remove(cache.get(key));
        }
        
        else if(cache.size() >= capacity){
            int k = list.removeTail();
            cache.remove(k);
        }
        list.insertHead(n);
        cache.put(key,n);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */