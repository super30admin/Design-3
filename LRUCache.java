import java.util.*;

// O(1) time, O(capacity) space
class LRUCache {

    int capacity;
    Map<Integer, DLLNode> map;
    DLLNode head;
    DLLNode tail;

    // O(1) time, O(n) space
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new DLLNode(0, 0); // least recently used
        tail = new DLLNode(0, 0); // most recently used
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            // Update to most recent used by removing from map and inserting to DLL
            DLLNode node = map.get(key);
            remove(node);
            insert(node);
            return node.value;
        }
        else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            DLLNode node = map.get(key);
            node.value = value; // update to given value
            remove(node); // remove from DLL
            insert(node); // insert as MRU now
        }
        else { // create new node and add to map and DLL
            DLLNode newNode = new DLLNode(key, value);
            map.put(key, newNode);
            insert(newNode);

            if (map.size() > capacity) { // we have to evict lru
                DLLNode lru = head.next; // true lru
                map.remove(lru.key);
                remove(lru);
            }
        }
    }

    private void remove(DLLNode node) {
        DLLNode prev = node.prev;
        DLLNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private void insert(DLLNode node) {
        DLLNode prev = tail.prev;
        DLLNode next = tail;

        prev.next = node;
        next.prev = node;

        node.next = next;
        node.prev = prev;
    }
}

// Doubly Linked List
class DLLNode {
    int key;
    int value;
    DLLNode next;
    DLLNode prev;

    public DLLNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */