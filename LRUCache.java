/*
Problem: https://leetcode.com/problems/lru-cache/
TC: O(1)
SC: O(capacity)
*/
class Node {
    int key, value;
    Node prev, next;
    
    public Node(int k, int v) {
        key = k;
        value = v;
        prev = null;
        next = null;
    }
}

class DLL {
    Node head, tail;
    
    public DLL() {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    public Node evict() {
        Node evict = head.next;
        remove(evict);
        return evict;
    }
    
    public void addToEnd(Node n) {
        Node end = tail.prev;
        end.next = n;
        n.prev = end;
        n.next = tail;
        tail.prev = n;
    }
    
    public void moveToEnd(Node n) {
        remove(n);
        addToEnd(n);
    }
    
    private void remove(Node n) {
        Node prev = n.prev;
        Node next = n.next;
        prev.next = next;
        next.prev = prev;
    }
}

class LRUCache {

    int curSize, capacity;
    HashMap<Integer, Node> cache;
    DLL dll;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        curSize = 0;
        dll = new DLL();
        cache = new HashMap<Integer, Node>();
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node n = cache.get(key);
        dll.moveToEnd(n);
        return n.value;
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node n = cache.get(key);
            n.value = value;
            dll.moveToEnd(n);
        } else {
            if (curSize == capacity) {
                Node evict = dll.evict();
                cache.remove(evict.key);
                --curSize;
            }
            ++curSize;
            Node n = new Node(key, value);
            dll.addToEnd(n);
            cache.put(key, n);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */