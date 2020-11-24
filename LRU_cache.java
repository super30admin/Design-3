// Time Complexity : O(1), get and put operation are done on constant time
// Space Complexity : O(n), where is the number space required for 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

//Three Liner explanation of your code in plain english
//To implement LRU cache, the best data structures would be a combination of hashMap(as for integer keys, get can be done in O(1) time)
        //and a doubly linkedlist (with key and value as the node's attributes)(removing a node from its current position and adding 
        //it to head or tail can be done in O(1) time )
//For PUT operation, if capacity has reached, remove the node from the head, AND create a new node and add to the tail of duobly linked 
        //list and also add the node with its key in the hashMap
//For GET operation, get the node from the hashMap and return the value of the node

// Your code here along with comments explaining your approach

class LRUCache {
    //doubly linkedlist to maintain the order
    class ListNode{
        int key;
        int value;
        ListNode next;
        ListNode prev;
        
        public ListNode(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    //capacity of the cache
    int capacity;
    //head and tail of the doubly linkedlist
    ListNode head;
    ListNode tail;
    //HashMap to search the key (storing key with the corressponding Listnode )
    HashMap<Integer, ListNode> hMap;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        hMap = new HashMap<>();
        //creating dummy head and tail pointers
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    //function to remove the node from current position
    public void removeNode(ListNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    //function to add the node to the tail (Recently used are at the tail)
    public void addToTail(ListNode node){
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
    }
    
    //function to get the value for the provided key
    //If key exists return the value and move that node to the tail (recently used)
    public int get(int key) {
        if(!hMap.containsKey(key)) return -1;
        ListNode node = hMap.get(key);
        removeNode(node);
        addToTail(node);
        return node.value;
    }
    
    //function to put the key and value
    //if the key already exists, update its value and move the node to the tail
    //If the key does not exists, check if the capacity is full, if yes remove the least recently used (node at the head) and then add the newNode to the tail
    public void put(int key, int value) {
        if(hMap.containsKey(key)){
            ListNode node = hMap.get(key);
            node.value = value;
            removeNode(node);
            addToTail(node);
        }
        else{
            if(capacity == hMap.size()){
                ListNode toRemove = head.next;
                removeNode(toRemove);
                hMap.remove(toRemove.key);
            }
            ListNode newNode = new ListNode(key, value);
            hMap.put(key, newNode);
            addToTail(newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */