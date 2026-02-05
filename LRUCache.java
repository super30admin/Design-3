// TC: O(1) for get and put operations
// SC: O(n) -> n is number of nodes

// 1: We use a Doubly LinkedList to maintain O(1) for operations. For each node, it maintains reference to the node before it and after it
// 2: We also maintain a HashMap to quickly identify which keys are already present, useful for get and updating operations
// 3: If we need to create a fresh node, we first check with the capacity to determine whether we need to delete a Node, then proceed with updating the references
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

    private Node head;
    private Node tail;
    private int capacity;
    private HashMap<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            removeNode(node);
            addToHead(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        // existing node
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        } else {
            // fresh node
            // we have capacity, add Node
            if (map.size() < capacity) {
                Node node = new Node(key, value);
                addToHead(node);
                map.put(key, node);
            }
            // delete from tail and add new node to head
            else {
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
                Node node = new Node(key, value);
                addToHead(node);
                map.put(key, node);
            }
        }

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */