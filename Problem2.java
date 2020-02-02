/* Design and Implement LRU Cache */

//Time complexity: O(1) for both put() and get() operations
//Space complexity: O(capacity) since it most used by hashmap and double linked list with most capacity+1 elements
//Any problem: No

//Approach-Explain to interviewer like this
//First we will start with drawing hashmap and insert all keys and corresponding values
//we can start with creating arraylist(non-optimal solution) and can access any element and doing get(), remove() and put() will cost O(N) linear order
//but we will then create linkedlist but it will still result in O(N) time complexity as storing and accessing references takes linear order
//now to get optimal solution in O(1), we will create doubly linked list to store prev and next pointers that will help in remove() and add() operations in O(1) at same time
//Now in hashmap, we have to store our node references as well in values to have get() access in O(1)
//Final Implementation in O(1) time is using Doubly Linked List+HashMap(with node references as values)

//Note: ArrayList and Queues can be used here as well but it will result in O(N) and we want O(1) constant order
//Here LRU cache is FIFO order

class LRUCache {
    //defining Node class
    class Node {
        int key;
        int val;
        Node prev;
        Node next;
        //constructor
        public Node(int key, int value){
            this.key = key;
            this.val = value;
        }
    }
    //1. removing node
    public void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    //2. adding node to head
    public void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node; 
    }
    //initialise HashMap with key as integer and value as Node references
    HashMap<Integer, Node> map;
    Node head; Node tail;
    int capacity;
    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = tail;
        this.tail.prev = head;
        this.capacity = capacity;
    }
    //3.get access from hashmap
    public int get(int key) {
        //check if hashmap contains key
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key); //O(1)
        removeNode(node); //0(1)
        addToHead(node); //0(1)
        return node.val;
    }
    //4. updating values in hashmap and then updating LRU node as well simultaneously
    public void put(int key, int value) {
        //if node exists in map
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        } else {
            //initialise new node
            Node newNode = new Node(key, value);
            //Case1) if capacity is not full
            if(map.size() < capacity){
                addToHead(newNode);
            } else {
                //removing LRU node
                Node leastUsed = tail.prev;
                removeNode(leastUsed);
                //remove key from hashmap as well
                map.remove(leastUsed.key);
                addToHead(newNode);
            }
            //we will update node value in hashmap
            map.put(key, newNode);
        }
    }
}
