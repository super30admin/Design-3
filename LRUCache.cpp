// Time Complexity : O(1) for both put and get  
// Space Complexity : O(n) // map + LL
// Did this code succesfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// 1. Maintain a map of input key to pointer to node and a doubly LL with head and tail initialized to empty nodes
// 2. New nodes added or nodes updated move to front of LL and LRU node is pointed by tail
// 3. Map helps giving reference to a node to make moving to front of LL and updating tail an O(1) operation

// put operation:
// 1. If already present: update value of existing node; adjust tail if that's tail and move to front
// 2. If new key and map is full, delete node at tail and update tail
// 3. Whether map full or not, create new node and add to front of list and to map

//get operation:
// 1. If present in the map: adjust tail if that's tail and move to front and return value
// 2. Else return -1

class LRUCache {
public:
    class Node{
    public:
        int key;
        int val;
        Node* prev;
        Node* next;
        // had to use this for nested class constructor
        Node(int key, int val){
             this->key = key;
             this->val = val;
             this->prev = nullptr;
             this->next = nullptr;
         }
        Node(){
            this->key=0; this->val=0;
            this->prev = nullptr;
            this->next = nullptr;
        }
    };
    
    Node* head;
    Node* tail;
    map<int, Node*> nodes;
    int cap;
        
    LRUCache(int capacity) {
        cap = capacity;
        head = new Node();
        tail = new Node();
    }
    
    void moveToFront(Node* node){
        // neighbors
        if(node->next)
            node->next->prev = node->prev;
        if(node->prev)
            node->prev->next = node->next;
        // node next and prev
        node->prev = head;
        node->next = head->next;
        // head
        if(head->next)
            head->next->prev = node;
        head->next = node;
    }
        
    int get(int key) {
        cout<<"[*]  getting "<<key<<endl;
        // key is present in map; remove node and insert at front of list; return val
        if(nodes.find(key) != nodes.end()){
            if(tail->prev == nodes[key] && nodes.size()!=1)
               tail->prev = nodes[key]->prev;
            moveToFront(nodes[key]);
            if(head->next)
             cout<<"get op complete, now first el is "<<head->next->key<<endl;
            cout<<"check tail: "<<tail->prev->key<<endl;
            return nodes[key]->val;
       } 
        // key is not present; return -1;
        else
            return -1;
        
        // never reaches this
        return -1;
    }
    
    void put(int key, int value) {
        cout<<"[*] putting "<<key<<" "<<value<<endl;
        cout<<"size of map is "<<nodes.size()<<endl;
        // cout<<"head key "<<head->key<<endl;
        if(head->next)
             cout<<"first element is "<<head->next->key<<endl;
         
        // key is present in map; update value and move node to front
       if(nodes.find(key) != nodes.end()){
           nodes[key]->val = value;
           if(tail->prev == nodes[key] && nodes.size()!=1)
               tail->prev = nodes[key]->prev;
           moveToFront(nodes[key]);
       } 
       // key is not present; 
        else{
            // map is full; remove element at tail from list and map; add new el to list and map
            if(nodes.size() >= cap){
                cout<<"ALERT MAP FULL\n";
            // delete last element from list and map
                cout<<"tail val: "<<tail->prev->key<<endl;
                nodes.erase(tail->prev->key);
                tail->prev = tail->prev->prev;
                // delete tail->prev->next;
                tail->prev->next =nullptr;
            }
           
            cout<<"creating new node\n";
            // do this irresp of capacity
            // add new node to map and front of list
            Node* temp = new Node(key, value);
            // cout<<"new node key "<<temp->key<<" val "<<temp->val<<endl;
            // updating tail to the first added node
            if(nodes.size() == 0)
                tail->prev = temp;
            cout<<"check tail: "<<tail->prev->key<<endl;
            nodes[key] = temp;
            cout<<"now size of map is "<<nodes.size()<<endl;
            moveToFront(temp);            
            if(head->next)
             cout<<"put operation complete, now first el is "<<head->next->key<<endl;
        }
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */