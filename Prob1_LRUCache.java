// Time Complexity : O(1) for both operations : get() and put(K,V)
// Space Complexity : O(Capacity)

// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

class LRUCache {
    class Node{ //  Doubly LinkedList Node
        int key, val;
        Node prev, next;
        Node(int key, int val){
            this.key = key;
            this.val = val;
            prev = null;
            next = null;
        }
    }
    Node head;
    Node tail;
    HashMap<Integer, Node> map;
    int capacity;
    
    public void removeNode(Node node){//Removing node from LinkedList
        node.prev.next = node.next;
        
        node.next.prev = node.prev;
    }
    public void insertAtHead(Node node){ //At node at head of LinkedList
        node.prev = head;
        node.next = node.prev.next;
        
        node.next.prev = node;
        node.prev.next = node;
    }
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        this.head.next = tail;
        this.tail.prev = head;
        map = new HashMap<>(); // Maintaining Hashmap to get node reference of any node in O(1) time
    }
    public int get(int key) {
        if(!map.containsKey(key))   return -1;
        //IF the node with given is there, return its value and mark node at most recently used by putting it at head
        Node curr = map.get(key);
        removeNode(curr);
        insertAtHead(curr);
        return curr.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){ // If node is existing with given key, just update its value
            Node curr = map.get(key);
            curr.val = value;
            removeNode(curr);
            insertAtHead(curr);
        }else{
            if(capacity == map.size()){//When capacity of cache is full, delete least recently used node from LinkedList and HashMap 
                Node lastNode = tail.prev;
                removeNode(lastNode);
                map.remove(lastNode.key);
            } //Then add new node at head
            Node newNode = new Node(key,value);
            insertAtHead(newNode);
            map.put(key, newNode);
        }
    }
}
