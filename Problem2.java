// Time Complexity - O(1)
// Space Complexity - O(n)
// This solution worked on LeetCode
// LRU can be implemented using any linear data structure like Arrays, Queue, Stack, HashMap but these implementations will be O(n) time Complexity. This is why Doubly Linkedlist and HashMap will be used for O(1) time complexity for LRU cache operations. 
HashMap will have Key and Value as reference to the Linked List node containing the key and value pair.


class LRUCache {
    int size =0;
    Node head;        // Head of the doubly link list
    Node tail;        // Tail of the doubly link list
    int capacity;     // Capacity of the LRUCache
    HashMap<Integer, Node> map;
    private class Node{     //Doubly Linked List Node
        int key;    
        int val;
        Node next;    
        Node prev;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        } 
    }
    public LRUCache(int capacity) {   
        map  = new HashMap<>(); //Initialize the data structures 
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }
    
    public void removeNode(Node node){  
        node.prev.next = node.next;    // Remove the Node from the list
        node.next.prev = node.prev;
        
    }
    
    public void addToHead(Node node){   // Add the node after the dummy head node
        node.next = head.next;
        head.next = node;
        node.prev = head; 
        node.next.prev = node;          
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
                return -1;
        Node node = map.get(key);
        removeNode(node);   //The recently used cache will be removed from the doubly linked list and 
        addToHead(node);     // Add to head so as to maintain the recently used order.
        return node.val;
        //return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){     // If the map already contains the Key, update the existing value in HashMap with the new Value
            Node node = map.get(key); // Retrieve the node from the map and update the node val with new value
            node.val = value;
            removeNode(node);     //Since this node is recently updated. remove the node from the list and add to head 
            addToHead(node);
        }
        else{
           Node node = new Node(key,value);   //Else if the key is not already existing check the capacity of the cache
            if(size < capacity){              // If the size of the list is less than capacity add the new node at the head
                addToHead(node);
                size++;         //increment the size to keep track of the size of the list
            }
            else{
                Node tailprev = tail.prev;      // else , remove the least recently used node which is the previous node of the dummy tail node
                removeNode(tailprev);
                map.remove(tailprev.key);     // remove the key of the node from the HashMap as well
                addToHead(node);      // Add the new node at head        
            }
            map.put(key,node);      // Put the new key, node in HashMap
        }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
