// Time Complexity : O(1)
// Space Complexity : O(number of nodes in Hashmap)

class LRUCache {
    
    Node head = new Node(0, 0);
    Node tail = new Node(0, 0);
    HashMap<Integer, Node> h = new HashMap();
    int size;
    
    public LRUCache(int capacity) {
        size = capacity;
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(h.containsKey(key))
        {
            Node node = h.get(key);
            remove(node);
            insert(node);
            return node.second;
        }
        else return -1;
        
    }
    
    public void remove(Node n)
    {
        h.remove(n.first);
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }
    
    public void insert(Node n)
    {
        h.put(n.first, n);
        n.prev = head;
        n.next = head.next;
        head.next = n;
        n.next.prev = n;
    }
    
    public void put(int key, int value) {
        if(h.containsKey(key))
            remove(h.get(key));
        
        if(h.size() == size)
            remove(tail.prev);
        
        insert(new Node(key, value));
    }
    
    class Node
    {
        Node prev, next;
        int first, second;
        Node(int x, int y)
        {
            first = x;
            second = y;
        }
    }
}