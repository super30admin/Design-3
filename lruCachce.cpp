// Time Complexity :O(1) 
// Space Complexity : O(C) - C is the capacity.  
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
class ListNodes {
public:
    int key;
    int val;
    ListNodes *next;
    ListNodes *prev;
    ListNodes(){
        key = -1;
        val = -1;
        next = nullptr;
        prev = nullptr;
    }  
};
class LRUCache {
    int capacity;
public:
    ListNodes *head; //= new ListNodes();
    ListNodes *tail;
    unordered_map<int,ListNodes*> hMap;
    LRUCache(int capacity) {
        this->capacity = capacity;
        head = new ListNodes();
        tail= new ListNodes();
        head->next = tail;
        tail->prev = head;
    }
    void addToList(ListNodes* curr){
        curr->next = head->next;
        head->next->prev = curr;
        curr->prev = head;
        head->next = curr;
    }
    void removeFromList(ListNodes* node){
        node->next->prev = node->prev;
        node->prev->next = node->next;
    }
    
    int get(int key) {
        if(hMap.find(key) != hMap.end()){
            ListNodes *node = hMap[key];
            removeFromList(node);
            addToList(node);
            return hMap[key]->val;
        }
        else return -1;
    }
    
    void put(int key, int value) {
        if(hMap.find(key) != hMap.end()){
            removeFromList(hMap[key]);
            addToList(hMap[key]);
            hMap[key]->val = value;
        }
        else{
            ListNodes *newNode = new ListNodes();
            newNode->key = key;
            newNode->val = value;
            if(hMap.size() == capacity){
                ListNodes *node = tail->prev;
                hMap.erase(node->key);
                removeFromList(node);
                addToList(newNode);
                hMap[key] = newNode;   
            }
            else{
                addToList(newNode);
                hMap[key] = newNode;
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