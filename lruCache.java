// Time Complexity : O(1)
// Space Complexity : O(n), where n is the number of elements the user puts.
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

/*
1. make a double linkedlist and a hashmap
2. Keep the new elements at the start of LL and keep pushing old to behind
3. Maintain Hashmap to access necessary node in LL
*/


// Your code here along with comments explaining your approach
class LRUCache {
    class Node {
        Node next; Node prev;
        int val; int key;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    private void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    HashMap<Integer, Node> map;
    Node head; Node tail;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        } else {
            if(capacity == map.size()) {
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
        }
    }
}
