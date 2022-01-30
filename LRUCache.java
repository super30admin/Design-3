// We will use a map for keeping track of the key and values
// We use a doubly link list to check which nodes were accessed least recently (it would be at the end), the node will be stored as the value of the map
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

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addNodeToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }

    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;

        Node node = map.get(key);
        removeNode(node);
        addNodeToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        // if the key is not present in the map
        if(!map.containsKey(key)) {
            // check if the map is full, if so we need to remove the key from map and from the doubly link list (before tail)
            if(capacity == map.size()) {
                Node tailPrev = tail.prev;
                map.remove(tailPrev.key);
                removeNode(tailPrev);
            }
            Node node = new Node(key, value);
            map.put(key, node);
            addNodeToHead(node);
        }
        else {
            // Get the key and update its value
            Node node = map.get(key);
            node.value = value;

            //remove the existing node and move it to the front
            //add the existing node next to the head
            removeNode(node);
            addNodeToHead(node);
        }
    }
}