// Time Complexity : Get, Put O(1) 
// Space Complexity : O(Capacity)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

// Have a Doubly Linked List as a way of maintaing the cache. 
//Keep pushing the nodes on which get and put are obtained to the front (near head).
// Have a hashmap to maintain capacity. If new node is added keep , remove node from the end (tail side).

class LRUCache {
    
    class Node{
        int key;
        int value;
        Node prev,next;
        public Node(int key,int value)
        {
            this.key=key;
            this.value=value;
        }
    }
    
    Node head,tail;
    HashMap<Integer,Node> map;
    int capacity;
    
    public void removeNode(Node node)
    {
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }
    
    public void addNodeToHead(Node node)
    {
        node.next=head.next;
        node.next.prev=node;
        head.next=node;
        node.prev=head;     
    }

    public LRUCache(int capacity) {
        
    head=new Node(-1,-1);
    tail=new Node(-1,-1);
    head.next=tail;
    tail.prev=head;
    this.capacity=capacity;
    map=new HashMap<>();
        
    }
    
    public int get(int key) {
        
        if(!map.containsKey(key)) return -1;
        
        Node node=map.get(key);
        removeNode(node);
        addNodeToHead(node);
        
        return node.value;
        
    }
    
    public void put(int key, int value) {
        
        if(map.containsKey(key))
        {
            Node node=map.get(key);
            node.value=value;
            removeNode(node);
            addNodeToHead(node);
        }
        else {
         
            if(capacity==map.size())
            {
                Node curr=tail.prev;
                map.remove(curr.key);
                removeNode(curr);
            }
            
            Node newNode=new Node(key,value);
            addNodeToHead(newNode);
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