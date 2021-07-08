/* Problem Statement:
Verified on leetcode

146. LRU Cache
Medium

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2);

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4



 *
 * Time Complexity : O(1), get and put operations
 * Space complexity : O(n) where n is capacity 
 *
 */


class LRUCache {
    int capacity;
    list<std::pair<int, int>> dll;
    unordered_map<int, decltype(dll)::iterator> hashmap;
    
public:
    LRUCache(int capacity) {
        this->capacity = capacity;

    }
    /* If key exists, then return that value , else return -1 */
    int is_key_present_in_list(int key) {
        int value = -1;

        unordered_map<int, decltype(dll)::iterator>::iterator iterate = hashmap.find(key);
        /* Found entry, move the LL element to the front of the list by using splice */
        if (iterate != hashmap.end()) {
            value = iterate->second->second;
            dll.splice(dll.begin(), dll, iterate->second);
        }
        return value;
    }
    
    int get(int key) {
        return is_key_present_in_list(key);
    }
    
    void put(int key, int value) {
        /* If entry is found, update it with new value */
        if (-1 != is_key_present_in_list(key)) {
            dll.front().second = value;
        }else {
            /* Push new entry to the front of doubly linked list */
            dll.push_front(std::pair<int,int>(key,value));
            /* add to hash table */
            hashmap[key] = dll.begin();
            /* Wow !! missed capacity, time to remove the last element in the list */
            if (dll.size() > capacity) {
                hashmap.erase(dll.back().first);
                dll.pop_back();
            }
        }
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
/* Execute on leetcode platform */


