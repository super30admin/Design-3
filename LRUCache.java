class LRUCache {
  class Node {
    int key;
    int val;
    Node next;
    Node prev;
    Node(int key, int val) {
        this.key = key;
        this.val = val;
        this.next = null;
        this.prev = null;
    }
}

Node head;
Node tail;

int capacity;
Map<Integer, Node> map;
int currCapacity;
public LRUCache(int capacity) {
    this.capacity = capacity;
    this.head = new Node(-1, -1);
    this.tail = new Node(-1, -1);
    this.map = new HashMap<>();
    head.next = tail;
    tail.prev = head;
}

/**
 * 
 * Time Complexity: O(1)
 * Were you able to solve this on leetcode? Yes
 */
public int get(int key) {
    if(map.containsKey(key)){ 
        // move the node to the head of list.
        
        Node curr = map.get(key);
        removeNode(curr);
        addToHead(curr);
        return map.get(key).val;
    }
    
    return -1;
}

/**
 * 
 * Time Complexity: O(1)
 * Were you able to solve this on leetcode? Yes
 */
public void put(int key, int value) {
    // if key already exists, just update the existing Node value.
    if(map.containsKey(key)){
        Node curr = map.get(key);
        curr.val = value;
        // System.out.println("updating: "+ key + ",  value:" + value);
        removeNode(curr);
        addToHead(curr);
        
    } else { // key doesn't already exist.
        // if current capacity  == the max capacity of cache
        if(map.size() == this.capacity) {
            Node nodeToRemove = this.tail.prev;
            map.remove(nodeToRemove.key);   
            removeNode(nodeToRemove);

        }
        
        Node nodeToInsert = new Node(key, value);
        addToHead(nodeToInsert);
        map.put(key, nodeToInsert);
    } 
}

private void removeNode(Node node) {
    if(node == null) { return; }
    
    node.prev.next = node.next;
    node.next.prev = node.prev;
}

private void addToHead(Node node){
    if(node == null) { return; }
    
    node.prev = head;
    node.next = head.next;
    head.next = node;
    node.next.prev = node;
  }
}
