/**
Leet Code Submitted : YES
Space Complexity : O(Capacity)
Time Complexity : O(1)

The idea is to use doubly linked list and Hash Map for LRU cache. Where the remove Eldest Entry method is overrided to remove elements which are older than size of the cache.
**/

class LRUCache {
    int capacity;
    LinkedHashMap<Integer,Integer> hm;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.hm = new LinkedHashMap<>(capacity);
        this.hm = new LinkedHashMap<Integer,Integer>(){
            public boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) 
            {   
                return size() > capacity; 
            } 
        };
    }
    
    public int get(int key) {
        if(!hm.containsKey(key)) 
            return -1;
        Integer value = hm.get(key);
        hm.remove(key);
        hm.put(key,value);
        return value;
    }
    
    public void put(int key, int value) {
        if(hm.containsKey(key)){
            hm.remove(key);
            hm.put(key,value);
        }else{
            hm.put(key,value);
        }  
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
