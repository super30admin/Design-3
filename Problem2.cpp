// Problem: Design LRU
//  Time Complexity : O(1) for all operations
//  Space Complexity : O(n) for hashmap
// Did this code successfully run on Leetcode : Yes
//  Any problem you faced while coding this : No

// Your code here along with comments explaining your approach in three
// sentences only
// 1. We use a doubly linked list here and a hashmap to get the node references
// 2. For put, we first check if we have the key, we update the value, make it
// most recently used, if we hvae reached the capacity we remove the tail, and
// then add the node to head
// 3. For get, we first check if we have the node, if yes if remove the node and
// add to head, return the value
#include <bits/stdc++.h>
using namespace std;

class Node {
public:
  Node *next;
  Node *prev;
  int key;
  int value;
  Node(int key, int value) {
    this->key = key;
    this->value = value;
  }
};

class Dll {
public:
  Node *head;
  Node *tail;
  Dll() {
    this->head = new Node(-1, -1);
    this->tail = new Node(-1, -1);
    this->head->next = tail;
    this->tail->prev = head;
  }

  void addToHead(Node *node) {
    node->next = head->next;
    node->prev = head;
    head->next = node;
    node->next->prev = node;
  }

  void removeNode(Node *node) {
    node->prev->next = node->next;
    node->next->prev = node->prev;
    // delete node;
  }

  Node *removeTail() {
    if (tail->prev == head)
      return nullptr;
    Node *node = tail->prev;
    removeNode(node);
    return node;
  }
};

class LRUCache {
public:
  int capacity;
  unordered_map<int, Node *> um;
  Dll *dll;
  LRUCache(int capacity) {
    this->capacity = capacity;
    this->dll = new Dll();
  }

  int get(int key) {
    if (um.count(key) == 0) {
      return -1;
    }
    dll->removeNode(um[key]);
    dll->addToHead(um[key]);
    return um[key]->value;
  }

  void put(int key, int value) {
    if (capacity == 0)
      return;
    if (um.count(key) > 0) {
      um[key]->value = value;
      get(key);
      return;
    }

    if (capacity == um.size()) {
      Node *node = dll->removeTail();
      um.erase(node->key);
    }
    um[key] = new Node(key, value);
    dll->addToHead(um[key]);
  }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
