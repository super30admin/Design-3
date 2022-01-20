//Time Complexity O(n)
// Space Complexity O(n)
//Problem successfully executed on leetcode
//No problems faced while coading this.


#include<iostream>
#include<vector>
#include<queue>
#include<map>
#include<stack>
using namespace std;

class LRUCache {
public:
    class Node
    {
        public:
        Node* next;
        Node* prev;
        int key;
        int value;
        Node(int key1,int value1)
        {
            key=key1;
            value=value1;
        }
    };
    Node* head;
    Node* tail;
    map<int,Node*> keyToNodeAddress;
    int cap;
    
    void insertAfterHead(Node* node)
    {
        (head->next)->prev=node;
        node->next=head->next;
        head->next=node;
        node->prev=head;
    }
    
    void removeNode(Node* node)
    {
        (node->prev)->next=node->next;
        (node->next)->prev=node->prev;
    }
    
    
    
    LRUCache(int capacity) {
        cap=capacity;
        head=new Node(-1,-1);
        tail=new Node(-1,-1);
        head->next=tail;
        tail->prev=head;
        head->prev=NULL;
        tail->next=NULL;
    }
    
    int get(int key) {
        if(keyToNodeAddress.find(key)==keyToNodeAddress.end())
        {
            return -1;
        }
        Node* current=keyToNodeAddress[key];
        removeNode(current);
        insertAfterHead(current);
        return current->value;
    }
    
    void put(int key, int value) {
        Node* current;
        if(keyToNodeAddress.find(key)!=keyToNodeAddress.end())
        {
            current=keyToNodeAddress[key];
            removeNode(current);
            insertAfterHead(current);
            current->value=value;
        }
        else
        {
            current=new Node(key,value);
            if(keyToNodeAddress.size()==cap)
            {
                keyToNodeAddress.erase((tail->prev)->key);
                removeNode(tail->prev);
            }
            insertAfterHead(current);
            keyToNodeAddress[current->key]=current;
        }
        
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */