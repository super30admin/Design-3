// Time Complexity : O(1) - As both hashMap and Double linked List take O(1) time for lookup,add and delete operations
// Space Complexity : O(N) - As we use a HashMap
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class LRUCache{
    class Node{
        int key;
        int val;
        Node prev;
        Node next;
    }
    private void addNode(Node node){
        Node p1 = head.next;
        node.prev = head;
        node.next = p1;
        p1.prev = node;
        head.next = node;
    }
    private void removeNode(Node root){
        Node n1 = root.prev;
        Node n2 = root.next;
        n1.next = n2;
        n2.prev = n1;
    }
    private void moveToHead(Node node){
        removeNode(node);
        addNode(node);
    }
    private Node removeTail(){
        Node temp = tail.prev;
        removeNode(temp);
        return temp;
    }
    Map<Integer,Node> cache = new HashMap<>();
    private int size;
    private int capacity;
    Node head,tail;
    public LRUCache(int capacity) {
       head = new Node();
       tail = new Node();
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }
    
    public int get(int key) {
       Node node = cache.get(key);
        if(node == null) return -1;
        moveToHead(node);
        return node.val;
        
    }
    
    public void put(int key, int value) {
        Node node = cache.get(key);
        if(node == null){
            if(size == capacity){
                Node tail = removeTail();
                cache.remove(tail.key);
                --size;   
            }
            Node temp = new Node();
            temp.key = key;
            temp.val = value;
            addNode(temp);
            cache.put(key,temp);
            ++size;
        }
        else{
            node.val = value;
            moveToHead(node);
            
        }
    }
    
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// Your code here along with comments explaining your approach