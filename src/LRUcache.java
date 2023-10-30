// Time Complexity:  O(1)
// Space Complexity: O(n)

class LRUCache {

    class Node {                                         // Node used for maintaining sequence as a LinkedList
        int key; int val;
        Node next; Node prev;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    int capacity;
    Node dummyHead; Node dummyTail;                     // dummy nodes
    Map<Integer, Node> map;                             // map key->node for updating value and accessing nodes in O(1) time

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.dummyHead = new Node(-1, -1);
        this.dummyTail = new Node(-1, -1);
        dummyHead.next = dummyTail;
        dummyTail.next = dummyHead;
        this.map = new HashMap<>();
    }
    
    public int get(int key) {
        if(!map.containsKey(key))                       // if key not there, invalid
            return -1;
        Node node = map.get(key);                       // get node
        removeNode(node);                               // remove it from LinkedList
        addNodeHead(node);                              // add it to head(for most recently used)
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {                      // if key is already there
            Node node = map.get(key);                   // get node
            node.val = value;                           // change node value only(no need to update map value(which is node))
            removeNode(node);                           // remove it from LinkedList
            addNodeHead(node);                          // add it to head(for most recently used)
        }
        else {                                          // if key not there, new node coming
            if(map.size() == capacity) {                // and LinkedList is full
                Node LRUNode = dummyTail.prev;          // get LRU node
                removeNode(LRUNode);                    // remove it from LinkedList
                map.remove(LRUNode.key);                // remove it from map
                System.out.println("map size: "+map.size()+" key: "+key);
            }
            Node newNode = new Node(key, value);        // LinkedList is not full, create new node
            addNodeHead(newNode);                       // add it to head(for most recently used)
            map.put(key, newNode);                      // add it to map
        }
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addNodeHead(Node node) {
        node.next = dummyHead.next;
        node.prev = dummyHead;
        dummyHead.next.prev = node;
        dummyHead.next = node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
