// Time Complexity:O(1)
// Space Complexity: O(n)
// Did this code successfully run on Leetcode: Yes
// Any problem you faced while coding this:No


// Your code here along with comments explaining your approach
/*
 * Solve hurdles one by one.
 * We need a DS that is ordered to maintain order. We chose a doubly linked list. To get O(1) time complexity
 * for get and put operations we use a HashMap with key to node mapping. The node mapping is required because we do not 
 * want to iterate over LL to remove and put it at the front.
 */

public class LRUcache {
       
    class Node{
        int key;
        int val;
        Node prev;
        Node next;
        public Node(int key,int val)
        {
            this.key = key;
            this.val = val;
        }
    }
    
    public void removeNode(Node node)
    {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    public void addtoHead(Node node)
    {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    
    Node head;
    Node tail;
    HashMap<Integer,Node> map;
    int capacity;
    public LRUCache(int capacity) {
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.capacity = capacity;
        this.map = new HashMap<>();
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        Node temp = map.get(key);
        removeNode(temp);
        addtoHead(temp);
        return temp.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key))
        {
            Node temp = map.get(key);
            temp.val = value;
            removeNode(temp);
            addtoHead(temp);
        }
        else
        {
            if(capacity == map.size())
            {
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key,value);
            addtoHead(newNode);
            map.put(key,newNode);
        }
    } 
}
