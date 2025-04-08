// TC: O(1) for put and get
// SC: O(capacity)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// we are using Linked HashMap DS for LRUCache
class LRUCache { 
    class Node {
        int key, val;
        Node prev, next;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    Node head, tail;
    int capacity;
    HashMap<Integer, Node> map; // HashMap of Key & Node

    public LRUCache(int capacity) {
        this.head = new Node(-1, -1); // creating 2 dummy Nodes head and tail
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.capacity = capacity;
        this.map = new HashMap<>();
    }
    // writing 2 helper functions
    private void removeNode(Node node) { // O(1)
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    private void addToHead(Node node) { // O(1)
        node.next = head.next;
        head.next = node;
        node.prev = head;
        node.next.prev = node;
    }

    public int get(int key) { // O(1)
        if(!map.containsKey(key)) return -1; // DNE doesnt exist
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) { // already existing node
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }
        else { // new node
            if(map.size() == capacity) {// check for size
                Node tailPrev = tail.prev;
                removeNode(tailPrev); // remove from both the DS, HashMap & LL
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key, value);
            addToHead(newNode); // similarly add to both DS
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