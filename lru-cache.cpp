// TC: O(1) for put and get
// SC: O(capacity)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// we are using Linked HashMap DS for LRUCache
class LRUCache {
public:
    class Node {
    public:
        int key, val;
        Node *next, *prev;
        Node(int key, int val) {
            this->key = key;
            this->val = val;
        }
    };
    Node *head, *tail;
    unordered_map<int, Node*> map; // HashMap of Key & Node
    int capacity;
    LRUCache(int capacity) {    // H -><- T
        this->head = new Node(-1, -1); // creating 2 dummy Nodes head and tail
        this->tail = new Node(-1, -1);
        this->head->next = this->tail;
        this->tail->prev = this->head;
        this->capacity = capacity;
    }
    // writing 2 helper functions
    void removeNode(Node* node) { // O(1)
        node->prev->next = node->next;
        node->next->prev = node->prev;
    }
    void addToHead(Node* node) { // O(1)
        node->next = head->next;
        head->next = node;
        node->prev = head;
        node->next->prev = node;
    }
    int get(int key) {
        if(map.find(key) == map.end()) return -1; // DNE
        Node* node = map[key];
        removeNode(node);
        addToHead(node);
        return node->val;
    }
    
    void put(int key, int value) {
        if (map.find(key)!= map.end()) { // already existing node
            Node* node = map[key];
            node->val = value;
            removeNode(node);
            addToHead(node);
        }
        else { // new node
            if (map.size() == capacity) { // check for size
                Node* tailPrev = tail->prev;
                removeNode(tailPrev); // remove from both the DS, HashMap & LL
                map.erase(tailPrev->key);
            }
            Node* newNode = new Node(key, value);
            addToHead(newNode); // similarly add to both DS
            map[key] = newNode;
        }
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */