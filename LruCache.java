package Nov23;

import java.util.Map;

class LruCache {

    /*
    Time Complexity: O(1) because inserting entries and accessing elements in hashmap is O(1) when key is integer. Also, since we are accessing linked list node references in the hashmap, updating or accessing linkedlist is also O(1) operation.
    
    Space Complexity: O(2N) because we are using a linkedlist of size N and hashmap of size N where N is capacity.
    
     Did this code successfully run on Leetcode : Yes
    
     Any problem you faced while coding this : No
    
     Approach: Used a hashmap and doubly linked list.
     Hashmap holds the key for the entry to 'lrucache' DS and value as the corresponding nodes in the doubly linked list.
     Doubly linked list holds nodes (with key and value attributes) corresponding to every entry in 'lrucache' DS. It is used to maintain order of access/updation of entries in the 'lrucache' DS.
    
    */
    
    // node of the doubly linked list
    class Node {
        int key;
        int val;
        Node next;
        Node prev;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    Map<Integer, Node> hm;
    Node head ;
    Node tail ;
    int maxCap;
    
    // constructor for LRUcache class where we initialise the hashmap and doubly linked list's head and tail node.
    // Note that we keep a dummy node at head and tail to ease the coding effort.
    public LruCache(int capacity) {
        maxCap = capacity;
        hm = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }

    // function to find a record in lrucache with key = key(in fn.param)
    public int get(int key) {
        // if there is a hashmap entry with the key valye as 'key' function parameter:
        //      find value of the entry. It will be a node.
        //      find and return the NodeValue of this node.
        //      since this key,value for lrucache has been accessed now, it is most recently used. So, push this node to head of the linked list where we hold the most recently used entry.
        if (hm.containsKey(key)) {
            Node foundVal = hm.get(key);
            removeFromCurrentPos(foundVal);
            addToHead(foundVal);
            return foundVal.val;
        }
        // if there is NO hashmap entry with the key valye as 'key' function parameter: return -1/
        return -1;
    }
    
     // function to put a record in lrucache with key = key(in fn.param) and value = value(in fn.param) 
    public void put(int key, int value) {
        // if map already contains an entry with the key value as key(in fn.param), just update the entry value to that passed in this function in 'value' param.
       // since this key,value for lrucache has been accessed now, it is most recently used. So, push this node to head of the linked list where we hold the most recently used entry.
        if (hm.containsKey(key)) {
            Node foundVal = hm.get(key);
            foundVal.val = value;
            removeFromCurrentPos(foundVal);
            addToHead(foundVal);
        } else {
            // if map does NOT contain an entry with the key value as key(in fn.param):
            //          first check for capacity. If capacity is already exhausted, delete the oldest accessed node which will be at the tail of the linked list. Also, delete the tail node's entry from the hashmap.
            //          add the new node at the head of the linked list.
            //          add a new entry in the hashmap for the newly added node.
            // since this key,value for lrucache has been accessed now, it is most recently used. So, push this node to head of the linked list where we hold the most recently used entry.
            Node newNode = new Node(key,value);
            if (hm.size() == maxCap) {
                Node tailPrev = tail.prev;
                removeFromCurrentPos(tailPrev);
                hm.remove(tailPrev.key);
            }
            addToHead(newNode);
            hm.put(key, newNode);
        }
    }
    
    // helper function to add a node just after the dummy head node for the list.
    public void addToHead(Node node) {
        node.next = head.next;
        node.next.prev = node;
        node.prev = head;
        head.next = node;
    }
    
    // helper function to delete a node just before the tail node for the list.
    public void removeFromCurrentPos(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */