// Time Complexity : O(1)
// Space Complexity : O(n)
//    where n : capacity.
// Did this code successfully run on Leetcode : yes

// Three line explanation of solution in plain english
/* LRU cache is implemented using hashmap and doubly linked list. 
 * To perform fast lookup hashmap is used. To achieve fast insertion doubly linked list is used.  
 */

class LRUCache {
    
    class Node{
        public:
            int key, value;
            Node *next, *prev;
        
            Node(int key, int value) : key(key), value(value)
            {
                next = nullptr;
                prev = nullptr;
            }
    };
    
public:
    int capacity;
    map<int, Node*> map;
    Node *head, *tail;
    
    void insertToHead(Node* node){
        node->next = head->next;
        node->prev = head;
        head->next->prev = node;
        head->next = node;
    }
    
    void remove(Node* node)
    {
        node->prev->next = node->next;
        node->next->prev = node->prev;
    }
    
    LRUCache(int capacity) {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        
        head->next = tail;
        tail->prev = head;
        
        this->capacity = capacity;
    }
    
    int get(int key) {
        
        if (map.find(key) == map.end())
            return -1;
        
        Node* node = map.find(key)->second;
        remove(node);
        insertToHead(node);
        
        return node->value;
    }
    
    void put(int key, int value) {
        if (map.find(key) != map.end())
        {
            Node* node = map.find(key)->second;
            node->value = value;
            remove(node);
            insertToHead(node);
        }
        else
        {
            if (capacity == map.size())
            {
                Node* node = tail->prev;
                remove(node);
                map.erase(node->key);
                delete node;
            }
            
            Node* node = new Node(key, value);
            insertToHead(node);
            map.insert({key, node});
        }
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */