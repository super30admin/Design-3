// 146. LRU Cache
// Time Complexity : O(1)
// Space Complexity :O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : No


class LRUCache {
public:
    class ListNode{
        public:
            int key;
            int val;
            ListNode* prev;
            ListNode* next;
            ListNode(int key, int val): key(key), val(val), prev(NULL), next(NULL) {} 
    };

    int length = 0;
    unordered_map<int, ListNode*> cache;
    ListNode* head;
    ListNode* tail;
    int cap;
    LRUCache(int capacity) {
        cap = capacity;
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        head->next = tail;
        tail->prev = head;   
    }
    
    int get(int key) {
        if(cache.find(key) != cache.end())
        {
            ListNode* curr = cache[key];
            curr->prev->next = curr->next;
            curr->next->prev = curr->prev;

            ListNode* temp = tail->prev;
            temp->next = curr;
            curr->next = tail;
            curr->prev = temp;
            tail->prev = curr;

            return curr->val;
        }
        return -1;
    }
    
    void put(int key, int value) {
        ListNode* curr = NULL;
        if(cache.find(key) != cache.end())
        {
            curr = cache[key];
            curr->val = value;
            curr->prev->next = curr->next;
            curr->next->prev = curr->prev;
        }
        else
        {
            length++;
            curr = new ListNode(key, value);
            cache[key] = curr;    
        }

        ListNode* temp = tail->prev;
        temp->next = curr;
        curr->next = tail;
        curr->prev = temp;
        tail->prev = curr;

        if(length > cap)
        {
            length--;
            ListNode* nodeToDelete = head->next;
            head->next = nodeToDelete->next;
            nodeToDelete->next->prev = head;
            cache.erase(nodeToDelete->key);
            delete(nodeToDelete);
        }
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
