//Time Complexity=O(1)
//Space Complexity=O(c), c = capacity of cache
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class LRUCache {
    class Node{
        int val;
        int key;
        Node prev, next;
        public Node(int key,int val)
        {
            this.key=key;
            this.val=val;
        }
    }
    Node head=new Node(-1,-1); //Creating a head node
    Node tail=new Node(-1,-1); //Creating a tail node
    private void removeNode(Node node) //Removing a specified node by connecting its previous and next nodes
    {
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }
    
    //Add a node to the head by linking head to the curr node, curr node node to the node after head, also linking the node after head to curr and setting previous of curr to head
    private void addTohead(Node node)
    {
        node.next=head.next;
        node.prev=head;
        head.next=node;
        node.next.prev=node;
    }
    
    HashMap<Integer,Node> map;
    int capacity; //Defining the capacity of or cache which can be accessed across the entire class
    public LRUCache(int capacity) {
        this.capacity=capacity;
        map=new HashMap<>();
        head.next=tail;
        tail.prev=head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1; //Checking whether key is in the hash map if it is then return -1.
        
        Node node=map.get(key);//If key is found then removing it from its location and placing it 
        removeNode(node);      //ahead in linked list and returning its value
        addTohead(node);
        return node.val;
        
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) //Checking if element already in hashmap, if it is then update its 
        {                        //value to new value and place it at start of linked list
            Node node=map.get(key);
            node.val=value;
            removeNode(node); 
            addTohead(node);
        }
        //To add element we have to check whether we aren't exceding our capacity, if we are then remove the least used element or the last element(i.e element before the tail), then add the new node to map
        else 
        {
            if(capacity==map.size())
            {
                Node last=tail.prev;
                removeNode(last);
                map.remove(last.key);
            }
            Node newn=new Node(key,value);
            map.put(key,newn);
            addTohead(newn);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */