// Time Complexity : O(1) for all operations
// Space Complexity : O(capacity) for hashmap
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
Have a map of key and node of doubly linked list to keep track of least recent used items at the head directly.
Have dummy/sentinel nodes intialized for easier access of head and tail. Whenever get call happens, we need to
remove that node from that position and add it to before tail because of recent use.Similarly, when put call
happens, we need to check if that key is already present, if so, we remove that node and create a new node and
add it to the list before tail. We also need to keep track of capacity by using map's size and remove the node
at the head as it is least used.
 */
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

    Map<Integer, Node> map;
    int capacity;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addNode(Node node) {
        tail.prev.next = node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
    }

    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        removeNode(node);
        addNode(node);
        return node.val;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node nodeToBeRemoved = map.get(key);
            removeNode(nodeToBeRemoved);
        }
        Node node = new Node(key, value);
        addNode(node);
        map.put(key, node);

        if(map.size() > capacity) {
            Node nodeToBeRemoved = head.next;
            removeNode(nodeToBeRemoved);
            map.remove(nodeToBeRemoved.key);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */