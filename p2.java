// Time Complexity :O(1) for get and put
// Space Complexity :O(1) for get and put
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach

class LRUCache {
    //Using doubly linked list to keep track of least recently used key
    class Node{
        int key;
        int val;
        Node prev;
        Node next;
        public Node(int key, int value){
            this.key = key;
            this.val = value;
        }
    }

    Node head;
    Node tail;
    //Using hashmap to avoid traversing linked list.
    Map<Integer, Node> map;
    int capacity;

    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    private void addNode(Node node){
        node.next = this.head.next;
        node.prev = this.head;
        this.head.next = node;
        node.next.prev = node;
    }


    public LRUCache(int capacity) {
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.map = new HashMap<>();
        this.capacity = capacity;
    }
    
    //Return the value if the key is present and move the position of the key to the top in linkedlist
    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            addNode(node);
            return node.val;
        }
        return -1;
    }
    
    //if the key is already present, update the value and put the key to the top of linked list
    public void put(int key, int value) {

        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            addNode(node);
            node.val = value;
            return;
        }


        // If key is not present, check the size of hashmap
        // If the size of hashmap is equal to the capacity of LRU cache, remove the LRU key frm linkedlist and hashmap. Insert the new key in hashmap and in linkedlist at the top
        if(capacity == map.size()){
            Node node = tail.prev;
            map.remove(node.key);
            removeNode(node);
        }
            Node node = new Node(key,value);
            addNode(node);
            
            map.put(key,node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */