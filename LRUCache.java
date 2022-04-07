import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache extends LinkedHashMap<Integer, Integer>{
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75F, true); // default load factor is 0.75, access order true, not insertion order
        this.capacity = capacity;
    }
    
    public int get(int key) { // hashmap
        return super.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) { // hashmap
        super.put(key, value);
    }
    
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> oldest){ // linkedlist 
        return size() > capacity;
    }
    
    
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */