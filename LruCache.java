// Time Complexity : O(1)
// Space Complexity : O(c), c = capacity of the LRU cache;
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Notes : Create a HashMap to store all the key value pairs, to maintain the capacity of the cache and get() the required values. Create a doubly linked list to efficiently maintain the order of the key value pairs, keeping the most recently used pairs at the head of the list.

public class LruCache {
    class Node{
        Node next;
        Node prev;
        int key;
        int val;
        Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    
    HashMap<Integer, Node> map = new HashMap<>();
    Node head;
    Node tail;
    int cap = 0;
    
    public LRUCache(int capacity) {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        cap = capacity;
    }
    
    private void insertAtHead(Node node){
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }
    
    private void removeNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node curr = map.get(key);
            removeNode(curr);
            insertAtHead(curr);
            return curr.val;
        } 
        return -1;
    }
    
    public void put(int key, int value) {
        Node newNode = new Node(key, value);
        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
        }else if(map.size() == cap){
            Node lastNode = tail.prev;
            removeNode(lastNode);
            map.remove(lastNode.key);
        }
        insertAtHead(newNode);
        map.put(key, newNode); 
    }
}
