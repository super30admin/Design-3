/*
get() function
Time Complexity = O(1)
Space Complexity = O()

put() function
Time Complexity = O(1)
Space Complexity = O()
*/
class LRUCache {
public:
    class Node{
        public:
        int key, value;
        Node *next;
        Node *prev;
        Node(int key, int value){
            this->key = key;
            this->value = value;
        }
    };
    
    Node *head;
    Node *tail;
    
    void addtohead(Node *node)
    {
        node->next = head->next;
        node->prev = head;
        node->next->prev = node;
        head->next = node;
    }
    
    void removenode(Node *node)
    {
        node->next->prev = node->prev;
        node->prev->next = node->next;
    }
    
    int cap;
    map<int, Node*> m;
    LRUCache(int capacity) {
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head->prev = NULL;
        head->next = tail;
        tail->prev = head;
        tail->next = NULL;
        cap = capacity;
    }
    
    int get(int key) {
        if(m.find(key)!=m.end()){
            Node *node = m[key];
            removenode(node);
            addtohead(node);
            return node->value;
        }
        return -1;
    }
    
    void put(int key, int value) {
        if(m.find(key)!=m.end())
        {
            Node *node = m[key];
            node->value = value;
            removenode(node);
            addtohead(node);
        }
        else{
            if(cap==m.size()){
                Node *tailprev = tail->prev;
                removenode(tailprev);
                m.erase(tailprev->key);
            }
            Node *newnode = new Node(key,value);
            addtohead(newnode);
            m[key] = newnode;
        }
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
