/**
Time Coplexity  
          get    - O(1). 
          put    - O(1). 
Space Complexity - O(n) where n is Maximum(capacity, no of keys in cache).
 */
class LRUCache {

    HashMap<Integer, DLL> map;
    DLL head, tail;
    int capacity, size;

    public class DLL {
        int key, val;
        DLL prev, next;

        public DLL(int key, int val) {
            this.key = key;
            this.val = val;  
        }
    }

    public void moveToHead(DLL node) {
        remove(node);
        insert(node);
    }

    public DLL remove(DLL node) {
        DLL prev = node.prev;
        DLL next = node.next;
        prev.next = next;
        next.prev = prev;
        return node;
    }

    public void insert(DLL node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public DLL removeFromTail() {
        return remove(tail.prev);
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        map = new HashMap<Integer, DLL>();
        head = new DLL(0, 0);
        tail = new DLL(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        DLL node = map.get(key);
        moveToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            DLL node = map.get(key);
            node.val = value;
            map.put(key, node);
            moveToHead(node);
            return;
        }
        DLL node = new DLL(key, value);
        insert(node);
        map.put(key, node);
        size++;
        if(size > capacity) {
            DLL del = removeFromTail();
            map.remove(del.key);
            size--;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
