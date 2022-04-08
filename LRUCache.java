/**
 * time complexity for get and put is O(1)
 * space complexity is capacity.
 * 
 * every time an element is accessed - it is removed and added 
 * when the size reaches capacity, the first element is deleted since the LinkedHashMap maintains order of insertion.
 */
import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache {
    
    Map<Integer, Integer> map = new LinkedHashMap<>();
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(map.containsKey(key)) {
            int val = map.get(key);
            this.put(key, val);
            return val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            map.remove(key);
        } else if(map.size() == capacity) {
            this.remove(map.keySet().iterator().next());
        }
        map.put(key, value);
    }
    
    private void remove(int key) {
        map.remove(key);
    }
}