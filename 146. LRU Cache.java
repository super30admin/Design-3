// Time Complexity : O(1)
// Space Complexity : O(Capacity)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach: We will be using HashMap with doubly LinkedList, In hashmap we will store the key and address of the linklist, when the linklist gets updated it is made head so in this way we will get the tail as Least Recent Used

class LRUCache {
    
    class Node{
        int key, value;
        Node prev, next;
        public Node(int key, int value){
            //creating local value
            this.key = key;
            this.value = value;
        }
    }
    
    Node head, tail;
    HashMap<Integer, Node> map;
    int capacity;
    
    private void removeNode(Node node){
        node.next.prev = node.prev; //current node's nect's prev is set to currentnode's prev, basically linking prev and next of current node and removing current node from there
        node.prev.next = node.next; //vice verca of above comment
    }
    
    //removed node is to be placed as head because it got updated
    private void addToHead(Node node){
        node.next = head.next;      //removed node becomes head.next here here
        node.prev = head;           //node's previous is now set to dummy head
        head.next = node;           //node is now set to dummy head's next
        node.next.prev = node;      //node's next's previous is set here
    }
    
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = new Node(-1, -1);    //Dummy Head
        tail = new Node(-1, -1);    //Dummy Tail
        head.next = tail;
        tail.prev = head;
        
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;    //if there is no key available then return -1
        Node node = map.get(key);               //Get node associated with the key
        removeNode(node);                       //Remove node from the current position and add to head, which happens in below line
        addToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {   
        //if it contains key then get it and move to head.next
        if(map.containsKey(key)){
            Node node = map.get(key);           //Get node associated with the key
            node.value = value;
            removeNode(node);
            addToHead(node);
            return;
        }
        
        //Check size of capacity, then add the node
        if(map.size() == capacity){
            Node tailPrev = tail.prev;
            removeNode(tailPrev);
            map.remove(tailPrev.key);
        }
        
        //Create a new Node
        Node node = new Node(key, value);
        addToHead(node);
        map.put(key, node);
    }
}

