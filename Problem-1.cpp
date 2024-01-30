// Time Complexity : O(1)

// Space Complexity : O(N)

// Did this code successfully run on Leetcode : Yes

// Appoarch: Using doubely linked lists and hashmap for searching the nodes(get operation).

// 146. LRU Cache

#include <bits/stdc++.h>
using namespace std;

class LRUCache
{
public:
    class Node
    {
    public:
        int key;
        int val;
        Node *next;
        Node *prev;
        Node(int _key, int _val)
        {
            key = _key;
            val = _val;
        }
    };
    Node *head = new Node(-1, -1);
    Node *tail = new Node(-1, -1);
    unordered_map<int, Node *> mp;
    int size;
    LRUCache(int capacity)
    {
        size = capacity;
        head->next = tail;
        head->prev = NULL;
        tail->prev = head;
        tail->next = NULL;
    }

    void addNode(Node *newNode)
    {
        Node *temp = head->next;
        newNode->prev = head;
        newNode->next = temp;
        head->next = newNode;
        temp->prev = newNode;
    }

    // delete node from tail
    void deleteNode(Node *delNode)
    {
        Node *pr = delNode->prev;
        Node *nxt = delNode->next;
        pr->next = nxt;
        nxt->prev = pr;
    }

    int get(int _key)
    {
        if (mp.find(_key) != mp.end())
        {
            Node *temp = mp[_key];
            mp.erase(_key); // erasing from map
            deleteNode(temp);
            addNode(temp);
            mp[_key] = head->next;
            return temp->val;
        }
        return -1;
    }

    void put(int _key, int value)
    {
        if (mp.find(_key) != mp.end())
        {
            // update
            Node *temp = mp[_key];
            mp.erase(_key);
            deleteNode(temp);
        }
        else if (mp.size() == size)
        {
            // delete
            mp.erase(tail->prev->key);
            deleteNode(tail->prev);
        }
        Node *newNode = new Node(_key, value);
        addNode(newNode);
        mp[_key] = newNode;
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */