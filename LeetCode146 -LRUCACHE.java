/*tc: O(1) for all the operation
sc: O(capacity) for both Map and LinkedList
*/

class LRUCache {

    class Node {
        int key, value;
        Node next, prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Node head, tail;
    int capacity;

    Map<Integer, Node> map;

    public LRUCache(int capacity) {

        this.capacity = capacity;
        map = new HashMap<>();

        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    // remove Node
    public void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    // Add to Head
    public void addToHead(Node node) {
        // 4 - >5
        node.next = head.next;
        node.next.prev = node;

        // head <--> node

        head.next = node;
        node.prev = head;

    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;

        Node node = map.get(key);
        // remove Node
        removeNode(node);
        addToHead(node);
        return node.value;
    }

    public void put(int key, int value) {

        if (map.containsKey(key)) // just update the value
        {
            // update node in the list
            Node newNode = map.get(key);
            newNode.value = value;

            // remove Node
            removeNode(newNode);
            // add to Head
            addToHead(newNode);
        } else {
            if (capacity == map.size()) // check capacity
            {
                Node node = tail.prev;
                removeNode(node);
                node.next = null;
                node.prev = null;
                map.remove(node.key);
            }
            Node node = new Node(key, value);
            addToHead(node);
            map.put(key, node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */