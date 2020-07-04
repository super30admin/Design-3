// time: O(1)
// space: O(n) n is sixe of capacity
class LRUCache {
    class Node{
        int key; 
        int val;
        Node next;
        Node prev;
        public Node(int key,int val)
        {
            this.key=key;
            this.val=val;
        }
    }
    HashMap<Integer,Node> map;
    Node head; 
    Node tail;
    int capacity;
    public LRUCache(int capacity) {
        this.map=new HashMap<>();
        this.head=new Node(-1,-1);
        this.tail=new Node(-1,-1);
        this.head.next=this.tail;
        this.tail.prev=this.head;
        this.capacity=capacity;
    }
    public void removeNode(Node node)
    {
        node.next.prev=node.prev;
        node.prev.next=node.next;
    }
    public void addTohead(Node node)
    {
        node.next=head.next;
        node.prev=head;
        head.next=node;
        node.next.prev=node;
    }
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node=map.get(key);
        removeNode(node);
        addTohead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key))
        {
            Node node =map.get(key);
            node.val=value;
            removeNode(node);
            addTohead(node);
        }
        else{
            Node newNode=new Node(key,value);
            if(map.size()<capacity)
            {
                addTohead(newNode);
                map.put(key,newNode);
            }
            else{
                Node leastUsed=tail.prev;
                removeNode(leastUsed);
                map.remove(leastUsed.key);
                addTohead(newNode);
                map.put(key,newNode);
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */