// Time Complexity : O(1) for both get and put because HashMap lookup and linked list operations take constant time
// Space Complexity : O(capacity) for storing at most capacity number of nodes
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach in three sentences only
// We use a HashMap for fast key-to-node lookup and a doubly linked list to track least recently used and most recently used nodes.
// Whenever a key is accessed or updated, we move that node to the head because it becomes the most recently used.
// If capacity is full during insertion, we remove the node before tail because it represents the least recently used item.

class LRUCache {

    class Node {
        int key;
        int val;
        Node next;
        Node prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private HashMap<Integer, Node> map;
    private int capacity;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {

        // initialize map and capacity
        this.map = new HashMap<>();
        this.capacity = capacity;

        // dummy head and tail
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);

        // connect dummy nodes
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    private void removeNode(Node node) {

        // detach node from linked list
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node) {

        // insert node right after head
        node.next = head.next;
        node.prev = head;

        head.next = node;
        node.next.prev = node;
    }
    
    public int get(int key) {

        // key not found
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);

        // move accessed node to most recent position
        removeNode(node);
        addToHead(node);

        return node.val;
    }
    
    public void put(int key, int value) {

        // key already exists
        if (map.containsKey(key)) {

            Node node = map.get(key);

            // update value
            node.val = value;

            // move updated node to most recent position
            removeNode(node);
            addToHead(node);
        } else {

            // capacity reached
            if (map.size() == capacity) {

                // remove least recently used node
                Node tailPrev = this.tail.prev;

                removeNode(tailPrev);

                map.remove(tailPrev.key);
            }

            Node newNode = new Node(key, value);

            // add new node as most recently used
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