/**
 * TC: O(1) 
 * SC: O(capacity)
 * LeetCode: Y(https://leetcode.com/problems/lru-cache/)
 * Approach: Use a Hashmap to store key,value 
 * To obey the constraints of LRU Cache, one can use Stack, Queue.
 * All DS listed above would take O(N)
 * Singly Linked List: One can track tail and head of singly LL. tail would be the LRU item.
 * So removing a LRU item from tail, I need access to the item that was previous to tail. That previous item would be the     new LRU and tail needs to point to that.
    Singly LL would not give me O(1) access to previous of tail
    So use Doubly LL to get previous in O(1).
 */

class LRUCache {

    class Node {
        int key, value;
        Node next, prev;
        
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    Node head, tail;
    int capacity;
    Map<Integer, Node> map;
    
    public LRUCache(int capacity) {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        // get value of existing node
        if(map.containsKey(key)) {
            Node current = map.get(key);
            remove(current);
            addNodeToBeginning(current);
            
            return current.value;
        }
        // return -1 for the value of key that not was seen before
        return -1;
    }
    
    public void put(int key, int value) {
        // if the key to be inserted already exists in the cache then update value, move node to beginning of LL(recently used)
        if(map.containsKey(key)) {
            // get node
            Node current = map.get(key);
            // update value
            current.value = value;
            // remove node from its current position
            remove(current);
            // add node to beginning - most recently used
            addNodeToBeginning(current);
            return;
        }
        // if the current item is new and current cache size is occupied 100%
        else if(map.size() == capacity) {
            // get lru item
            Node lruNode = map.get(tail.prev.key);
            // remove lru item from map
            map.remove(lruNode.key);
            // remove lru from DoublyLL
            remove(lruNode);
        }
        // insert a new node after determining the new node is not present and there is space in the cache
        Node newNode = new Node(key, value);
        // add new node to beginning
        addNodeToBeginning(newNode);
        // update hashmap
        map.put(key, newNode);
    }
    
    // helper function to remove a node
    private void remove(Node current) {
        current.prev.next = current.next;
        current.next.prev = current.prev;
    }
    
    // helper function to add a node at the beginning
    private void addNodeToBeginning(Node current) {
        current.next = head.next;
        head.next.prev = current;
        current.prev = head;
        head.next = current;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
