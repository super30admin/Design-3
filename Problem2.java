// Time Complexity : O(1) for get() and put methods
// Space Complexity : O(n) where n is the capacity
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach

/**
 * Using doubly linked list to keep track most recently used and least recently used elements at head and tail respectively.
 * Using a hashmap to keep a reference of the existing keys and nodes in the list to get the elements in O(1).
 */

class LRUCache {
    Map<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;

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

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        // using dummy head and tail for edge cases
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;

        // Removing the entry and adding it to head so it is most recently used
        removeEntry(node);
        addEntry(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            removeEntry(node);
            addEntry(node);
        } else {
            // if map has reached the capacity, remove the least recently used element first
            if (map.size() == capacity) {
                Node lastNode = tail.prev;
                removeEntry(lastNode);
                map.remove(lastNode.key);
            }
            Node newNode = new Node(key, value);
            addEntry(newNode);
            map.put(key, newNode);
        }
    }

    // Removes the entry from the doubly linked list
    private void removeEntry(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }

    // adds the entry to the head of the list
    private void addEntry(Node node) {
        node.prev = head;
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */