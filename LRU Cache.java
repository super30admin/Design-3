//Time complexity: O(1)
//Space complexity: O(capacity)

class LRUCache {

    class Node
    {
        int key;
        int val;
        Node next;
        Node prev;
        public Node(int key, int val)
        {
            this.key=key;
            this.val=val;
        }
        
    }
    private  void addtoHead(Node node)
    {
        node.next=head.next;
        node.prev=head;
        head.next=node;
        
        node.next.prev=node;
    }
    private  void removeNode(Node node)
    {
        node.next.prev=node.prev;
        node.prev.next=node.next;
    }
    int capacity;
    Node head;
    Node tail;
    HashMap<Integer, Node>map;
    public LRUCache(int capacity) {
        this.capacity=capacity;
        this.map=new HashMap();
        this.head=new Node(-1,-1);
        this.tail=new Node(-1,-1);
        head.next=tail;
        tail.prev=head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        Node node= map.get(key);
        removeNode(node);
        addtoHead(node);//most recently used added to head
        
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key))
        {
            Node node= map.get(key);
            node.val=value;
            removeNode(node);
            addtoHead(node);
        }
        else
        {
            if(capacity==map.size())
            {
                Node tailprev=tail.prev;
                removeNode(tailprev);
                map.remove(tailprev.key);
                
                
            }
            Node newNode= new Node(key,value);
                addtoHead(newNode);
                map.put(key,newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */