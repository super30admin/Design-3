// Time Complexity : O(1)
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach

// as we can see int the question, we are required to use a key and value pair , so we should use a map.
// we need maintain a data structure to arrange the keys  in the order of their usage. 
// . we cant use a queue because while it requires us to travers till we find the key incase of update activity.
// . we cant use a linkedlist because to delete a node , we need to traverse from head to identify a prev node so that we can delete .so this 
//   linkedlist also requires us to traverse through the nodes. but we need to do in O(1)
// . we can use a double linked list so, the above two problems can be overcomed and also we can perform our task in O(1) time.
//   we can implement an lru cache in O(1) time by using map with key as int , value as Node pointer and a double linked list with 
//   key(int) and value (int) .


class LRUCache {
public:
    class Node{
        public:
        int key;
        int val;
        Node* prev;
        Node* next;
        Node(int k,int v){
            key = k;
            val = v;
            prev = NULL;
            next = NULL;
        }
    };
    Node* head,*tail;
    unordered_map<int,Node*>map;
    int cap;
    LRUCache(int capacity) {
        cap = capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head->next = tail;
        tail->prev = head;
    }
    void removeNode(Node* curr)
    {
        map.erase(curr->key);
        curr->next->prev = curr->prev;
        curr->prev->next = curr->next;
    }
    void addToHead(Node*curr)
    {
        map[curr->key] = curr;
        curr->next = head->next;
        curr->prev = head;
        head->next = curr;
        curr->next->prev = curr;
    }
    
    int get(int key) {
        if(map.find(key)==map.end()) return -1;
        int ans = map[key]->val;
        Node* nn = map[key];
        removeNode(nn);
        addToHead(nn);
        return ans;
    }
    
    void put(int key, int value) {
        if(map.find(key)!=map.end()){
            Node* nn = map[key];
            removeNode(nn);
            addToHead(nn);
            nn->val = value;
        }
        else{
            if(map.size()==cap)
            {
                Node* d = tail->prev;
                removeNode(d);
                delete d;
            }
            addToHead(new Node(key,value));
        }
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */