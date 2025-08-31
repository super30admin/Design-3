class LRUCache {
    //Idea is to use a doubly linked list to store key values and a HashMap to perform quick search on linked list nodes
    //Time Complexity: O(1)
    //Space Complexity: O(1)

    private class Node{
        int key;
        int val;
        Node prev;
        Node next;
        Node(int key, int val)
        {
            this.key = key;
            this.val = val;
        }
    }

    private HashMap<Integer,Node> hm;
    private Node dummyhead;
    private Node dummytail;
    private int cap;
    public LRUCache(int capacity) {
        hm = new HashMap<>();
        dummyhead = new Node(-1,-1);
        dummytail = new Node(-1,-1);
        dummyhead.next = dummytail;
        dummytail.prev = dummyhead;
        cap = capacity;
    }
    
    public int get(int key) {
        if(!hm.containsKey(key)) return -1;
        //Find node, make it head
        Node node = hm.get(key);
        removeNode(node);
        makeHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(hm.containsKey(key))
        {
           // Find Node, update value, make it head, update tail
           Node node = hm.get(key);
           node.val = value;
           removeNode(node);
           makeHead(node);
           
        }
        else
        {
           //check size
           //If size < capacity , create node & make it head, update tail
           if(hm.size()==cap)
           {
            hm.remove(dummytail.prev.key);
            removeNode(dummytail.prev);
            
           }
            Node node = new Node(key,value);
            makeHead(node);
            hm.put(key,node);
           // If size > capacity, remove tail node, create node & make it head, update tail
        }
    }

    private void makeHead(Node node)
    {
       node.prev = dummyhead;
       node.next = dummyhead.next;
       dummyhead.next.prev = node;
       dummyhead.next = node;
    }

    private void removeNode(Node node)
    {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }


}
