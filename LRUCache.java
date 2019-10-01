//time complexity:  O(1)
//SPace complexity: O(n) n being the capacity of the cache
//Approached the problem that there is a need of 2 datastrutures HAshmap (to store the values) and doubley linkedlist (to keep track of the order as we must keep reordering from time to time)
//Ran and accepted on leetcode
class LRUCache {
    //make node class for the doubley linked list and for hashmap datastructures
    private class Node
    {
        int key, value;
        Node prev, next;
        
        Node(int k, int v)
        {
            this.key = k;
            this.value = v;
        }
        
        Node()
        {
            this(0,0);
        }
    }
    //Initialize required variables for doubley linkedlist and Hashmap
    private int capacity, count;
    private Map<Integer,Node> map;
    private Node head, tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        Node n = map.get(key);
        //If the value not in linked list return -1
        if(n == null) 
        { 
            return -1;
        }
        //if present update the node and return the value of that key
        update(n);
        return n.value;
    }
    
    public void put(int key, int value) 
    {
        //get the value from the hash map and store as the n to add int the linkedlist
        Node n = map.get(key);
        //If the node is null 
        if(n==null)
        {
            //make a new node which is n
            n = new Node(key,value);
            //put it in the hashmap
            map.put(key,n);
            //add it to the linkedlist
            add(n);
            //Increment the counter
            ++count;
        }
        else
        {
            //assign the value as the value for node n 
            n.value = value;
            //update the node 
            update(n);
        }
        //If iy exceeds storing capacity 
        if(count>capacity)
        {
            //set the last element in the linked list as the node to be deleted
            Node toDel = tail.prev;
            //remove the node form the linkedlist
            remove(toDel);
            //also remove from hashmap
            map.remove(toDel.key);
            //decrement the counter
            --count;
        }
    }
    
    //Function to update the order
    private void update(Node node)
    {
        //call remove function to remove the node from initial position
        remove(node);
        //call add function to add the node at the front
        add(node);
    }
    
    //function to add node in linked list
    private void add(Node node)
    {    //head <->2<->3<->tail (node = 1)
        Node after = head.next;     //2 = after          
        head.next = node;           //head ->1
        node.prev = head;           //head<->1
        node.next = after;          //1->2
        after.prev = node;          //1<->2
        //head<->1<->2<->3<->tail (added 1)
    }
    
    //function to remove a node from the linked list
    private void remove(Node node)
    {
        //head<->2<->1<->3<->tail (node = 1)
        Node before = node.prev;    // before = 2
        Node after = node.next;    // after = 3
        before.next = after;        // 2->3 
        after.prev = before;        //2<->3
        //head<->2<->3<->tail (removed 1)
    }
}