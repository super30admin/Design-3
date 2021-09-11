// Time Complexity : O(1)
// Space Complexity : O(C) where c is the Capacity.
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class LRUCache {
public:
    class Node{
    public:
        int key, val;
        Node* prev, *next;
        Node(int key, int val){
            this->key = key;
            this->val = val;
        }
    };
    Node *head, *tail;
    
    void addToHead(Node* node){
        node->next = head->next;
        node->prev = head;
        node->next->prev = node;
        head->next = node;
    }
    
    void removeNode(Node* node){
        node->next->prev = node->prev;
        node->prev->next = node->next;
        
    }
    
    int capacity;
    unordered_map<int, Node*> map;
    LRUCache(int capacity) {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head->next = tail;
        tail->prev = head;
        this->capacity = capacity;
    }
    
    int get(int key) {
        if(map.find(key) != map.end()){
            Node* node = map[key];
            removeNode(node);
            addToHead(node);
            return node->val;
        }
        return -1;
    }
    
    void put(int key, int value) {
        if(map.find(key) != map.end()){
            Node* node = map[key];
            node->val = value;
            removeNode(node);
            addToHead(node);
        }
        else{
            if(capacity == map.size()){
                Node* node = tail->prev;
                removeNode(node);
                map.erase(node->key);
            }
            Node* node = new Node(key, value);
            addToHead(node);
            map[key] = node;
        }
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
