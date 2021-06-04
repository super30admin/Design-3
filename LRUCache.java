// Time Complexity : O(1) for put and get functions.
// Space Complexity : O(n) number of elements inserted into the cache.
//            - O(n) for Map.
//            - O(n) for the double LinkedList.
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

class LRUCache {
    
    class Node{
        int key; int val;
        Node prev; Node next;
        
        Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    Map<Integer, Node> map;
    int capacity;
    Node head;
    Node tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    private void removeNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    private void addAtHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    public int get(int key) {
        // Check if the given key is present in the map.
        // If the key is not present then return -1.
        if(!map.containsKey(key)){
            return -1;
        }
        // If the given key is present then retrieve the node associated to that key.
        // Move the node to the head of the Double Linked List since it is accessed.
        Node curr = map.get(key);
        removeNode(curr);
        addAtHead(curr);
        // Once the node is moved to the head the Q it is in correct place.
        // Return the value associated to the Q.
        return curr.val;
    }
    
    public void put(int key, int value) {
        // Check if the given key is present in the map.
        if(map.containsKey(key)){
            
            // If the node is present in the map then update the value of the key.
            Node curr = map.get(key);
            curr.val = value;
            // After updating the value move the node to head of the Q since it is recently accessed.
            removeNode(curr);
            addAtHead(curr);
        }else{
            
            // If the node is not present in the map then the incoming value is a new key.
            // First check if the size of the Q is equal to the initialized capacity.
            if(capacity == map.size()){
                
                // If the Q capacity is at defined threshold then remove the last element from the Q.
                Node last = tail.prev;
                removeNode(last);
                // After removing the element from the Q, remove it from the map as well.
                map.remove(last.key);
            }
            
            // If the Q is not the max capacity then we can directly add the new element.
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addAtHead(newNode);
        }
    }
}
