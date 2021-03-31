class LRUCache {
    
    // Create a Doubly Linked List Node 
    class Node {
        int key, val;
        Node next, prev;
        
        public Node (int key, int val) {
            this.key = key;
            this.val = val;
            next = null;
            prev = null;
        }
    }
    
    Node head, tail;
    int capacity, size;
    Map<Integer, Node> map;
    
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        size = 0;
        
        // Initialize extra nodes as head and tail for easy insertion and deletion later
        head = new Node(0,0);
        tail = new Node(0,0);
        
        head.next = tail;
        tail.prev = head;
    }
    
    // Add a node to the linked list
    public void add(Node curr){
        Node temp = tail.prev;
        temp.next = curr;
        curr.prev = temp;
        curr.next = tail;
        tail.prev = curr;
    }
    
    // Remove a node from the linked list
    public void remove(Node curr){
        Node before = curr.prev;
        Node after = curr.next;
        
        before.next = after;
        after.prev = before;
    }
    
    // Update the linked list - when a get operation is called or we put the same element into the cache
    public void update(Node curr){
        remove(curr);
        add(curr);
    }
    
    // Time Complexity: O(1)
    // Space Complexity: O(capacity)  
    public int get(int key) {
        Node curr = map.get(key);
        if(curr == null)
            return -1;
        update(curr);
        return curr.val;
    }
    
    // Time Complexity: O(1)
    // Space Complexity: O(capacity)  
    public void put(int key, int value) {
        Node curr = map.get(key);
        // If its a new page --> add it to the linked list and map
        if(curr == null){
            curr = new Node(key, value);
            map.put(key, curr);
            size++;
            add(curr);
        }
        // If its an old page --> update the linkedlist and map with the new value (since it is visited again)
        else{
            update(curr);
            curr.val = value;
        }
        
        // If cache goes out of capacity - then remove the least recently used page - head.next
        if(size > capacity){
            map.remove(head.next.key);
            remove(head.next);
            size--;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */