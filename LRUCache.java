class LRUCache {
    /*I will keep 2 data stuctures map and linked list
    map for doing get and put operation O(1) time. 
    but to maintain the order and delele least recetly used ele in O(1) time map is not feasible(O(n))
    so to maintain the order we would need linear datastructure which support delete in O(1) time and that       is linked list. 
    I would be using doubly linked list as given a reference of node the node we can delete the node in the 
    linked list. 
    Singly linked lists could have worked, but are harder to implement correctly, because if we want to
    remove an item from the list you should to know its predecessor.
    */
    //node class
    private class Node{
        int key, value;
        Node next, prev;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    //member variables of the class
    private Node head, tail;
    private int maxSize;
    private Map<Integer, Node>map;
    
    public LRUCache(int capacity) {
        maxSize= capacity;
        map = new HashMap<>();
        
        head= new Node(-1, -1);
        tail= new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    private void addToHead(Node node){
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }
    private void removeNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    private void moveToHead(Node node){
        removeNode(node);
        addToHead(node);
    }
    //get -O(1), remove node -O(1) and add node to head -O(1)
    public int get(int key) {
        if(!map.containsKey(key)) 
            return -1;
        Node node = map.get(key);
        //move node to head when we access the current it as it is most recently used key now.
        moveToHead(node);
        return node.value;
    }
    //map insertion -O(1), remove node -O(1) and add node to head -O(1)
    public void put(int key, int value) {
        //case 1 : entry already present for the key, just update the value.
        //and as we access this ele move that to head to make it most recently.
        if(map.containsKey(key)){
            Node node = map.get(key);
            moveToHead(node);
            node.value = value;
        }
        else{
            //if cache size exceeds, delete tail node
            if(map.size()  == maxSize){
                Node nodeToBeEvicted = tail.prev;
                removeNode(nodeToBeEvicted);
                //remove from map too
                map.remove(nodeToBeEvicted.key);
            }
            //now insert as space is available now.
            Node newNode = new Node(key, value);
            //add entry in the linked list as well as in map.
            addToHead(newNode);
            map.put(key, newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
