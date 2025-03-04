import java.util.HashMap;


// Problem 2: LRU Cache(https://leetcode.com/problems/lru-cache/)

//Time Complexity (TC):
//get(key): O(1)
//put(key, value): O(1)
//Space Complexity (SC): O(capacity)

// Did this code successfully run on Leetcode : Yes

//To implement LRUCache, we make use of a doubly linked list with nodes as key and values given and a hashmap with keys and given keys and values as nodes
//For implementing the get() method, we just retrieve the object, if it exists from the hashmap and we make that node as most recent
//For implementing the put() method, we check if the node already exists or not. If it exists, we update the value and make it most recent.

class LRUCache {

    class Node {
        int val;
        int key;
        Node next;
        Node prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node head;
    private Node tail;
    private int cap;
    HashMap<Integer, Node> map;

    public LRUCache(int capacity) {
        this.map = new HashMap();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.cap = capacity;
    }

    public int get(int key) {

        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);
        removeNode(node);
        addToHead(node);

        return node.val;
    }

    private void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }

    public void put(int key, int value) {

        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        } else {
            // fresh
            // check for capcacity

            if (map.size() == cap) {
                // full
                // remove the tail prev
                Node tailPrev = this.tail.prev;
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