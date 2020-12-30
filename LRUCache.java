/** Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.*/
/**
* TC O(1) both for put and get. SC O(capacity) 
*/
class LRUCache {

    class Node {
        int key, value;
        Node prev, next;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private int capacity;
    private Node head, tail;
    Map<Integer, Node> nodeMap;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        nodeMap = new HashMap<>();
    }
    
    private void addToBeg(Node curr) {
        head.next.prev = curr;
        curr.next = head.next;
        curr.prev = head;
        head.next = curr;
    }
    
    private void remove(Node curr) {
        curr.next.prev = curr.prev;
        curr.prev.next = curr.next;
    }
    
    public int get(int key) {
        if (nodeMap.containsKey(key)) {
            Node curr = nodeMap.get(key);
            remove(curr);
            addToBeg(curr);
            return curr.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        Node curr = head;
        if (nodeMap.containsKey(key)) {
            curr = nodeMap.get(key);
            curr.value = value;
            remove(curr);
            addToBeg(curr);
            return;
        }
        else if(nodeMap.size() == capacity) {
            int newKey = tail.prev.key;
            remove(tail.prev);
            nodeMap.remove(newKey);
        }
        curr = new Node(key, value);
        addToBeg(curr);
        nodeMap.put(key, curr);
    }
}
