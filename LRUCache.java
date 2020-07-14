class LRUCache {
    class Node {
        int key; int val;
        Node prev; Node next;
        public Node(int key,int val){
            this.key=key;
            this.val=val;            
        }
    }
    
    public void removeNode(Node node)
    {
        node.next.prev=node.prev;
        node.prev.next=node.next;
    }
    
    public void addRecentToHead(Node node)
    {
        node.next=head.next;
        node.prev=head;
        head.next=node;
        node.next.prev=node;
    }
    
    HashMap<Integer,Node> map;
    Node head; Node tail;
    int capacity;
    public LRUCache(int capacity) {
        head=new Node(-1,-1);
        tail=new Node(-1,-1);
        head.next=tail;
        tail.prev=head;
        map=new HashMap<>();
        this.capacity=capacity;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node temp=map.get(key);
        removeNode(temp);
        addRecentToHead(temp);
        return temp.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key))
        {
            Node temp=map.get(key);
            temp.val=value;
            removeNode(temp);
            addRecentToHead(temp);            
    
        }else
        {
            Node temp=new Node(key,value);
            if(capacity==map.size())
            {
                Node remove=tail.prev;
                removeNode(remove);
                map.remove(remove.key);
            }
            map.put(key,temp);
            addRecentToHead(temp);
            
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */