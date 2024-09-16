// Space Complexity: O(capacity)
class LRUCache {

    class Node {
        int key, val;
        Node next, prev;
        
        public Node(int key, int val)
        {
            this.key = key;
            this.val = val;
        }
    }
    
    int capacity;
    Map<Integer, Node> map; // used to keep reference of the key so that easier to update
    Node head, tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        
        head = new Node(-1, -1);
        tail = new Node(-1,-1);
        
        //make Doubly LL
        head.next = tail;
        tail.prev = head;
    }
    
    //O(1)
    private void removeNode(Node node)
    {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // O(1)
    private void addToHead(Node node)
    {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
        
    }

    // O(1)
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        
        // get node refer
        Node node = map.get(key);
        // remove curr location
        removeNode(node);
        // put as recently used to head
        addToHead(node);
        
        return node.val;
    }
    
    //O(1)
    public void put(int key, int value) {
        if(map.containsKey(key))
        {
            // get node refer
            Node node = map.get(key);
            // update val
            node.val = value;
            // remove curr location
            removeNode(node);
            // put as recently used to head
            addToHead(node);
        }
        else
        {
            //new node
            Node node = new Node(key, value);
            if(map.size() == capacity)
            {
                // get least recently used
                Node tailPrev = tail.prev;
                // make space by removing least recently used                
                removeNode(tailPrev);
                // remove refer
                map.remove(tailPrev.key);
            }
            // put as recently used to head
            addToHead(node);
            // add to map
            map.put(key, node);
        }
    }
}