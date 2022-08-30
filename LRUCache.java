// Time Complexity :
// Space Complexity :
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
//146. LRU Cache (Medium) - https://leetcode.com/problems/lru-cache/
class LRUCache {
    
    class Node {
        int key;
        int val;
        Node prev;
        Node next;
        
        public Node(int key, int value) {
            this.key = key;
            this.val = value;
        }
    }

    int capacity;
    Node head;
    Node tail;
    HashMap<Integer, Node> map;
    
    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = tail;
        this.tail.prev = head;
        this.capacity = capacity;
    }
    
    private void addToHead(Node node) { // O(1)
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    private void remove(Node node) { // O(1)
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        
        Node node = map.get(key);
        remove(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            remove(node);
            addToHead(node);
        } else {
            if (map.size() == capacity) {
                Node tailPrev = tail.prev;
                remove(tailPrev);
                map.remove(tailPrev.key);
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