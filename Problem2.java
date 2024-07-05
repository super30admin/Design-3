/*
    LC - 146. LRU Cache
    TC - O(1) for all the operations
    SC - O(capacity), where capacity is the maximum number of key-value pairs the cache can hold.
            The HashMap stores at most 'capacity' number of entries.
            The doubly linked list also contains at most 'capacity' + 2 nodes (including dummy head and tail).
    Approach - Uisng Doubly Linkedlist + hashmap
*/

class LRUCache {

    class Node {
        int key, value;
        Node next, prev; // by default it will be null since it is java.
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Node head, tail;

    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    HashMap<Integer, Node> map;
    int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            removeNode(node);
            addToHead(node);
            node.value = value;
            return;
        }
        if(capacity == map.size()) {
            Node tailprev = tail.prev;
            removeNode(tailprev);
            map.remove(tailprev.key);
        }
        Node node = new Node(key, value);
        addToHead(node);
        map.put(key, node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */