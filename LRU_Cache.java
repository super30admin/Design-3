//Time Complexity-O(n)
//Space Complexity-O(n)
class LRUCache {
public class Node{
    Node prev;
    Node next;
    int val;
    int key;
    Node(int key,int val)
    {
        this.key=key;
        this.val=val;
        prev=null;
        next=null;
    }
}
    private int capacity;
    private int size;
    private HashMap<Integer,Node> map;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        this.capacity=capacity;
        map=new HashMap();
        head=new Node(0,0);
        tail=new Node(0,0);
        head.next=tail;
        tail.prev=head;
        size=0;
    }
    private void addNode(Node n)
    {
        Node temp=tail.prev;
        tail.prev=n;
        n.prev=temp;
        temp.next=n;
        n.next=tail;
    }
    private void removeNode(Node n)
    {
        Node before=n.prev;
        Node after=n.next;
        before.next=after;
        after.prev=before;        
    }
    private void update(Node n)
    {
        removeNode(n);
        addNode(n);
    }
    public int get(int key) {
        Node n=map.get(key);
        if(n==null)
        {
            return -1;
        }
        else{
             update(n);
        }
        return n.val;

    }
    
    public void put(int key, int value) {
        Node n=map.get(key);
        if(n==null)
        {
            n=new Node(key,value);
            addNode(n);
            map.put(key,n);
            size=size+1;
        }
        else{
            n.val=value;
            update(n);
        }
        if(size>capacity)
        {
            Node temp=head.next;
            removeNode(temp);
            map.remove(temp.key);
            size=size-1;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */