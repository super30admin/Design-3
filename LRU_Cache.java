
/*
Author: Akhilesh Borgaonkar
Problem: Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
Approach: Implemented Least Recently Used Cache using HashMap for cache and Doubly LinkedList for organizing it. The basic methods implemented
    are get() and put(). get() method basically gets the node form cache, puts it to the next of head node and returns the node.
    put() method basically checks if the node exists and if it does then updates the value of existing node and puts it next to the head.
    If the node doesn't exist then, creates a new node, sets the key and value & puts in the cache. If the cache size exceeds the capacity then
    the tail node is removed to maintain the cache capacity.
Time Complexity: O(1) for both put() and get() methods
Space Complexity: O(1) for both put() and get() methods
Issue: Will be trying to optimize it by dropping the use of 'key' as it the Node already has key attribute in it. Will try to use that instead.
*/


class LRUCache {
    
    class DLNode{
        int key;
        int value;
        DLNode prev;
        DLNode next;
    }
    
    private void addNode(DLNode node){  //method to add new node to the hashmap
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    
    private void removeNode(DLNode node){   //method to remove the node from the hashmap
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void moveToHead(DLNode node){   //method to remove the node from hashmap and add it next to the head
        removeNode(node);
        addNode(node);
    }

    private DLNode popTail(){   //method to remove the node from the tail
        DLNode tailNode = tail.prev;
        removeNode(tailNode);
        return tailNode;
    }
    
    private HashMap<Integer, DLNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLNode head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;

        head = new DLNode();
        tail = new DLNode();
        head.next = tail;
        tail.prev = head;   // initiating a dummy doubly linked list
    }
    
    public int get(int key) {
        DLNode node = cache.get(key);
        if(node==null)return -1;
        moveToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        DLNode node = cache.get(key);

        if(node == null){
            DLNode newNode = new DLNode();
            newNode.key = key;
            newNode.value = value;
            cache.put(key, newNode);
            addNode(newNode);
            size++;

            if(size>capacity){
                DLNode poppedNode = popTail();
                cache.remove(poppedNode.key, poppedNode);
                size--;
            }
        }
        if(node != null){
            node.value = value;
            moveToHead(node);
        }
    }
}