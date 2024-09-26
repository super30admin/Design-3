import java.util.*;

class LRUCache {
    public class node
    {
        int key,val;
        node(int key,int val)
        {
            this.key=key;
            this.val=val;
        }
        node prev,next;
    }
    HashMap<Integer,node> map;
    int capacity;
    node head;
    node tail;
    public LRUCache(int capacity) {
        this.capacity=capacity;
        this.head=new node(-1,-1);
        this.tail=new node(-1,-1);
        this.map=new HashMap<>();
        this.head.next=this.tail;
        this.tail.prev=this.head;
    }
    public void addToHead(node n)
    {
        n.next=head.next;
        n.prev=head;
        head.next=n;
        n.next.prev=n;
    }
    public void deleteNode(node n)
    {
        n.next.prev=n.prev;
        n.prev.next=n.next;
        // node.prev=null;
        // node.next=null;
    }
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        else
        {
            node temp=map.get(key);
            deleteNode(temp);
            addToHead(temp);
            return temp.val; 
        }
    }
    public void put(int key, int value) {
        if(map.containsKey(key))
        {
            node temp=map.get(key);
            temp.val=value;
            deleteNode(temp);
            addToHead(temp);
        }
        else
        {
            node data=new node(key,value);
            if(map.size()<capacity)
            {
                addToHead(data);
            }
            else
            {
                node leastUsed=tail.prev;
                deleteNode(leastUsed);
                //System.out.println(leastUsed.val+" "+data.val);
                map.remove(leastUsed.key);
                addToHead(data);
            }
            map.put(key,data);
        }
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */