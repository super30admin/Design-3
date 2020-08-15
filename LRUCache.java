import java.util.*;

/**
 * Time complexity : O(1) for put and get methods
 * Space complexity : O(N) N is size of the cache (size of the map)
 * 
 * Approach:
 * 1. To maintain the elements sequentially (as per time), we can use linkedlist.
 * 2. But to make access to each node as O(1), we can use map. To make ease of access to each node in linked list
 * we can map key element to its created Node which stores key and value.
 * 3. To make and remove operations as O(1), we can use doubly linkedlist.
 * 4. For adding the node (put method), we have to check is it exists or not, if it exists we have to replace its value and
 * put it at the head of the queue(most recent). If it doesn't exist create new and add it to the head.
 * 5. While getting node value (get method), we have to move the node to the head.
 */

class LRUCache {
    
    class Node {
        int key;
        int value;
        Node next;
        Node prev;
        
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Node head;
    Node tail;
    Map<Integer, Node> map;
    int capacity;
    
    public LRUCache(int capacity) {
        
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
        
    }
    
    /*
    Remove Node
    */
    
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    /*
    Add Node to head
    */
    private void addNode(Node node) {
        node.next = head.next;
        node.prev = head;
        
        head.next.prev = node;
        head.next = node;
        
    }
    
    
    public int get(int key) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            removeNode(node);
            addNode(node);
            return node.value;
        }
        else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            removeNode(node);
            map.put(key, new Node(key, value));
            addNode(map.get(key));
            
        }
        else {
            Node newNode = new Node(key, value);
            if(map.size() == this.capacity) {
                Node tailNode = tail.prev;
                removeNode(tailNode);
                map.remove(tailNode.key);
            }
            
            addNode(newNode);
            map.put(key, newNode);
            
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */