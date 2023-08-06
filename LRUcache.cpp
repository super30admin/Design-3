//time: O(1) amortized
//space: O(n)
class Node{
    
    public:
    int key;
    int val;
    Node* prev;
    Node* next;
        Node(int key, int val){
            this->next = NULL;
            this->prev = NULL;
            this->key = key;
            this->val = val;
        }
};
class LRUCache {
    unordered_map<int,Node*> map;
    Node* head;
    Node* tail;
    int capacity;
    
public:

    void addToHead(Node* n){
            n->prev = head;
    n->next = head->next;
    head->next->prev = n;
    head->next = n;

    }

    void removeNode(Node* n){
        n->prev->next = n->next;
        n->next->prev = n->prev;
        
    }
    LRUCache(int capacity) {
        this->capacity = capacity;
        this->head = new Node(-1,-1);
        this->tail = new Node(-1,-1);
        head->next = tail;
        tail->prev = head;
        
    }
    
    int get(int key) {
        if(map.find(key)==map.end()){
            return -1;

        }
        
            Node* node = map[key];
            removeNode(node);
            addToHead(node);
            
        

        int val = node->val;
        return val;
        
        
    }
    
    void put(int key, int value) {
        //case 1: non fresh node;
        if(map.find(key)!=map.end()){
            Node* node = map[key];
            node->val = value;
            removeNode(node);
            addToHead(node);
        }
        //fresh Node
        else{
            if(capacity != map.size()){
                Node* node = new Node(key,value);
                addToHead(node);
                map[key]=node;

            }
            else{
                 Node* node = new Node(key,value);
                 Node* prevTail = tail->prev;
                 removeNode(prevTail);
                 map.erase(prevTail->key);
                 
                 
                 addToHead(node);
                 map[key]=node;

            }
        }
        
        
    }
};
