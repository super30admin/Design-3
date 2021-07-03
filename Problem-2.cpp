// I hate amazon for coming up with this question
// lru is linked list, map of key-iterator is iterator
//key value is a different pair
//this question tests your DS knowledge more than pure coding skills
//SC is O(n) (it's a cache, duh) tc per operation is different, get, put and update is O(1) amortized and it has to be that, or our PCs would be crap if there's sequential access all the time
class LRUCache {
public:
int size;
list<int> lru;                              // MRU ... LRU
unordered_map<int, list<int>::iterator> mp; // key -> iterator
unordered_map<int, int> kv;                 // key -> value

LRUCache(int capacity) : size(capacity) {}

int get(int key) {
    if (kv.count(key) == 0) return -1;
    updateLRU(key);
    return kv[key];
}

void put(int key, int value) {
    if (kv.size() == size && kv.count(key) == 0)
        evict();
    updateLRU(key);
    kv[key] = value;
}

void updateLRU(int key) {
    if (kv.count(key)) 
        lru.erase(mp[key]);
    lru.push_front(key);
    mp[key] = lru.begin();
}

void evict() {
    mp.erase(lru.back());
    kv.erase(lru.back());
    lru.pop_back();
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
