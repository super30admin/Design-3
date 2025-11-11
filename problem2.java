// Time Complexity : O(1)
// Space Complexity :O(capacity)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :yes

/*
 * We are using a doubly linked list so that we can keep track of the order of the node
 * and we use a hashmap to keep track of which nodes are there in the cache
 * 
 * when the cache is initialised we create the linkedlist and add a dummy head a tail and link them
 * 
 * we get funciton is called we check if the key is in the hashmap or not, if it is presnt we delete it from it initial postion 
 * and move it to the top of the list and return the value
 * 
 * when we push 
 * we check if we are at capacity
 *  if we are, delete the last node of the list and 
 * add the key value in the hashmap and create a new node and push it to the top
 * 
 * or if not at capacity just push it to the list and map
 * if it is already there update the value and push the node to the top of the list
 */
class LRUCache {
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

    private Node head;
    private Node tail;
    private int capacity;
    private HashMap<Integer, Node> map;

    private void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    private void addToHead(Node node) {
        node.next = head.next;
        head.next = node;
        node.prev = head;
        node.next.prev = node;

    }

    public LRUCache(int capacity) {
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head.next = tail;
        this.tail.prev = head;

    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        int value = node.value;
        removeNode(node);
        addToHead(node);
        return value;

    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
        } else {
            if (map.size() == capacity) {
                Node tailPrev = tail.prev;
                tail.prev = tailPrev.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
                tailPrev.next = null;
                tailPrev.prev = null;
            }
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
        }

    }
}