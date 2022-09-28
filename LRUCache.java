// Approach: we use a combination of a hashmap (to get and put elements in O(1) time) and a doubly linked list. We don't use a singly linked list as when we have to remove a node and bring it to the front, we cannot do this in O(1) with singly as we don't have the previous pointer. Removing is O(1) with doubly linked list. Also in our map<Integer,ListNode>, our value is a ListNode as we want the reference in O(1) time so that we know which node is to be removed and move to the beginning of the list, otherwise we will have to traverse the list to find the reference and that won't be O(1)

// Space complexity: O(capacity) since space is used only for hashmap and doubly linked list with at most capacity + 1 elements

class LRUCache {
    
    class ListNode {
        int key;
        int val;
        ListNode prev;
        ListNode next;
        
        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    HashMap<Integer,ListNode> map;
    ListNode head;
    ListNode tail;
    int capacity;
    
    public LRUCache(int capacity) { // O(1)
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.head = new ListNode(-1, -1);
        this.tail = new ListNode(-1, -1);
        this.head.next = tail;
        this.tail.prev = head;
    }
    
    public int get(int key) { // O(1)
        // remove node and bring it to the front
        if (!map.containsKey(key)) {
            return -1;
        }
        ListNode node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) { // O(1)
        // existing. update node value and remove node and bring it to the front
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }
        // fresh node
        else {
            if (map.size() == capacity) {
                // remove last element (least recently used) from list and map
                ListNode last = tail.prev;
                removeNode(last);
                map.remove(last.key);
            }
            // create new node, add to head and add to map
            ListNode newNode = new ListNode(key, value);
            addToHead(newNode);
            map.put(key, newNode);
        }
    }
    
    // add node to head (front of list as it is the most recently used element)
    private void addToHead(ListNode node) {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    // remove node from list
    private void removeNode(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */