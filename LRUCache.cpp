#include <iostream>
#include <unordered_map>
#include <algorithm>

using namespace std;


class LRUCache{        

    public:
        struct Node 
        {
            int key, val;
            Node *next, *prev;
            Node(int a, int b): key(a), val(b), next(NULL), prev(NULL){}
    
        };
    
        unordered_map<int, Node*> cache;
        Node *head, *tail;
        int cap;
    
        LRUCache(int capacity): cap(capacity), head(new Node(-1,1)){
            tail = head;
        }

        void moveToTail(Node* node)
        {
            if(node == tail) return;
            node->prev->next = node->next;
            node->next->prev = node->prev;
            tail->next = node;
            node->prev = tail;
            node->next = NULL;
            tail = node;
        }

        int get(int key)
        {
            if(cache.find(key) == cache.end()) return -1;
            moveToTail(cache[key]);
            return cache[key]->val;
        }

        void put(int key, int value)
        {
            if(cache.find(key) == cache.end())
            {
                cache[key] = new Node(key, value);
                cache[key]->prev = tail;
                tail->next = cache[key];
                tail = cache[key];
            }

            else 
            {
                cache[key]->val = value;
                moveToTail(cache[key]);
            }

            if(cache.size() > cap)
            {
                Node *temp = head->next;
                head->next = temp->next;
                temp->next->prev = head;
                cache.erase(temp->key);
                delete temp;
            }
        }
};