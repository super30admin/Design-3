// Time Complexity : O(1) 
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode : Yes 

//doubly linked list to keep a track of the most frequently used node and the last node 
class Node {
    public:
            int key;
            Node* prev;
            Node* next;
            int value;

            //initialising the nodes
            Node(int key, int value){
                this->key = key;
                this->value = value;
                this->next = NULL;
                this->prev = NULL;
            }
        };

class LRUCache {
public:
    //To get the key-value in O(1)
    unordered_map<int, Node*> hashmap;
    
    Node* head;
    Node* tail;
    int capacity;

    // Initialising the basic doubly-LL
    LRUCache(int capacity) {
        this->capacity = capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head->next = tail;
        tail->prev = head;
        // hashmap = unordered_map<int, Node*> ();
    }

    // Removing the node from the doubly-LL
    void removeFromLL(Node* node){
        node->next->prev = node->prev;
        node->prev->next = node->next;
    }

    //Adding the node to the front of the LL
    //All recently used node is thus at the front of the LL
    void addToFront(Node* node){
        node->next = head->next;
        head->next = node;
        node->prev = head;
        node->next->prev = node; 
    }
    
    // if the key exists, put the node in thr front of LL and return the value 
    // else return -1
    int get(int key) {
        if(hashmap.find(key) != hashmap.end()){
            Node* currNode = hashmap[key];
            removeFromLL(currNode);
            addToFront(currNode);
            return currNode->value;
        }
            
        return -1;
    }
    
    void put(int key, int value) {
        // if the key already exists, update the value and put the node in the front
        if(hashmap.find(key) != hashmap.end()){
            Node* node = hashmap[key];
            node->value = value;
            removeFromLL(node);
            addToFront(node);
            return;
        }

        // If the capacity has reached, delete the last node (least recently used)
        if(hashmap.size() == capacity){
            Node* lastNode = tail->prev;
            removeFromLL(lastNode);
            hashmap.erase(lastNode->key);
        }

        //and add a new node 
        Node* newNode = new Node(key, value);
        hashmap[key] = newNode;
        addToFront(newNode);
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */