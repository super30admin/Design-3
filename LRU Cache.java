// Time Complexity : O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Your code here along with comments explaining your approach

//We will use a LinkedList to maintain the sequence of the cache. The most recently used will be close to the head and the least recently used to the tail. 
//We will use a hashmap to optimize the searching process of nodes to get and put the cache. The key in the hashmap would be the the key given as input and t
//he value would be the reference to the node in the linked list


class LRUCache {

    class Node
    {
        int key;
        int val;
        Node next;
        Node prev;
        public Node(int key, int val)
        {
            this.key=key;
            this.val=val;
        }
    }

    HashMap<Integer,Node> map;
    int capacity;
    Node head;
    Node tail;

    public LRUCache(int capacity) 
    {
        this.map=new HashMap<>();
        this.capacity=capacity;
        this.head=new Node(-1,-1);
        this.tail=new Node(-1,-1);
        head.next=tail; //Head points at the tail in the beginning
        tail.prev=head; //Tail points at the head in the beginning
    }

    private void addToHead(Node node)
    {
       node.next=head.next;
       node.prev=head;
       head.next=node;
       node.next.prev=node;

    }

    private void removeNode(Node node)
    {
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }
    
    public int get(int key) 
    {
        //if the map doesn't contain the key, then return -1
        if(!map.containsKey(key)) return -1;
        Node node=map.get(key); //First we need to check the hashmap to get the node
        removeNode(node);  //Remove the Value i.e the node from the linked list
        addToHead(node);   //Add it to the head
        return node.val;   //return the value of the node
    }
    
    public void put(int key, int value) 
    {
        //If the key exists in the HashMap
        if(map.containsKey(key))
        {   Node node= map.get(key);  //Get the key from the hashmap
            node.val=value;           //Update the value in the node
            removeNode(node);         //remove it from where it is
            addToHead(node);          //add it to the head
        }

        //If the key doesn't exist in the hashmap
        else
        {
            //If there is no cache capacity
            if(capacity==map.size())
            {
                //Remove LRU from the LinkedList
                Node tailPrev=tail.prev;            //LRU if the node previous to the tail
                removeNode(tailPrev);               //remove it from where it is
                map.remove(tailPrev.key);           //Remove it from the hashmap
            }
            
            Node newNode=new Node(key,value);       //create the new node
            addToHead(newNode);                     //add it to the head of the list
            map.put(key,newNode);                   //add it to the hashmap
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */