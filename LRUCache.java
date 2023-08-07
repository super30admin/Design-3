import java.util.HashMap;
import java.util.Map;

//TC:get and put operations O(1)
//SC: O(n)
class LRUCache {

    class Node
    {
        //use doubly LL
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
    //Declare 2 pointers
    Node head;
    Node tail;
    int capacity;
    Map<Integer,Node> hmap;

    public LRUCache(int capacity) {

        this.capacity=capacity;
        head=new Node(-1,-1);//dummy Node
        tail=new Node(-1,-1);//dummy node
        hmap=new HashMap<>();
        head.next=tail;
        tail.prev=head;

    }

    public int get(int key) {

        if(!hmap.containsKey(key))
        {
            return -1;
        }
        else
        //since we visisted this,this becomes most recently visited key
        {
            Node curr=hmap.get(key);
            remove(curr);
            addToFront(curr);
            return curr.val;
        }
    }

    public void put(int key, int value) {

        //if node alreday present
        if(hmap.containsKey(key))
        {
            Node curr=hmap.get(key);
            curr.val=value;
            remove(curr);
            addToFront(curr);
        }
        else
        {
            //size of cache is full
            if(hmap.size()>=capacity)
            {
                Node tailprev=tail.prev;
                remove(tailprev);
                //remove node from map
                hmap.remove(tailprev.key);

            }
            //add fresh node to start of list
            Node curr=new Node(key,value);
            addToFront(curr);
            //add node and key to map
            hmap.put(curr.key,curr);

        }
    }
    private void addToFront(Node curr) //TC O(1)
    {
        //resolve curr node link first
        curr.next=head.next;
        curr.prev=head;
        head.next=curr;
        curr.next.prev=curr;
    }
    public void remove(Node curr)
    {
        //resolve curr node link first
        curr.prev.next=curr.next;
        curr.next.prev=curr.prev;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */