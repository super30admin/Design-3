// Time Complexity : 
//      put: O(1)
//      get: O(1)
// Space Complexity : O(n+(n+2)) = O(n+2) = O(n)
//      n: number of elements
//    for hashMap as well as Doubly-linked list
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no

import java.util.HashMap;

// Your code here along with comments explaining your approach
// 1. We will create a doubly linked list.
// 2. Also create two dummy nodes Head and tail.
// 3. We will also have HashMap to check if any node is present.
// 4. Head side is Most recently used Node and tail side we have least recently used.
// 5. For any put and get operation we will add node to head and also update the map.
class LRUCache {
    
    /** definition of Node */
    class Node{

        // member variables
        int key, value;
        Node prev, next;
        
        // constructor
        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    
    // LRU Members
    Node dummyHead;
    Node dummyTail;
    HashMap<Integer, Node> map;
    int maxCapacity;
    
    // constrcutor
    public LRUCache(int capacity) {

        // initialize

        // Doubly Linked List
        dummyHead = new Node(-1, -1);
        dummyTail = new Node(-1, -1);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;

        maxCapacity = capacity;
        map = new HashMap<Integer, Node>(capacity);
    }
    
    /** add to head */
    private void addToHead(Node newNode){

        // first link new-node to head
        newNode.prev = dummyHead;
        
        // second link new-node to head's next;
        newNode.next = dummyHead.next;

        // update head's next
        dummyHead.next = newNode;
        // now update new-node next `s previous
        newNode.next.prev = newNode;
    }

    /** remove a node */
    private void removeNode(Node current){

        // unlink current's previous
        current.prev.next = current.next;
        // unlink current 's next
        current.next.prev = current.prev;
    }

    /** get value based on key */
    public int get(int key) {
        
        // get Node if not present then return null
        Node current = map.get(key);
        // not null
        if(current != null){
            // remove current
            removeNode(current);
            // add to head
            addToHead(current);

            return current.value;     
        }
        else
            return -1;
    }
    
    /** add to cache */
    public void put(int key, int value) {

        // get Node if not present then returns null
        Node current = map.get(key);

        // not null
        if(current != null){

            // remove the node
            removeNode(current);

            // change the value
            current.value = value;

            // add to head
            addToHead(current);  
        // not present   
        }else{

            // capacity is reaches
            if(map.size() == maxCapacity){

                // remove from tail
                map.remove(dummyTail.prev.key);
                removeNode(dummyTail.prev);
            }
            // create new-node add to head and map
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToHead(newNode);
        }
    }
}
