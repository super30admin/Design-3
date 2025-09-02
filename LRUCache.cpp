#include<iostream>
#include<bits/stdc++.h>
#include<vector>
using namespace std;
/*
We store each key’s node in a map so we can find it instantly.
We use a doubly linked list to track usage order, moving items to the front when used.
When we're full, we throw out the least-used item from the back of the list.
*/
class LRUCache {
    struct Node{
        int key, value;
        Node* prev;
        Node* next;
        Node(int k, int v) : key(k), value(v), prev(nullptr), next(nullptr) {}
    };

    unordered_map<int, Node*> map;
    int capacity;
    Node* head;
    Node* tail;

    void removeNode(Node* node){
        node->prev->next = node->next;
        node->next->prev = node->prev;
        node->prev = nullptr;
        node->next = nullptr;
    }
    void addToHead(Node* node){
        node->prev = head;
        node->next = head->next;
        node->next->prev = node;
        head->next = node;
    }
public:
    LRUCache(int capacity) {
        this->capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head->next = tail;
        tail->prev = head;
    }
    
    int get(int key) {
        if(map.find(key) == map.end()) return -1;
        Node* node = map[key];
        removeNode(node);
        addToHead(node);
        return node->value;
    }
    
    void put(int key, int value) {
        if(map.find(key) != map.end()){
            Node* node = map[key];
            node->value = value;
            removeNode(node);
            addToHead(node);
        }else{
            if(map.size() == capacity){
                Node* lastNode = tail->prev;
                removeNode(lastNode);
                map.erase(lastNode->key);
                delete lastNode;
            }
            Node* newNode = new Node(key, value);
            addToHead(newNode);
            map[key] = newNode;
        }
    }
};
