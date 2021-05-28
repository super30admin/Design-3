/*
Intuition: In this program, start with an array. An array wont allow us to reorder elements in O(1) time

We can reorder elements by moving the removed node to the head. This can be done via a doubly linked list.

Accessing of elements can be done in O(1) using a hashmap.
We could store key, Node* as {key, value} pair.

#####################################################################
removeNode Method: Only rearranging the pointers 
Time Complexity : O(1)
Space Complexity : O(1)
#####################################################################
moveToHead Method: Only rearranging the pointers 
Time Complexity : O(1)
Space Complexity : O(1)
#####################################################################
Get Method: Accessing the value at a particular key.
Time Complexity : O(1)
Space Complexity : O(1)
#####################################################################
Put Method: Adding a key to a hashmap and rearranging the pointer of the other nodes.
Time Complexity : O(1)
Space Complexity : O(1)
#####################################################################

*/
class Node{
    public:
        Node* prev;
        Node* next;
        int key, val;
        Node(int key, int val){
            this->key = key;
            this->val = val;
        }
};
class LRUCache {
    
public:
    int capacity;
    map <int, Node* > hashMap;
    Node* head;
    Node* tail;
    LRUCache(int capacity) {
        this->capacity = capacity;
        head = new Node(-1,-1); 
        tail = new Node(-2,-2);
        head->next = tail;
        tail->prev = head;
    }
    
    void removeNode(Node* currentNode){
        currentNode->prev->next = currentNode->next;
        currentNode->next->prev = currentNode->prev;
    }

    void moveToHead(Node* currentNode){
        currentNode->prev = head;
        currentNode->next = head->next;
        head->next->prev = currentNode;
        head->next = currentNode;
    }
    
    int get(int key) {
        if ( hashMap.find(key) == hashMap.end())   {
            return -1;
        }
        Node* currentNode = hashMap[key];
        removeNode(currentNode);
        moveToHead(currentNode);
        return currentNode->val;
        
    }
    
    void put(int key, int value) {
        
        if ( hashMap.find(key) == hashMap.end()){
            if ( this->capacity == hashMap.size()){
                Node* prevToTail = tail->prev;
                removeNode(prevToTail);
                hashMap.erase(prevToTail->key);
            }
            Node * newNode = new Node(key, value);
            hashMap.insert({key, newNode});
            moveToHead(newNode);
        }
        else{
            Node * currentNode = hashMap[key];
            currentNode->val = value;
            removeNode(currentNode);
            moveToHead(currentNode);
        
        }
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */