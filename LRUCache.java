// Time Complexity : O(1) 
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// HashMap + doubly linked list

class LRUCache {
    
    class Node {
        int key;
        int val;
        Node next;
        Node prev;
        Node(int k, int v) {
            key = k;
            val = v;
        }
    }
    
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void addAfterHead(Node node) {
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }
    
    HashMap<Integer, Node> map;
    Node head, tail;
    int capacity;
    
    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addAfterHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addAfterHead(node);
        } else {
            Node newNode = new Node(key, value);
            if (map.size() < capacity) {
                addAfterHead(newNode);
                map.put(key, newNode);
            }
            else {
                Node toRemove = tail.prev;
                removeNode(toRemove);
                map.remove(toRemove.key);
                addAfterHead(newNode);
                map.put(key, newNode);
            }
        } 
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */