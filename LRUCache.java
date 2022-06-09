//Time: O(1) |Space: O(No of unique keys w.r.t capacity) due to the hashMap and LL

class LRUCache {
    //DLL
    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    Node head;
    Node tail;
    int size;
    // Map to maintain key node pair
    Map<Integer, Node> map;
    public LRUCache(int capacity) {
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.size = capacity;
        map = new HashMap<>();
    }
    public int get(int key) {
        // if key exists, remove that from its curr pos,
        // add it to the top of LL as it is latest used
        if(map.containsKey(key)) {
            Node nodeToReturn = map.get(key);
            removeNode(nodeToReturn);
            addToHead(nodeToReturn);
            return nodeToReturn.value;
        }
        return -1;
    }
    // to add it to the top of LL,
    // need to make head's next and head's next pointing to curr node
    // curr node's prev pointing to head and next pointing to head's prev next
    public void addToHead(Node node) {
        node.prev = this.head;
        node.next = this.head.next;
        node.next.prev = node;
        this.head.next = node;
    }
    public void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    public void put(int key, int value) {
        // if key present, update its value and remove it from its curr pos and add it to the head of LL
        if(map.containsKey(key)) {
            Node nodeToModify = map.get(key);
            nodeToModify.value = value;
            removeNode(nodeToModify);
            addToHead(nodeToModify);
        } else {
            // when capacity is reached, remove the least used from before the tail
            if(size == map.size()) {
                Node prevsPrev = this.tail.prev;
                Node nodeToDelete = map.get(prevsPrev.key);
                removeNode(nodeToDelete);
                map.remove(prevsPrev.key);
            }
            // create freshNode, add the entry to the hashMap and head of LL
            Node freshNode = new Node(key, value);
            addToHead(freshNode);
            map.put(key, freshNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */