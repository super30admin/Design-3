// Time Complexity : O(1)
// Space Complexity : O(capacity) //we are using a hashmap
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : no

/**
 * We are using a hashmap and initializing a doubly linked list to perform get and put. We are keeping the MRU node
 * at the head node.
 */


class LRUCache {

    class Node{

        int key;
        int val;
        Node next;
        Node prev;

        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    private void addToHead(Node node){

        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    private void removeNode(Node node){

        node.next.prev = node.prev;
        node.prev.next = node.next;
    }


    Node head;
    Node tail;
    int capacity;
    //hashmap
    HashMap<Integer, Node> map;


    public LRUCache(int capacity) {

        map = new HashMap<>();

        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);

        //initializing the doubly inlked list
        this.head.next = tail;
        this.tail.prev = head;
        this.capacity = capacity;
    }

    public int get(int key) {

        //if not there
        if(!map.containsKey(key)) return -1;

        //if the key is there
        //will gte the refernce of that node to remove it
        //and adding it to head
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);

        return node.val;
    }


    public void put(int key, int value) {

        //if node is there
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }

        //if node doesn't exists
        else{
            if(capacity == map.size()){
                //removing the LRU node
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key,value);
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