/* Time Complexity : O(1) */
/* Space Complexity : O(k) 
 *  k - size of the hashmap */
// Did this code successfully run on Leetcode : Yes 
// Any problem you faced while coding this :

class LRUCache {
    class Node{
        int key;
        int val;
        Node next;
        Node prev;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    Node head;
    Node tail;
    HashMap<Integer, Node> map;
    int capacity;

    public LRUCache(int capacity) {
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next = tail;
        this.tail.prev = head;
        this.map = new HashMap<>();
        this.capacity = capacity;
    }

    private void removeNode(Node node){        
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    private void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    public int get(int key) {
        //key not present
        if(!map.containsKey(key)) return -1;
        //key present - move node to head and return the nodes value
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        //key present -> update value and move node to head
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }
        //key not present
        else{
            //capcity is full - remove least used node (at the tail)
            if(capacity == map.size()){
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            //add the new node at the head
            Node newNode = new Node(key, value);
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