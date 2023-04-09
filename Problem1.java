// Time Complexity : O(1)
// Space Complexity :O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach in three sentences only
/*Store the key and Node in a hashmap to retrieve and search quickly. 
To insert elements we add it to the head of the double linked list. 
To remove when capaicity is full, we remove from tail and the map. 
We use double linked list so we can add and remove in O(1).
 * 
 */

class LRUCache {
    class Node{
        int key; int val; 
        Node next; Node prev; 
        Node(int key, int val){
            this.key = key; 
            this.val = val;
        }
    }
    private void removeNode(Node node){
        node.next.prev = node.prev; 
        node.prev.next = node.next;
    }
    private void addToHead(Node node){
        node.next = head.next;
        node.prev = head; 
        head.next = node; 
        node.next.prev = node;
    }
    private Node head; 
    private Node tail;
    private HashMap<Integer, Node> map;
    private int capacity;
    public LRUCache(int capacity) {
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.capacity = capacity; 
        map = new HashMap<>();
        this.head.next = this.tail; 
        this.tail.prev = this.head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value; 
            removeNode(node);
            addToHead(node);
        }
        else {
            if(capacity == map.size()){
                Node tailprev = tail.prev; 
                removeNode(tailprev);
                map.remove(tailprev.key);
            }
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToHead(newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */