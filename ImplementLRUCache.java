//Time Complexity: O(1)
//Space Complexity: O(n)
class LRUCache {
    int capacity;
    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    class Node
    {
        int key;
        int value;

        Node next;
        Node prev;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = tail;
        this.tail.prev = head;
    }

    private void removeNode(Node node)
    {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        node.next = null;
        node.prev = null;
    }

    private void addNodeToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }

        Node node = map.get(key);
        removeNode(node);
        addNodeToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key))
        {
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addNodeToHead(node);
        }
        else
        {    //non existing node
            //capacity, no capacity
            if(map.size() == capacity){
                Node last = tail.prev;
                removeNode(last);
                map.remove(last.key);
            }
            Node newNode = new Node(key, value);
            addNodeToHead(newNode);
            map.put(key, newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */