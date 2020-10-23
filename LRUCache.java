// Time Complexity : O(capacity) for get() and put() operations 
// Space Complexity : O(capacity) for cache HashMap + O(capacity) for rank HashMap 
// Did this code successfully run on Leetcode : TLE, 17/18, test cases passed 
// Any problem you faced while coding this : coming up with a way to keep track of the ranks for the items
// that way could update and remove the one with max rank

// Your code here along with comments explaining your approach
// used two hashmaps one for cache, one to maintain rank of items
// on get increment all other ranks by 1, set this one to 1
// on put if under capacity do same as above and put entry in cache
// else find and delete the key with max rank and repeat the above step

class LRUCache {
    
    HashMap<Integer, Integer> cache;
    HashMap<Integer, Integer> rank;
    int capacity;

    public LRUCache(int capacity) {
        if(capacity>0){
            this.capacity = capacity;
            this.cache = new HashMap(capacity, 1);
            this.rank = new HashMap(capacity, 1);
        }
    }
    
    public int get(int key) {
        if(cache.containsKey(key)){
            int val = cache.get(key);
            
            incrementAllRanks();
            rank.put(key, 1);
            
            return val;
        }
        else{
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if(cache.containsKey(key)){
            cache.put(key, value);
            
            incrementAllRanks();
            rank.put(key, 1);
        }
        else{
            if(cache.size()==capacity){
                removeKeyWithMaxRank();
           
                cache.put(key, value);

                incrementAllRanks();                
                rank.put(key, 1);
            }
            else{
                cache.put(key, value);
                incrementAllRanks();   
                rank.put(key, 1);
            }
        }
    }
    
    private void incrementAllRanks(){
        for(int k : rank.keySet()){
            rank.put(k, rank.get(k)+1);
        }
    }
    
    private void removeKeyWithMaxRank(){
        //get key with max value
        int keyWithMaxRank = -1;
        int maxRank = -1;
        for(int k : rank.keySet()){
            if(rank.get(k)>maxRank){
                maxRank = rank.get(k);
                keyWithMaxRank = k;
            }
        }
        
        //remove k,v
        cache.remove(keyWithMaxRank);
        rank.remove(keyWithMaxRank);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */