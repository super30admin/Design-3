class LRUCache {
    class Node{
        public: int key;
        public: int val;
        public: Node* prev;
        public: Node* next;
    };
    
    public: void add(Node* node)
    {
        node->prev=head;
        node->next=head->next;
        head->next->prev=node;
        head->next=node;
    }
    
    public: void remove(Node* node)
    {
        Node* prev=node->prev;
        Node* next=node->next;
        prev->next=next;
        next->prev=prev;
    }
    
    public: void movetohead(Node* node)
    {
        remove(node);
        add(node);
    }
    
    public: Node* poptail()
    {
        Node* res=tail->prev;
        remove(res);
        return res;
    }
    
    
public:
    
    map<int,Node*>map;
    int size;
    int capacity;
    Node* head;
    Node* tail;
    
    LRUCache(int capacity) {
        this->size=0;
        this->capacity=capacity;
        
        head = new Node();
        tail = new Node();
        head->next=tail;
        tail->prev=head;
    }
    
    int get(int key) {
        Node* node=map[key];
        if(node==NULL)
            return -1;
        movetohead(node);
        return node->val;
    }
    
    void put(int key, int value) {
        Node* node=map[key];
        if(node==NULL)
        {
            Node* newnode=new Node();
            newnode->key=key;
            newnode->val=value;
            map[key]=newnode;
            add(newnode);
            ++size;
            if(size>capacity)
            {
                Node* tail=poptail();
                map.erase(tail->key);
                --size;
            }
        }
        else
        {
            node->val=value;
            movetohead(node);
        }
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */