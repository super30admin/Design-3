// https://leetcode.com/problems/lru-cache/
// Time Complexity : O(1) for both get and put operations
// Space Complexity : O(n) where n is capacity;
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class LRUCache {
    HashMap<Integer, Node> map;
    int capacity;
    Node head;
    Node tail;

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

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    private void addToHead(Node node){
        node.prev = this.head;
        node.next = this.head.next;
        node.next.prev = node;
        this.head.next = node;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;

    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
        } else{
            if(map.size() == capacity){
                Node lastNode = tail.prev;
                removeNode(lastNode);
                map.remove(lastNode.key);
            }
            Node newNode = new Node(key, value);
            addToHead(newNode);
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