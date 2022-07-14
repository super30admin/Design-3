//Time Complexity- O(1)
//Space Complexity- O(capacity)

class node{
    public:
        int key;
        int val;
        node* prev;
        node* next;
        node(int _key,int _val){
            key=_key;
            val=_val;
        }
};

void addNode(node*& head,node*& k){
    
    k->next=head->next;
    k->next->prev=k;
    k->prev=head;
    head->next=k;
}

void removeNode(node*& k){
    
    k->prev->next=k->next;
    k->next->prev=k->prev;
}

class LRUCache {
public:
    int cap;
    unordered_map<int,node*> mp;
    node* head;
    node* tail;
    LRUCache(int capacity) {
        
        cap=capacity;
        head=new node(-1,-1);
        tail=new node(-1,-1);
        head->next=tail;
        tail->prev=head;
    }
    
    int get(int key) {
        
        if(mp.find(key)==mp.end()){
            return -1;
        }
        int val=mp[key]->val;
        removeNode(mp[key]);
        addNode(head,mp[key]);
        return val;
    }
    
    void put(int key, int value) {
        
        if(mp.find(key)!=mp.end()){
            removeNode(mp[key]);
            mp[key]->val=value;
            addNode(head,mp[key]);
        }
        else{
            if(cap==mp.size()){
                mp.erase(tail->prev->key);
                removeNode(tail->prev);
            }
            node* newnode=new node(key,value);
            addNode(head,newnode);
            mp[key]=newnode;
        }
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */