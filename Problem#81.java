// LRU Cache

// Time Complexity : O(1)
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : -


// Your code here along with comments explaining your approach

class LRUCache {

    class Node{
        int key;
        int val;
        Node prev;
        Node next;

        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    private void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        // head.next = node;
        // node.next.prev = node;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }


    private Node head;
    private Node tail;
    private int capacity;
    private HashMap<Integer, Node> map;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.head = new Node(-1,-1);
        this.tail = new Node(-1, -1);
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
        } else {
            if(map.size() == capacity){
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key, value);
            addToHead(newNode);
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