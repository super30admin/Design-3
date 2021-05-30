// Time Complexity: O(1) for both get and put
// Space Complexity: O(n) for hashmap where n = number of input nodes
// Did it reun on leetcode: yes
// Did you face any problem: no
class LRUCache {
    //capacity of cache
    int capacity;
    //head and tail nodes
    Node head;
    Node tail;
    //map to store key and node
    HashMap<Integer, Node> map;

    //constructor
    public LRUCache(int capacity) {
        //initializing capacity
        this.capacity = capacity;
        //initializing head and tail node
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        //connecting head and tail node
        this.head.next = this.tail;
        this.tail.prev = this.head;
        //initializing map
        this.map = new HashMap<>();

    }
    //class to create doubly linked list
    class Node{

        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }

    }

    public int get(int key) {
        //if key present in map
        if(map.containsKey(key)){
            //get the node
            Node node = map.get(key);
            //remove the node from its current position
            removeNode(node);
            //add the node at the begining
            addToStart(node);
            //return value of node
            return node.value;
        }
        // if key not present
        return -1;
    }

    public void put(int key, int value) {
        //if key not present
        if(!map.containsKey(key)){
            //if cache is full
            if(map.size()==this.capacity){
                //get least recently used node
                Node node = tail.prev;
                //delete least recently used node
                removeNode(node);
                ////remove least recently used node from hashMap
                map.remove(node.key);

            }
            //create new node
            Node node = new Node(key, value);
            //add node to map
            map.put(key, node);
            //move node to begining
            addToStart(node);

        }//if key present
        else{
            //get the key
            Node node = map.get(key);
            //update the value
            node.value = value;
            //update themap
            map.put(key, node);
            //remove node from current position
            removeNode(node);
            //move the node to begining
            addToStart(node);
        }

    }


    //remove node from its current position
    public void removeNode(Node node){
        //point next node's prev to previous node
        node.next.prev = node.prev;
        //point previous node's next to current node's next node
        node.prev.next = node.next;
    }

    //add given node to start
    public void addToStart(Node node){

        //point current node's next to head's next node
        node.next = head.next;
        //point head's next node's prev to current node
        head.next.prev = node;
        //point head's next to current node
        head.next = node;
        //point current node's prev to head
        node.prev = head;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */