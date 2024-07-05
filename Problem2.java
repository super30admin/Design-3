// Time Complexity : O(1)
// Space Complexity : o(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// Use a doubly linked list to keep track of the order of elements and a hash map to store the key-node mappings.
//On get, move the accessed node to the head of the list to mark it as recently used and return its value.
//On put, add the new node to the head, remove the least recently used node if the capacity is exceeded, and update the hash map accordingly.

class LRUCache {

    class Node{
        int key, value;
        Node next, prev;

        public Node(int key, int value){
            this. key = key;
            this.value = value;
        }
    }

    Node head, tail;

    private void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        head.next = node;

    }

    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    HashMap<Integer, Node> map;
    int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            addToHead(node);
            node.value = value;
            return ;
        }
        if(capacity == map.size()){
            Node tailprev = tail.prev;
            removeNode(tailprev);
            map.remove(tailprev.key);
        }
        Node node = new Node(key, value);
        addToHead(node);
        map.put(key, node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */