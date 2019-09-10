class Node // Doubly linked list node.
{
int key;
    int value;
    Node prev;
    Node next;
    public Node(int key,int val)
    {
        this.key=key;
        this.value=val;
    }
}

class LRUCache {
    int capacity;
    int count = 0;  
    Node head;
    Node tail ;
    HashMap<Integer, Node>map;
    public LRUCache(int capacity)  // Create a constructor to intialise variables.
    {
        this.capacity=capacity;
        head = new Node(0,0);   // Create dummy head and tail nodes.
        tail = new Node(0,0);
        map = new HashMap<>(); // Create a new hashmap.
        head.next=tail;
        tail.prev=head;
        head.prev= null;
        tail.next=null;
    }
    public void addToHead(Node node)
    {
        node.next=head.next;
        head.next=node;
        node.prev=head;
        node.next.prev=node;
    }
    
    public void removeNode(Node node)
    {
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }
    public int get(int key) {
        
        if(map.containsKey(key)){
            Node temp = map.get(key);
            removeNode(temp);
            addToHead(temp);
            return temp.value;
        }
            
        return -1;
        
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key))
        {
            Node temp = map.get(key);
            temp.value=value;   // If the key is already there in HashMap, Just update the temp value. 
            removeNode(temp);   // remove node and then add to head.
            addToHead(temp);
            
        }
        else
        {
            Node newNode= new Node(key,value); // If key not in hashmap, create it and put it in HashMap.
            map.put(key,newNode);
            if(count<capacity) // If capacity not yet reached, add the node to head.
            {   
                addToHead(newNode);
                count++; 
            }
            else    // If max capacity reached.
            {
                Node tailPrev = tail.prev;
                map.remove(key,tailPrev); // remove the last node from the Linked List and remove key from map.
               removeNode(tailPrev); // remove the node from linked list.
                addToHead(newNode); // add new node to head of linked list.
            }
        }
        
        
    }
}
