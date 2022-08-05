// Time Complexity : O(1)
// Space Complexity : O(capacity), capacity of the hashmap
// Did this code successfully run on Leetcode : Yes


//Maintain the most recently nodes in the beginning of the linkedlist and least recently used nodes at the end of the linkedlist
// Doubly linked list is used to easily add nodes in the beginning and remove nodes from the end. Pseudo head and tail helps in eliminating boundary check for null
class LRUCache {

    //Doubly linked list
    class Node{
        int key, value;
        Node prev, next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    HashMap<Integer, Node> map;
    int capacity;
    Node head, tail;

    //add the node to the beginning of the linkedlist
    public void addNode(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    //remove the node
    public void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    public LRUCache(int capacity) {
        //initialize hashmap with the given capacity
        map = new HashMap<>(capacity);
        this.capacity = capacity;
        //dummy head
        head = new Node(-1, -1);
        //dummy tail
        tail = new Node(-1, -1);
        //link dummy head and dummy tail
        head.next = tail;
        tail.prev = head;
    }

    //if the key exists in the map, remove that node and add to front (as it is recently used) and return the value
    public int get(int key) {
        if(map.get(key) == null) return -1;
        Node node = map.get(key);
        removeNode(node);
        addNode(node);
        return node.value;
    }


    public void put(int key, int value) {
        //if the key already exists, update the value of the key, remove the node and add it to front
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addNode(node);
            return;
        }
        //if the hashmap which maintains cache reaches the capacity, remove the least recently node which is at the end(tail)
        if(map.size() == capacity) {
            Node tailPrev = tail.prev;
            removeNode(tailPrev);
            map.remove(tailPrev.key);
        }
        //add the new node to the beginning of the linkedlist
        Node node = new Node(key, value);
        addNode(node);
        map.put(key, node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */