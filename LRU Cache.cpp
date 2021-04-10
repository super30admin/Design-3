// Time Complexity : O(1) 
// Space Complexity : O(n) where n is the capacity of LRU
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class LRUCache {
public:
    class Node{
        public:
        int key, value;
        Node *next, *prev;
        Node(int key, int value){
            this->key = key;
            this->value = value;
        }
    };
    Node *head, *tail;
    int cap;
    unordered_map<int, Node*> map;
    
    LRUCache(int capacity) {
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head->next = tail;
        tail->prev = head;
        cap = capacity;
    }
    void remove(Node *current){
        current->next->prev = current->prev;
        current->prev->next = current->next;
    }
    
    void addtobegin(Node *current){
        current->next = head->next;
        head->next->prev = current;
        current->prev = head;
        head->next = current;
    }
    
    int get(int key) {
        if(map.find(key)!=map.end()){
            Node *current = map[key];
            remove(current);
            addtobegin(current);
            return current->value;
        }
        return -1;
    }
    
    void put(int key, int value) {
        Node *current = head;
        if(map.find(key)!=map.end()){
            current = map[key];
            current->value = value;
            remove(current);
            addtobegin(current);
            return;
        }
        else if(map.size()==cap){
            int key = tail->prev->key;
            remove(tail->prev);
            map.erase(key);
        }
        current = new Node(key, value);
        addtobegin(current);
        map.insert(pair<int, Node*>(key, current));
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
