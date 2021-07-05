// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Almost (Got TLE on last test case)
// Any problem you faced while coding this: Could have used Linked List for LRU policy

class LRUCache {
private:
    unordered_map<int, int> cache_map;
    vector<int> lru_queue;
    int cap;
    
public:
    LRUCache(int capacity) {
        cap = capacity;
    }
    
    int search_key(int key)
    {
        for(int i = 0; i < lru_queue.size(); i++)
        {
            if(lru_queue[i] == key)
            {
                return i;
            }
        }
        
        // Shouldn't come here
        return -1;
    }
    
    int get(int key) {
        
        // Key present
        if(cache_map.find(key) != cache_map.end())
        {
            int ret_val = cache_map[key];
            
            int idx = search_key(key);
            
            lru_queue.erase(lru_queue.begin() + idx);
            
            lru_queue.push_back(key);
            
            return ret_val;
        }
        else
        {
            return -1;
        }
        
        // Shouldn't come here
        return -1;
    }
    
    void put(int key, int value) {

        // Key already present
        if(cache_map.find(key) != cache_map.end())
        {
            // updating value
            cache_map[key] = value;
            
            int idx = search_key(key);
            
            lru_queue.erase(lru_queue.begin() + idx);
            
            lru_queue.push_back(key);
        }
        // Key not present
        else
        {
            cache_map.insert({key, value});

            lru_queue.push_back(key);
            
            if(lru_queue.size() > cap)
            {
                int key = lru_queue[0];
                
                cache_map.erase(key);
                
                lru_queue.erase(lru_queue.begin());
            }            
        }
    }
};
