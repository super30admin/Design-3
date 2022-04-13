//Time Complexity : O(1) for all operations
//Space Complexity : O(n) map space and node space

class LRUCache {

    //create node class for doubly linked list
    class Node {
        int key;
        int val;
        Node next;
        Node prev;
        public Node(int key, int val)
        {
            this.key = key;
            this.val = val;
        }
    }

    //map for key against node
    HashMap<Integer, Node> map;

    //dummy head and tail for DLL
    Node head;
    Node tail;

    int capacity;

    public LRUCache(int capacity) {
        this.map = new HashMap();
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        //make it a doubly linked list
        this.head.next = tail;
        this.tail.prev = head;
        this.capacity = capacity;
    }

    //we keep LRU at tail and MRU at head

    //we'll need to add to MRU side
    private void addToHead(Node node)
    {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    //we'll need to remove from LRU side or from middle of DLL
    private void removeNode(Node node)
    {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public int get(int key) {

        //map does not have this key, so it won't be in our list either
        if(!map.containsKey(key))
            return -1;
        //if key exists, get the node from map
        Node node = map.get(key);
        //remove from current position in list
        removeNode(node);
        //put on MRU side (head)
        addToHead(node);
        return node.val;
    }

    public void put(int key, int value) {

        //existing key
        if(map.containsKey(key))
        {
            //get the node
            Node node = map.get(key);
            //update value of node
            node.val = value;
            //remove from current position in list
            removeNode(node);
            //put on MRU side (head)
            addToHead(node);
        }
        //fresh node
        else
        {
            //capacity of cache is full
            if(capacity == map.size())
            {
                //get LRU node
                Node lru = tail.prev;
                //remove from list
                removeNode(lru);
                //remove from map
                map.remove(lru.key);
            }

            //create the new node now - both for capacity full or not full
            Node fresh = new Node(key, value);
            //add to map
            map.put(key, fresh);
            //add to list at MRU side
            addToHead(fresh);
        }

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
