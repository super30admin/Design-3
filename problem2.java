
    /**
    Time Complexity :
    get() -> O(1)
    put() -> O(1)

    Explanation:
    - HashMap gives O(1) access to nodes
    - Doubly Linked List allows O(1) insert/remove operations

    Space Complexity : O(C)
    Explanation:
    We store at most C (capacity) nodes in HashMap and DLL.

    Did this code successfully run on LeetCode : Yes

    Any problem you faced while coding this :
    Initially tried using only HashMap which could not maintain order.
    Fixed it by combining:
        - HashMap → fast lookup
        - Doubly Linked List → maintain LRU order
    Also needed dummy head & tail nodes to simplify edge cases.
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
    
        private HashMap<Integer, Node> map;
        private Node head;
        private Node tail;
        private int capacity;
    
        public LRUCache(int capacity) {
    
            this.capacity = capacity;
            this.map = new HashMap<>();
    
            // Dummy nodes
            this.head = new Node(-1, -1);
            this.tail = new Node(-1, -1);
    
            head.next = tail;
            tail.prev = head;
        }
    
        // Remove node from DLL
        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    
        // Add node right after head (most recently used)
        private void addNode(Node node) {
            node.next = head.next;
            node.prev = head;
    
            head.next = node;
            node.next.prev = node;
        }
    
        public int get(int key) {
    
            if (!map.containsKey(key)) return -1;
    
            Node node = map.get(key);
    
            // Move to front (MRU)
            removeNode(node);
            addNode(node);
    
            return node.val;
        }
    
        public void put(int key, int value) {
    
            if (map.containsKey(key)) {
    
                Node node = map.get(key);
                node.val = value;
    
                removeNode(node);
                addNode(node);
            }
            else {
    
                // Evict LRU if capacity reached
                if (map.size() == capacity) {
                    Node lru = tail.prev;
                    removeNode(lru);
                    map.remove(lru.key);
                }
    
                Node node = new Node(key, value);
                addNode(node);
                map.put(key, node);
            }
        }
    }