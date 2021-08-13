class LRUCache {
    int capacity=0;
    LinkedHashMap<Integer,Integer> lhm= new LinkedHashMap<>(){
        protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest){
            return size()>capacity;
        }
    };

    public LRUCache(int capacity) {
        this.capacity=capacity;
    }

    public int get(int key) {
        if(lhm.containsKey(key)){
            int result= lhm.get(key);
            lhm.remove(key);
            lhm.put(key,result);
            return result;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if(lhm.containsKey(key)){
            int result= lhm.get(key);
            lhm.remove(key);
            lhm.put(key,value);
        } else {
            lhm.put(key,value);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */