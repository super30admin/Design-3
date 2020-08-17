/*
// Time Complexity : O(1) put, O(1) get, O(1)remove.
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :
// nope
// Your code here along with comments explaining your approach
*/

class LRUCache {
    class Node
    {
        int val;
        int key;
        Node prev;
        Node next;
    public Node(int key, int val)
    {
        this.key = key;
        this.val = val;
    }
    }
    
    public void removeNode(Node node)
    {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    public void addAfterHead(Node node)
    {
        node.next = head.next;
        head.next = node;
        node.prev = head;
        node.next.prev = node;
    }
    HashMap<Integer, Node> map;
    int capacity;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
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