// Time Complexity : get() - O(1) put() - O(1)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Thinking the approach

// Your code here along with comments explaining your approach

class LRUCache {

    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    
    Node head;
    Node tail;
    int capacity;
    HashMap<Integer,Node> hmap;
    
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

    public LRUCache(int capacity) {
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next = tail;
        this.tail.prev = head;
        this.capacity = capacity;
        this.hmap = new HashMap<>();
    }
    
    public int get(int key) {
        if(!hmap.containsKey(key)){
            return -1;
        } else {
            Node node = hmap.get(key);
            removeNode(node);
            addToHead(node);
            return node.value;
        }
    }
    
    public void put(int key, int value) {
        if(hmap.containsKey(key)){
            Node node = hmap.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
        } else {
            if(hmap.size() == this.capacity){
                //remove the LRU Node
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                hmap.remove(tailPrev.key);   
            }
            Node node = new Node(key,value);
            addToHead(node);
            hmap.put(key,node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
