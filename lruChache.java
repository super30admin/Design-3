// Time Complexity: O(1) for put and get operation on LRU cache 
// Space Complexity: Map of number of nodes is created = > O(n)
// Write your approach here
// Idea here is to use combination of 2 DataStructures 1st Doubly Linked list having previous and next pointer along with key value
// and HashMap holding Key and Node as value.
// Now to perform a get we get the node from map in O(1) if not found -1 is returned else that node is removed from doubly linked
// list, managing previous and next Nodes there the node is not placed next to head at first position and its value is returned
// In case of insertion, we again check if the number already exist by getting from map.
// if the node is found, value of the node is changed
// and it is again removed from current position and added to the head.
// else if the node is not found there are 2 cases if we reached capacity or not
// if we did not reach capacity new Node is added to the head. else we remove node from previous to tail as well as map
// and new node is added to the head.
class LRUCache {
    class Node {
        int key;
        int val;
        Node left;
        Node right;
        
        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    Node head; Node tail;
    int capacity;

    Map<Integer, Node> map;
    int count;
    
    public void addNode(Node node){
        node.right = head.right;
        node.right.left = node;
        head.right = node;
        node.left = head;
    }
    
    public void deleteNode(Node node) {
        node.right.left = node.left;
        node.left.right = node.right;
    }
    
    public LRUCache(int capacity) {
        map = new HashMap();
        head = new Node(0,0);
        tail = new Node(0,0);
        count = 0;
        head.right = tail;
        tail.left = head;
        head.left = null;
        tail.right = null;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(map.containsKey(key)) {
            Node curr = map.get(key);
            deleteNode(curr);
            addNode(curr);
            return curr.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(!map.containsKey(key)) {
            Node curr = new Node(key, value);
            map.put(key, curr);
            if(count<capacity) {
                count++;
                addNode(curr);
            } else {
                map.remove(tail.left.key);
                deleteNode(tail.left);
                addNode(curr);
            }
        } else {
            Node curr = map.get(key);
            curr.val = value;
            deleteNode(curr);
            addNode(curr);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */