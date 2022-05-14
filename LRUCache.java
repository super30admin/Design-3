import java.util.*;

public class LRUCache {
    class Node{
        int key,val;
        Node next,prev;
        
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    
    Node head,tail;
    
    int capacity;
    
    HashMap<Integer,Node> map;
    
    private void removeNode(Node node)
    {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void addToHead(Node node)
    {
        node.next = head.next;
        node.prev = head;
        
        node.next.prev = node;
        head.next = node;
        
    }

    public LRUCache(int capacity) {
        
        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        
        head.next = tail;
        tail.prev = head;
        
        this.capacity = capacity;
    }
    
    public int get(int key) {
        
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        
        return node.val;
    }
    
    public void put(int key, int value) {
        
        if(map.containsKey(key))
        {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
            return;
        }
        
        if(capacity == map.size())
        {
            Node tailPrev = tail.prev;
            removeNode(tailPrev);
            map.remove(tailPrev.key);
        }
        
        Node node = new Node(key, value);
        map.put(key, node);
        
        addToHead(node);
        
    }
}