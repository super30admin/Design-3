//Problem 80 : Design LRU Cache - Using Doubly Linked List
//TC: O(1)
//SC: O(capacity)

/*
Steps 

LRU Cache : If capacity is full remove/evict the the elements which are least used. 
So for Case of Doubly Linkedlist. Keep Most used(Get request)/updated or newly added towards head of the linkedlist and if capacity is full remove from the tail, 

For Linked HashMap : Keep Most used(Get request)/updated or newly added towards end of the linkedlist and if capacity is full remove the first node, 

Can be done using Doubly Linkedlist with HashMap or with Linked HashMap. But don't directly jump to the optimized solution.
Start with any linear data structure such as  Single Linked list or Queue. Initialise the head and tail for singly linkedlist. Show the interviewer that for "get" and "put" TC will be O(N). But "get" can be converted O(1) using HashMap. If Key and value are stored in HashMap. But Still "put" will be O(N). To make put O(1), Convert singly linked list to the Doubly Linkedlist. Store Nodes as values in hashmap. So that on update/put Request, node can be moved towards head and if new node is to be added and capacity is full still node can be removed from the tail in O(1).

Steps: 1) Create Node class with next and prev;
       2) Create 3 global variables Map, Node head, Node tail
       3) Initiatlise map and then head and tail with dummy values. Then make head.next = tail and tail.prev = head
       4) Create Function "addNodeToHead"
       5) Create Function removeNode"

       At get request: call removeNode and then add that node towards head
       At put request: if element exists in themap just do similar to the get request otherwise
         a) if capacity is equal to the map size then remove the node from tail and remove that values from hash map
         b) add new node to the head and add that value to map  

*/

import java.util.*;
class LRUCacheUsingDoubly {

    //Doubly Linkedlist
    class Node{
        Node prev; Node next;
        int key,val;
        
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    
    private void addToHead(Node node){
        
        node.next = head.next;
        node.prev = head;
        
        head.next      = node;
        node.next.prev = node;
        
    }
    
    private void removeNode(Node node){
        
        node.next.prev = node.prev;
        node.prev.next = node.next;
        
    }
    
    
    Node head;
    Node tail;
    
    Map<Integer,Node> map;
    int capacity;
        
    public LRUCacheUsingDoubly(int capacity) {
        
        this.capacity = capacity;
        map = new HashMap<>();

        head = new Node(-1,-1);
        tail = new Node(-1,-1); 
        head.next = tail;    
        tail.prev = head;    
    }
    
    public int get(int key) {
        
        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            addToHead(node);
            return node.val;
        }
        
        return -1;
    }
    
    public void put(int key, int value) {
        
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }else{//key is not there
             Node newNode = new Node(key,value);
            if(map.size()==capacity){
                //Remove previous Node from tail
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
           
            addToHead(newNode);
            map.put(key,newNode);
        }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */