
class LRUCache {
    
class Node{
    public:
    int key; int val;
    Node* next; Node* prev;
    Node(int key, int val){
        this->key = key;
        this->val = val;
    }
};
public:
    void removeNode(Node* node){
        node->prev->next = node->next;
        node->next->prev = node->prev;
    }
    void addToHead(Node* node){
        node->next = head->next;
        node->prev = head;
        head->next = node;
        node->next->prev = node;
    }
    unordered_map<int,Node*>map;
    Node* head; Node* tail;
    int capacity;
    LRUCache(int capacity) {
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head->next = tail;
        tail->prev = head;
        this->capacity = capacity;
    }
    
    int get(int key) {
        if(map.count(key)>0){
            Node* node = map[key];
            removeNode(node);
            addToHead(node);
            return node->val;
        }
        return -1;
        
    }
    
    void put(int key, int value) {
         if(map.count(key)>0){
            Node* node = map[key];
            node->val = value;
            removeNode(node);
            addToHead(node);
        }
        else{
            Node* newNode = new Node(key,value);
            if(map.size()==capacity){
                Node* tailPrev = tail->prev;
                removeNode(tailPrev);
                map.erase(tailPrev->key);
            }
            addToHead(newNode);
            map.emplace(key,newNode);
        }
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */