// Space Complexity : O(n) where n is the capacity of the Cache
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
class LRUCache {
    
    class Node{
        
        int key;
        int value;
        Node next;
        Node prev;
        public Node(int key, int value )
        {
            this.key = key;
            this.value = value;
        }
    }
    
    private void addNode(Node node)
    {
        //System.out.println("Here");
        Node temp = head.next;
        head.next = node;
        temp.prev = node;
        node.next = temp;
        node.prev = head;
    }
    
    private void moveNodeToHead(Node node) //O(1)
    {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        addNode(node);
    }
    
    private Node removeNodeFromTail() // O(1)
    {    
        Node node = tail.prev;
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
        return node;
    }
    
    
    Node head;
    Node tail;
    HashMap<Integer,Node> map = new HashMap<>();
    int size;
    int capacity;
    public LRUCache(int capacity) {        
        size = 0;
        head=new Node(-1,-1);
        tail= new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }
    
    public int get(int key) //O(1) 
    {
        //System.out.println(map);
        if(map.containsKey(key))
        {
            Node node = map.get(key);
            moveNodeToHead(node);
            //System.out.println(node.value);
            return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) // O(1)
    {
        //System.out.println(map);
        if(map.containsKey(key))
        {
            Node node = map.get(key);
            node.value = value;
            moveNodeToHead(node);
        }else{
            //System.out.println("Here");
            Node node = new Node(key,value);
            map.put(key,node);
            addNode(node);
            size++;
        }
        
        if(size>capacity)
        {
           Node node = removeNodeFromTail();
           map.remove(node.key);
           size--;
        }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
