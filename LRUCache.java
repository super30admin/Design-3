// Time Complexity : O(1) for get and put operations.
// Space Complexity : O(capacity) for storing in hashmap and doubly linked list.
// Did this code successfully run on Leetcode : Yes
// Approach : We use a doubly linked list and a HashMap to implement LRU. HashMap is for storing the key along with node as value so that search is O(1).
// In get, once we find it in hashMap, we need to remove the node from it's current position and move it to the beginning so that is recently used. For put, if
// capacity is full we remove the last node and the incoming one in the beginning, else we add to beginning and update the node value.

class LRUCache {

    HashMap<Integer, Node> map;
    int capacity;
    Node head;
    Node tail;

    class Node {
        int key;
        int value;
        Node next, prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    private void removeNode(Node node) { //update pointers to remove node
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }

    private void addToHead(Node node) { //update pointers to add at head
        node.prev = head;
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
    }

    public int get(int key) {
        if (!map.containsKey(key)) { //doesn't contain in hashmap
            return -1;
        }
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) { //if already present in hashmap
            Node node = map.get(key);
            node.value = value; // update value
            removeNode(node);
            addToHead(node);
        } else {
            if (map.size() == capacity) { //capacity is full
                Node lastNode = tail.prev; //remove last Node, that is LRU
                removeNode(lastNode);
                map.remove(lastNode.key);
            }
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode); //add the new node to hashmap as well.
        }
    }
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */