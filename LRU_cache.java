//TC:O(1) on avg 
//SC:O(n)

public class LRU_cache {
    class LRUCache {

        int size;
        Map<Integer, Integer> map = new LinkedHashMap<>();
        public LRUCache(int capacity) {
            size  = capacity;
        }
        
        public int get(int key) {
            Integer val = map.getOrDefault(key, -1);
            if(val != -1){
                map.remove(key);
                put(key, val);
            }
            return val;
        }
        
        public void put(int key, int value) {
            if(map.size() == size && !map.containsKey(key)){
                Integer del = map.keySet().iterator().next();
                map.remove(del);
            }else if(map.containsKey(key)){
                map.remove(key);
            }
            map.put(key, value);
        }
        }
        
        
}
