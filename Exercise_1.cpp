/* 
    Time Complexity                              :  get and put  --> O(1)
    Space Complexity                             :  get and put  --> O(capacity) --> max number of 
                                                    elements stored in the unordered_map and the 
                                                    doubly linked list.
    Did this code successfully run on Leetcode   :  Yes
    Any problem you faced while coding this      :  No
*/

#include <bits/stdc++.h> 
using namespace std;  


// https://leetcode.com/problems/lru-cache/

class Node {
 public: 
    Node *prev, *next;
    int key, val;
    Node(int k, int v) {
        this->key = k;
        this->val = v;
        this->prev = NULL;
        this->next = NULL;
    }
};

class Nlists {
 public:   
    Node *head, *tail;
    Nlists() {
        head = new Node(INT_MAX, INT_MAX);
        tail = new Node(INT_MAX, INT_MAX);
        head->next = tail;
        tail->prev = head;
    }
    
    void addNode(Node *node) {
        node->prev = head;
        node->next = head->next;
        head->next->prev = node;
        head->next = node;
    }
    
    void deleteNode(Node *node) {
        node->prev->next = node->next;
        node->next->prev = node->prev;
        node->prev = NULL;
        node->next = NULL;
        delete node;
    }
};

class LRUCache {
public:
    unordered_map<int, Node*> mp;
    int cap, cc;
    Nlists *nlists;
    LRUCache(int capacity) {
        this->cap = capacity;
        this->cc = 0;
        nlists = new Nlists();
    }
    
    int get(int key) {
        if(mp.find(key) == mp.end()) {
            return -1;
        }
        
        Node *nNode = mp[key];
        int val = nNode->val;
        Node *newNode = new Node(nNode->key, nNode->val);
        mp.erase(key);
        mp[key] = newNode;
        nlists->deleteNode(nNode);
        nlists->addNode(newNode);
        
        return val;
    }
    
    void put(int key, int value) {
        if(cap == 0) {
            return;
        }
        
        // exists before
        if(mp.find(key) != mp.end()) {
            Node *newNode = new Node(key, value);
            nlists->deleteNode(mp[key]);
            nlists->addNode(newNode);
            mp.erase(key);
            mp[key] = newNode;
        } else { // doesn't exist before
            
            if(cc == cap) {
                Node *delNode = nlists->tail->prev;
                mp.erase(delNode->key);
                nlists->deleteNode(delNode);
            } else {
                cc++;
            }
            
            Node *newNode = new Node(key, value);
            mp[key] = newNode;
            nlists->addNode(newNode);
        }
            
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */