/*
This program implements an LRU cache. The behaviour of an LRU cache is:
1. Whenever a new data packet is inserted, we append it to the front of the cache, and delete the least recently used node from the cache.
2. Whenever a packet in the cache is accessed, we bring it to the front of the cache.

With these considerations in mind, we design the LRU cache using a doubly linked hashmap i.e A hashmap of a doubly linked list.

For the get function, we simply get the value of the node from the map, remove it from its current location and then add it
to the head of the list.

For the put function, we either update the value of an existing key if the incoming data has the same key, or we remove the node
at the tail of the list, and then add the incoming data to the head of the list.

Did this program run on leetcode: Yes
*/
class LRUCache {
    //We store the key of the data along with its value on the node, so that we can remove the Node from the map as well as the list whenever
    //it has to removed
    class Node{
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
    //We initialise a head and tail node to keep track of the start and end of the list respectively
    Node head;
    Node tail;
    HashMap<Integer, Node> map;
    int capacity;
    public LRUCache(int capacity) {
        
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.map = new HashMap<>();
        this.capacity = capacity;
        
    }
    //Time Complexity = O(1)
    public int get(int key) {
        
        if(!map.containsKey(key))
            return -1;
        
        Node node = map.get(key);
        
        removeNode(node);
        addToHead(node);
        
        return node.val;
        
    }
    //Time Complexity = O(1)
    public void put(int key, int value) {
        
        if(map.containsKey(key))
        {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }
        else
        {
            if(capacity == map.size())
            {
                Node delete = tail.prev;
                removeNode(delete);
                map.remove(delete.key);
                
                Node node = new Node(key, value);
                addToHead(node);
                map.put(key, node);
            }
            else
            {
                Node node = new Node(key, value);
                addToHead(node);
                map.put(key, node);
            }
        }
        
    }
    //We create functions removeNode and add to head since we are using it during both the get and the put functions
    
    //Time Complexity = O(1)
    private void removeNode(Node node)
    {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }
    //Time Complexity = O(1)
    private void addToHead(Node node)
    {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
}
