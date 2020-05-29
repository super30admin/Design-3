// Time Complexity : O(1) All operations
// Space Complexity : O(2n) for hashmap and doubly linked list
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None
/* Algorithm: Introduce a Doubly Linekd List and a Hashtable. Doubly linked list helps us to eliniminate O(n) traversal to find a previous to the
current node, and hashtable helps to have O(1) access to the node that we want to use it for. Whenever we are accessing a node, we need to make sure
that the node reaches to the front of the linked list (for get and update operations). For put operations, we need to check if we can add the node
satisfying the capacity constraint else wise rmeove the least recently used node, that is at the end of the linked list marked by the tail.prev. Remove
that node and add the new node to the front of the linked list marked by head.next. If we are doing any operation on a node, ensure that the map is
being updated timely after each operation.
*/
class LRUCache {
    class Node {
        Node next;                                              // Doubly Linked List Structure
        Node prev;
        int val;
        int key;
        Node(int k, int v){
            this.key = k;
            this.val = v;
            this.next = null;
            this.prev = null;
        }
    }
    
    int capacity;
    HashMap<Integer, Node> store;
    Node head;
    Node tail;

    public LRUCache(int capacity){
        this.store = new HashMap<>();                                                       // Initialising the necessities
        this.capacity = capacity;
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next = tail;                                                      // Connect the head and tail
        this.tail.prev = head;
    }
    
    public void put(int key, int val){
        if(!store.containsKey(key)){
            if(capacity == store.size()){                                                   // if capacity is full
                store.remove(tail.prev.key);                                            // Remove the least recently used node
                removeNode(tail.prev);                                                  // Update the map
            }
            Node new_node = new Node(key, val);
            addToHead(new_node);                                                    // Add the new node always to the front of the list 
            store.put(key, new_node);
        } else {
            Node n = store.get(key);
            n.val = val;                                                            // Update the node value
            removeNode(n);                                                              
            addToHead(n);                                                           // Remove it and add it to the front because its been recently accessed
        }
    }
    public int get(int key){
        if(!store.containsKey(key)){                                                 // Key not found
            return -1;
        } else {
            Node fetch = store.get(key);                                                // Key found
            removeNode(fetch);
            addToHead(fetch);                                                       // Remove it and add it to the front
            return fetch.val;                                                       // Return the value of the key
        }
    }
    public void addToHead(Node fetch){
            fetch.next = head.next;                                                 // Node's next points head's next
            fetch.prev = head;                                                      // Node's previous points the head
            head.next = fetch;
            fetch.next.prev = fetch;                                                // Node's next's previous points to Node itself
    }
    
    public void removeNode(Node fetch){
        fetch.next.prev = fetch.prev;                                               // Adjust the previous and next pointers to remove the node
        fetch.prev.next = fetch.next;
    }
}
