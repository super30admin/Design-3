// Time Complexity : O(1) get and set
// Space Complexity: O(n) for hashmap

class LRUCache {

    class Node {
        int key; int val;
        Node next;
        Node prev;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    // O(1)
    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;

    }
    // O(1)
    public void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    Map<Integer, Node> map;
    Node head; Node tail;
    int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;

    }

    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            // remove it from the original position and add to head to make it most recent
            addToHead(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        // case 1 -> if node is already present
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }
        // case 2 -> new node
        else{
            Node newNode = new Node(key, value);
            if(map.size() == capacity){
                // remove the least recently used node
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            addToHead(newNode);
            map.put(key, newNode);
        }
    }
}
