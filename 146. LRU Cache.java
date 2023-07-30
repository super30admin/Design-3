// Time Complexity : get(key): O(1)  ,put(key,value):O(1)
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach:
// we take a linked list with one side being most recently used and the other least recently used but to remove a particular Node given the key
// or to update the value we need to traverse throught the linked list which will be O(n) so we use a hashmap if we store key value pair in the 
// hmap it doesn't solve our issue. So we will be using key : Node pair in hashMap because when we want to update the value. 
// we can find the corresponding address and remove the node in O(1) so we need a doubly linked list.

// get function :- if the key is not present in the hamp we return -1 . if it present we remove the node from that position 
// and add it to the most frequently used side and return the value

// put function:- if the key is already present in the hmap then we go that location, update the value, remove the node and add it to the MRU side
// if it is not present we check the capacity . if capacity is full we remove the RFU node and add the new node
class LRUCache {
    class Node {
        int key;
        int val;
        Node prev;
        Node next;
        public Node(int key, int val)
        {
            this.key=key;
            this.val=val;
            
        }
    }
    private Node head;
    private Node tail;
    private int capacity;
    private HashMap<Integer, Node> map;
    public LRUCache(int capacity) {
        this.head = new Node (-1,-1);
        this.tail = new Node (-1,-1);
        this.head.next= this.tail;
        this.tail.prev = this.head;
        this.map = new HashMap<>();
        this.capacity = capacity;
    }

    private void removeNode(Node curr)
    {
        curr.next.prev = curr.prev;
        curr.prev.next = curr.next;
    }

    private void addToHead(Node curr)
    {
        curr.next = head.next;
        curr.prev = head;
        head.next= curr;
        curr.next.prev = curr;
    }   

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key))
        {
            //update the val;
            Node node = map.get(key);
            node.val=value;
            removeNode(node);
            addToHead(node);
        }else {
            if(map.size()==capacity)
            {
                Node toremove = tail.prev;
                removeNode(toremove);
                map.remove(toremove.key);
            }

            Node newNode = new Node(key,value);
            addToHead(newNode);
            map.put(key,newNode);
        }
    }
}

/**
 * Your LRUCache object will be insta
 ntiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */