class LRUCache {
public:
    class Node {
        public:
        int key;
        int val;
        Node* prev;
        Node* next;
        
        Node(int pkey, int pval) {
            key = pkey;
            val = pval;
            prev = nullptr;
            next = nullptr;
        }
    };
    Node* head;
    Node* tail;
    unordered_map<int, Node*> mp;
    int capacity;
    void addToHead(Node* node) {
        node->next = head->next;
        head->next = node;
        node->prev = head;
        node->next->prev = node;
    }
    
    void removeNode(Node* node) {
        node->prev->next = node->next;
        node->next->prev = node->prev;
    }
    
    LRUCache(int capacity) {
        this->capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head->next = tail;
        tail->prev = head;
    }
    
    int get(int key) {
        if(mp.find(key) == mp.end()) return -1;
        Node* node = mp[key];
        removeNode(node);
        addToHead(node);
        return node->val;
    }
    
    void put(int key, int value) {
        if(mp.find(key) != mp.end()) {
            Node* node = mp[key];
            node->val = value;
            removeNode(node);
            addToHead(node);
        }
        else { // put new element
            Node* node = new Node(key, value);
            if(capacity == mp.size()) {
                // remove LRU element which is before tail
                Node* tailPrev = tail->prev;
                removeNode(tailPrev);
                mp.erase(tailPrev->key);
            }
            addToHead(node);
            mp[key] = node;
        }
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
