// Time Complexity : O(1)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes

#include <iostream>
#include <unordered_map>

class LRUCache {
private:
    struct Node {
        int key;
        int value;
        Node* prev;
        Node* next;

        Node(int k, int v) : key(k), value(v), prev(nullptr), next(nullptr) {}
        Node() : Node(0, 0) {}
    };

    Node* head;
    Node* tail;
    std::unordered_map<int, Node*> cache;
    int capacity;
    int count;

public:
    LRUCache(int capacity) {
        this->capacity = capacity;
        this->count = 0;

        head = new Node();
        tail = new Node();

        head->next = tail;
        tail->prev = head;
    }

    int get(int key) {
        if (cache.find(key) == cache.end()) {
            return -1;
        } else {
            Node* n = cache[key];
            update(n); // Update the cache to mark this node as most recently used
            return n->value;
        }
    }

    void put(int key, int value) {
        if (cache.find(key) == cache.end()) {
            Node* newNode = new Node(key, value);
            cache[key] = newNode;
            add(newNode); // Add the new node to the cache
            ++count;
        } else {
            Node* n = cache[key];
            n->value = value;
            update(n); // Update the cache to mark this node as most recently used
        }

        if (count > capacity) {
            Node* del = tail->prev;
            remove(del); // Remove the least recently used element from the cache
            cache.erase(del->key);
            delete del;
            --count;
        }
    }

    void update(Node* node) {
        remove(node);
        add(node);
    }

    void add(Node* node) {
        Node* after = head->next;
        head->next = node;
        node->prev = head;
        node->next = after;
        after->prev = node;
    }

    void remove(Node* node) {
        Node* before = node->prev;
        Node* after = node->next;
        before->next = after;
        after->prev = before;
    }

    ~LRUCache() {
        while (head != nullptr) {
            Node* del = head;
            head = head->next;
            delete del;
        }
    }
};