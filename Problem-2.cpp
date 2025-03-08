// https://leetcode.com/problems/lru-cache/
// Time Complexity : 
// get() - O(1)
// put() - O(1)
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach


class LRUCache {
    class Node{
    public:
        int key;
        int val;
        Node* next;
        Node* prev;

        Node(int key, int val) : key(key), val(val), next(NULL), prev(NULL) {}
    };
public:
    int cap;
    unordered_map<int, Node*> mp;
    Node* oldest;
    Node* latest;

    LRUCache(int capacity){
        oldest = new Node(0,0);
        latest = new Node(0,0);
        latest->next = oldest;
        oldest->prev = latest;
        cap = capacity;
    }
    
    int get(int key) {
        if(mp.find(key) != mp.end()){
            Node* node = mp[key];
            remove(node);
            insert(node);
            return node->val;
        }
        return -1;
    }
    
    void put(int key, int value) {
        if(mp.find(key) != mp.end()){
            remove(mp[key]);
        }
        Node* newNode = new Node(key, value);
        mp[key] = newNode;
        insert(newNode);
        if(mp.size() > cap){
            Node* lru = oldest->prev;
            remove(lru);
            mp.erase(lru->key);
            delete lru;
        }

    }
private:
    void insert(Node* node){
        node->next = latest->next;
        node->prev = latest;
        latest->next = node;
        node->next->prev = node;
    }
    void remove(Node* node){
        node->prev->next = node->next;
        node->next->prev = node->prev;
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */