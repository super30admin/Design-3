// Time Complexity -> O(1) for both get() and put() functions.
// Space Complexity -> O(n+n); map and doubly linked list
// Problems Faced - No!
// It runs on Leetcode!
class LRUCache {
    struct Node{
        int key;
        int val;
        Node* prev = nullptr;
        Node* next = nullptr;
        Node(){}
        Node(int k, int v){
            key = k;
            val = v;
        }
    };
    
    Node* head;
    Node* tail;
    int cap;
    unordered_map<int, Node*> map;

private:
    void removeNode(Node* node){
        node->prev->next = node->next;
        node->next->prev = node->prev;
    }
    void addToHead(Node* node){
        node->next = head->next;
        node->prev = head;
        node->prev->next = node;
        node->next->prev = node;
    }
public:
    LRUCache(int capacity) {
        head = new Node();
        tail = new Node();
        head->next = tail;
        tail->prev = head;
        cap = capacity;
    }
    
    int get(int key) {
        if(!map.count(key))
            return -1;
        Node* curr = map[key];
        removeNode(curr);
        addToHead(curr);
        return curr->val;
    }
    
    void put(int key, int value) {
        if(map.count(key)){
            Node* curr = map[key];
            curr->val = value;
            removeNode(curr);
            addToHead(curr);
        }else{
            if(map.size() == cap){
                Node* rm = tail->prev;
                removeNode(rm);
                map.erase(rm->key);
            }
            Node* curr = new Node(key, value);
            addToHead(curr);
            map[key] = curr;
        }
    }
};