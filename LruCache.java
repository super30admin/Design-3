class Node{
    int key;
    int value;
    Node next;
    Node prev;
    
    public Node(int key, int value){
        this.key = key;
        this.value = value;
        
    }
}
// class DoublyLinkedList{
//     Node head;
//     Node tail;
    
//     public DoublyLinkedList(Node head,Node tail){
//         this.head = new Node(-1,-1);
//         this.tail = new Node(-1,-1); 
        
//          this.head.next = tail;
//          this.tail.prev = head;
//     }
    
    
//}

class LRUCache {
    
    public void insertHead(Node node){
        node.prev = head;
        node.next = head.next;
        head.next = node;   
        node.next.prev = node;
    }
    
    public void deleteLast(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    HashMap<Integer, Node> cache;
    //DoublyLinkedList list;
    int capacity;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        //this.list = new DoublyLinkedList(head,tail);
    }
    
    public int get(int key) {
        if(!cache.containsKey(key)){
            return -1;
        }
        Node currNode = cache.get(key);
        //list.deleteLast(currNode);
        deleteLast(currNode);
        //list.insertHead(currNode);
        insertHead(currNode);
        return currNode.value;
    }
    
    public void put(int key, int value) 
    {      
            if(cache.containsKey(key)){
                //just update the value
                Node node = cache.get(key);
                node.value = value;
                deleteLast(node);
                insertHead(node);
            }
            else{
                if(capacity == cache.size()){
                    Node leastUsed = tail.prev;
                    deleteLast(leastUsed);
                    cache.remove(leastUsed.key);
                 } 
                Node node = new Node(key,value);
                insertHead(node);
                cache.put(key,node);
            }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */