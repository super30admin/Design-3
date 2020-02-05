//Time Complexity : O(1)
//Space Complexity : O(1)

class LRUCache {
    
    //class Node
    class Node
    {
        int key, value;
        Node next, prev;
        public Node(int key, int value)
        {
            this.key = key;
            this.value = value;
        }
    }
    //function to remove the least recently used node
    private void removeNode(Node node)
    {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    //to add node to the head position
    private void addToHead(Node node)
    {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    //declarations
    int capacity;
    HashMap<Integer, Node> map;
    Node head, tail;
    
    public LRUCache(int capacity) 
    {
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1,-1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.capacity = capacity;
    }
    
    //get node becomes most recently used node
    public int get(int key) {
        //if not present -> return -1
        if(!map.containsKey(key))
            return -1;
        //else remove from tail, add to head
        //since its the most recently used node
        else{
            Node node = map.get(key);
            removeNode(node);
            addToHead(node);
            return node.value;
        }
    }
    
    //adding node
    public void put(int key, int value) 
    {
        //if the key exists
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
        } 
        //if the key doesnot exist
        else
        {
            Node newNode = new Node(key, value);
            //if capacity is full
            if(map.size() == capacity)
            {
                Node leastRecentlyUsedNode = tail.prev;
                removeNode(leastRecentlyUsedNode);
                map.remove(leastRecentlyUsedNode.key);
                addToHead(newNode);
            }
            //if capacity not full yet
            // less than capacity
            else
            {
                addToHead(newNode);
            }
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