/**  Time Complexity : O(1) for both functions
 Space Complexity : O(Capacity)
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No
 your code here along with comments explaining your approach
 Main Idea:
 A HashMap that has key to listnode reference
 A doubly linked list that keeps track of most recent on head and least recent at tails
 **/

class LRUCache {
    class Node{
        Node prev;
        Node next;
        int value, key;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    Node head, tail;
    HashMap<Integer, Node> map;
    int capacity;
    
    public void removeNode(Node node){
         node.prev.next = node.next;
         node.next.prev = node.prev;
        
    }
    
    public void addHead(Node node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    public LRUCache(int capacity) {
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            addHead(node);
            return node.value;
        }else{
            return -1;
        }
        
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addHead(node);
        }else if(map.size() == capacity){
            Node last = tail.prev;
            removeNode(last);
            map.remove(last.key);
            Node node = new Node(key, value);
            map.put(key, node);
            addHead(node);
        }else{
            Node node = new Node(key, value);
            map.put(key, node);
            addHead(node);
        }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
