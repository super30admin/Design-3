//Problem 80 : Design LRU Cache - Using Linked HashMap
//TC: O(1)
//SC: O(capacity)

/*
For Linked HashMap : Keep Most used(Get request)/updated or newly added towards end of the linkedlist and if capacity is full remove the first node, 

*/
import java.util.*;
class LRUCache {
     
    private Map<Integer,Integer> map;
    private int capacity;
    public LRUCache(int capacity) {
        //TC: O(1) | SC:O(Capacity)
        //Recent used keys will be at last and least recent used key that can be removed will be at first position
        //Get and Put kequest -> for all these requests elements will be stored in last of the hashmap
        map = new LinkedHashMap<>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        
        int val = map.get(key);
        map.remove(key);
        //key will be inserted in last
        map.put(key,val);
        
        return val;
    }
    
    public void put(int key, int value) {
        
        if(map.containsKey(key)){
            map.remove(key);
            map.put(key,value);
        }else{
            if(map.size()==capacity){
                
                int keyToRemove=-1;
                //get the first key
                for(int k : map.keySet()){
                    keyToRemove = k;
                    break;
                }
               map.remove(keyToRemove);
            }
            map.put(key,value);
        }
        
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */