// Time Complexity :
get(key) and put(key, value ) take O(1) as we are using map
and delete a node and insering at head takes o(1) time in doubly linked list
// Space Complexity :
O(capacity) as we are using a Doubly linked list and a map
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach


class LRUCache {
    
    //Define calss ListNode
    class ListNode{
        //This class has key and value which is provided to us by put method
        int key, value;
        //Doubly linked list has previous and next pointers
        ListNode previous, next;
        
        //Define constructor
        ListNode(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    
    //Also, we need a head and a tail pointer to perform a delete operation
    ListNode head, tail;
    //we will need a variable called size
    int size;
    //we will also need a map
    Map<Integer,ListNode> map;

    public LRUCache(int capacity) {
        //Initialize all the variables
        head = null;
        tail = null;
        size = capacity;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            ListNode node = map.get(key);
            /*remove the node and place it at the head as the element to be processed should be at head */
            remove(node);
            setHead(node);
            return node.value;
        }
        //else return -1
        return -1;
    }
    
    public void put(int key, int value) {
        //If key already exists then we need to update it's value
         if(map.containsKey(key)){
             //fetch the node
            ListNode node = map.get(key);
             /*change the value of the fetched key to the value to which it has to be updated*/
             node.value = value;
         /*remove the node and place it at the head as the element to be processed should be at head */
             remove(node);
             setHead(node);
    }
        /*if key does not exist then it has to be a new key, value pair which has to
        be placed at head by removing lru key 
        */
        else{
            //check if capacity is sufficient to add new pair to map
            if(map.size() == size){
                //remove from map
                map.remove(tail.key);
                //remove from linked list
                remove(tail);
            }
            //if there is still space in map then create new pair
            ListNode node = new ListNode(key,value);
            map.put(key,node);
            //set this node as head
            setHead(node);
        }
}
    
    void remove(ListNode node){
        if(node.previous != null){
            node.previous.next = node.next;
        }
        else{
            //if it is head node just change the head to next
            head = node.next;
        }
        
        //forward connection
        if(node.next != null){
            node.next.previous = node.previous;
        }
        else{
            //if it is the tail we need to remove
            tail = node.previous;
        }
    }
    
    void setHead(ListNode node){
        //the new node's head will be pointing to null
        node.previous = null;
        node.next = head;
        if(head != null){
            head.previous = node;
        }
        head = node;
        //if the linked list is empty then the new node will be the head and tail
        if(tail == null){
            tail = node;
        }
    }
    
}
    

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
