// Time Complexity : O(1) for both put and get  
// Space Complexity : O(n) // map + LL
// Did this code succesfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// 1. Maintain a map of input key to pointer to node and a doubly LL with head and tail initialized to empty nodes
// 2. New nodes added or nodes updated move to front of LL and LRU node is pointed by tail
// 3. Map helps giving reference to a node to make moving to front of LL and updating tail an O(1) operation

// put operation:
// 1. If already present: update val, removeNode, moveToFront
// 2. If new element: if map is full then remove LRU node from map and LL; always for new elem: add new node to map and moveToFront 

//get operation:
// 1. If present in the map: removeNode, moveToFront, return val
// 2. Else return -1

class LRUCache {
public:
    class Node{
    public:
        int key;
        int val;
        Node* prev;
        Node* next;
        Node(int key, int value){
            this->key = key;
            this->val = value;
            this->prev = nullptr;
            this->next = nullptr;
        }
    };
    
    Node* head = nullptr;
    Node* tail = nullptr;
    map<int, Node*> nodes;
    int capacity;
    
    LRUCache(int capacity) {
        this->capacity = capacity;
        head = new Node(0,0);
        tail = new Node(0,0);
        head->next = tail;
        tail->prev = head;
    }
    
    void moveToFront(Node* node){
        node->next = head->next;
        node->prev = head;
        head->next=node;
        node->next->prev = node;
    }
    
    void removeNode(Node* node){
        node->prev->next = node->next;
        node->next->prev = node->prev;
        node->next = nullptr;
        node->prev = nullptr;
    }
    
    int get(int key) {
        // key not present
        if(nodes.find(key) == nodes.end())
            return -1;
        
        // key present
        Node* node = nodes[key];
        removeNode(node);
        moveToFront(node);
        return node->val;
    }
    
    void put(int key, int value) {
        // if 0 capacity then can't add anything
        if(capacity == 0)
            return;
        
        // already present in cache
        if(nodes.find(key) != nodes.end()){
            Node* node = nodes[key];
            node->val = value;
            removeNode(node);
            moveToFront(node);
        }
        // new entry
        else{
            // if cache is full, remove LRU from LL and map
            if(nodes.size() == capacity){
                Node* lru = tail->prev;
                nodes.erase(lru->key);
                removeNode(lru);
            }
            Node* node = new Node(key, value);
            moveToFront(node);
            nodes[key] = node; 
        }
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */