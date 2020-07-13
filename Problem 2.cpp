//Time Complexity : O(1)
// Space Complexity :O(n)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No
class Pair{
public:
    int key, value;
    Pair(int k, int v ){
        key = k;
        value = v;
    }
    
};

class LRUCache {
public:
    list<Pair*> ll;
    int cap;
    map<int, list<Pair*>::iterator> m;
    
    LRUCache(int capacity) {
        cap = capacity;
    }
    
    int get(int key) {
        if(m.find(key)==m.end()) return -1;
        auto iter = m[key];
        Pair* obj = *iter;
        ll.erase(iter);
        ll.push_front(obj);
        m[key] = ll.begin();
        return obj->value;
    }
    
    void put(int key, int value) {
        if(m.find(key)!=m.end()){
            auto iter = m[key];
            Pair* obj = *iter;
            
            ll.erase(iter);
            obj->value = value;
            
            ll.push_front(obj);
            m[key] = ll.begin();
        }
        else{
            if(m.size()>=cap){
                Pair* temp = ll.back();
                m.erase(temp->key);
                ll.pop_back(); 
             }
             Pair* obj= new Pair(key, value);
             ll.push_front(obj);
             m[key] = ll.begin();
        }
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */