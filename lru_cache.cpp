//TC: O(1) for put, get
//SC: O(capacity) since we have linked list and hashmap taking up space

class LRUCache {
public:
    
    //creating the doubly linked list node structure
    class dllNode{
        public:
        
        int key;
        int val;
        dllNode *prev;
        dllNode *next;
        
        dllNode(int k, int v){
            key = k;
            val = v;
            prev = NULL;
            next = NULL;
        }
        
    };
    
    
    //create dummy head and dummy tail. Also make function calls for the linked list.
    dllNode *head = new dllNode(-1, -1);
    dllNode *tail = new dllNode(-1, -1);        

    //creating map for the key, linked list node pairs
    unordered_map<int, dllNode*> cache;
    
    //count for the cache size check
    int cacheSize;
    //capacity of the cache
    int cap;
    
    //function to add to front of the linked list
    void addtoFront(dllNode *node){
        
        dllNode *temp = head->next;
        node->next = temp;
        node->prev = head;
        head->next = node;
        temp->prev = node;

    }
    
    //function to remove from the linked list
    void remove(dllNode *node){
        dllNode *delprev = node->prev;
        dllNode *delnext = node->next;
        
        delprev->next = delnext;
        delnext->prev = delprev;
    }
    
    //function to pop the tail element
    void poptail(){
        remove(tail->prev);
    }
    
    //function to move to the head (involves removing then adding to front)
    void movetoHead(dllNode *node){
        remove(node);
        addtoFront(node);
    }
    
        
    LRUCache(int capacity) {
        
        head->next = tail;
        tail->prev = head;
        cacheSize = 0;
        cap = capacity;
        
    }
    
    int get(int key) {
        
        //if we find the node, then move this to the head and update the pointer in the cache hashmap
        if(cache.find(key)!=cache.end()){
            dllNode *foundNode = cache[key];
            movetoHead(foundNode);
            cache[key] = head->next;
            return foundNode->val;            

        }
        
        //if not found then return -1
        return -1;        
    }
    
    void put(int key, int value) {
        //if we have not found the value in the cache
        if(cache.find(key)==cache.end()){
            
            dllNode *newNode = new dllNode(key, value);

            if(cacheSize == cap){
                //perform LRU here. LRU element is the last element in the cache
                //delete the last linked list element from the cache
                int delkey = tail->prev->key;
                //remove the last element from the tail
                poptail();
                cache.erase(delkey);
                
                //add the new node to the front and update the new key in the cache
                addtoFront(newNode);
                cache[key] = head->next;
                
            } else{
                //if the cacheSize is not yet equal to the capacity, then add this to the cache and the linked list
                addtoFront(newNode);
                cache[key] = head->next;
                cacheSize++;
            }
        } 
        else {
            //if the value exists in the cache, then simply update it and move it to the top
            dllNode *existNode = cache[key];
            existNode->key = key;
            existNode->val = value;
            movetoHead(existNode);
            cache.erase(key);
            //update the value of the cache 
            cache[key] = head->next;
        }
        
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */