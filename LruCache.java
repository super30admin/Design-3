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
class DoublyLinkedList{
    Node head;
    Node tail;
    
    public DoublyLinkedList(){
        this.head = new Node();
        this.tail = new Node();
        
        head.next = tail;
        tail.prev = head;
    }
    
    public void insertHead(Node n){
        n.prev = head;
        n.next = head.next;
        head.next.prev = n;
        head.next = n;
    }
    
    public void remove(Node n){
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }
    public int removeTail(){
        Node n = tail.prev;
        int k = n.key;
        
        remove(n);
        return k;
        
    }
}

class LRUCache {
    int capacity;
    HashMap<Integer, Node> cache;
    DoublyLinkedList list;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.list = new DoublyLinkedList();
        
    }
    
    public int get(int key) {
    
        if(!cache.containsKey(key)){
            return -1;
        }
        update(key,cache.get(key));
        
        return cache.get(key).value;
        
    }
    
    public void update(int key, Node n){
        list.remove(n);
        list.insertHead(n);
        cache.put(key,n);
    }
    
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