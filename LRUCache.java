import java.util.HashMap;
import java.util.Map;

class LRUCache{
    class Node{
        int val;
        int key;
        Node next;
        Node prev;
        Node(int key, int val)
        {
            this.key = key;
            this.val = val;
        }
    }
    Node head;
    Node tail;
    int capacity;
    Map<Integer,Node> cache;
    public LRUCache(int capacity) {
        head = new Node(-1,-1);
        tail = new Node(-2,-2);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        cache = new HashMap<>();
    }
    
    public void addToFront(Node node)
    {
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev=head;
    }
    public void delete(Node node)
    {
        node.next.prev = node.prev;
        node.prev.next=node.next;
        
    }
    
    public void deleteFromEnd()
    {
        cache.remove(tail.prev.key);
       
        tail.prev.prev.next = tail; 
        tail.prev = tail.prev.prev;
       
    }
    
    public int get(int key) {
        if(cache.containsKey(key))
        {
            Node node = cache.get(key);
            delete(node);
            addToFront(node);
            return node.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(capacity == 0)
            return;
        if(cache.containsKey(key))
        {
            Node node = cache.get(key);
            node.val = value;
            delete(node);
            addToFront(node);
            return;
        }
        if(cache.size() == capacity)
        {
            
            deleteFromEnd();
            
        }
        Node newNode = new Node(key,value);
        addToFront(newNode);
        cache.put(key,newNode);
        
    }
}