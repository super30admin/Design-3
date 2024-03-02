/*
Time: O(1) for each operation
Space: O(capacity)

Maintain doubly linkedlist for o(1) removal
Maintain hashmap for o(1) search and update
*/

class LinkedList{
    public:

    struct node{
        int val = -1;
        int key = -1;
        node* next = nullptr; 
        node* prev = nullptr; 
    };

    
    node* head;
    node* tail;

    LinkedList(){
        head = new node();
        tail = new node();

        head->next = tail;
        tail->prev = head;
    }

    node* insert(int key, int val){
        node* nd = new node();
        nd->val = val;
        nd->key = key;

        tail->prev->next = nd;
        
        nd->prev = tail->prev;
        nd->next = tail;

        tail->prev = nd;

        return nd;  
    }

    void remove(node* nd){
        nd->prev->next = nd->next;
        nd->next->prev = nd->prev;

        //delete(nd);
    }

    int pop(){
        node* nd = head->next;
        int ret = nd->key;

        remove(nd);
        return ret;
    }   
};


class LRUCache {
public:
    LinkedList ll;

    int cap;
    unordered_map<int,LinkedList::node*> m;
    
    LRUCache(int capacity) {
        cap = capacity;
    }
    
    int get(int key) {
        if(m.count(key)==0)  return -1;

        //get means the item is used most recently
        int val = m[key]->val;
        ll.remove(m[key]);
        m[key] = ll.insert(key, val);
        return val;
    }
    
    void put(int key, int value) {
        if(m.count(key)){
            ll.remove(m[key]);
            m[key] = ll.insert(key, value);
        }
        else if(m.size() < cap){
            m[key] = ll.insert(key, value);
        }
        else{
            int k = ll.pop();
            m.erase(k);

            m[key] = ll.insert(key, value);
        }

    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
