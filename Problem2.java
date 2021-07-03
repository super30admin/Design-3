
// Time - O(1) for put and get operations
//Space - O(capacity)



class LRUCache extends LinkedHashMap<Integer, Integer>{

    // Doubly linkedlist
    class Node {

        int key;
        int val;
        Node next;
        Node prev;
        public Node(int key, int val) {

            this.key = key;
            this.val = val;

        }

    }

    // add to Node by changing the pointers position by adding node next to the head node
    public void addToNode(Node node) {

        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;



    }

    // remove the node from the list
    public void removeNode(Node node) {

        node.next.prev = node.prev;
        node.prev.next = node.next;


    }
    HashMap<Integer,Node> map;
    int capacity;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;


    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1; // if the map does not contain key return -1
        Node node = map.get(key);   // get the reference of the node by searching thee key in the hashmap
        removeNode(node); // remove the node from the list
        addToNode(node); // add the node to the front of the list after the head
        return node.val;

    }

    public void put(int key, int value) {
        // if the map contains key and value
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToNode(node);
        }
        else {
            // if the capacity is full remove the last entry from the list which is prev pointer to the tail from hashmap and the list
            if(capacity == map.size()) {
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key, value); // if the list is not full add the node to the front of the list after head
            addToNode(newNode);
            map.put(key,newNode); // add the key and node references to the hashmap

        }

    }

}
