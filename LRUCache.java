/**
Time Complexity : O(1)
Space Complexity : O(N)
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No
*/
class LRUCache 
{
    int capacity;
    DoublyLinkedList list;
    Map<Integer, DoublyListNode> map;
    
    private final int NOT_FOUND = -1;

    public LRUCache(int capacity) 
    {
        this.capacity = capacity;
        this.list = new DoublyLinkedList();
        this.map = new HashMap<>();
    }
    
    public int get(int key) 
    {
        if(!map.containsKey(key))
            return NOT_FOUND;
        
        // Get the node from the map
        DoublyListNode node = map.get(key);
        
        // Delete the node from the list
        list.deleteNode(node);
        
        // Add the node to the list
        list.addNode(node);
        
        return node.value;
    }
    
    public void put(int key, int value) 
    {
        if(!map.containsKey(key))
        {
            int currentSize = map.size();
            if(this.capacity == currentSize)
            {
                DoublyListNode tail = list.tail;
                list.deleteNode(tail);  
                map.remove(tail.key);
            }
            
            list.addNode(key, value);
            DoublyListNode head = list.head;
            map.put(key, head);

        }
        else
        {
            get(key);
            DoublyListNode node = map.get(key);
            node.value = value;
        }
    }
}

class DoublyLinkedList
{
    DoublyListNode head;
    DoublyListNode tail;
    
    public DoublyLinkedList()
    {}
    
    public void addNode(int key, int value)
    {
        DoublyListNode newnode = new DoublyListNode(key, value);
        newnode.next = head;
        newnode.prev = null;
        
        if(head == null)
        {
            head = newnode;
            tail = newnode;
            return;
        }
        
        head.prev = newnode;
        head = newnode;
    }
    
    public void addNode(DoublyListNode newnode)
    {
        newnode.next = head;
        newnode.prev = null;
        
        if(head == null)
        {
            head = newnode;
            tail = newnode;
            return;
        }
        
        head.prev = newnode;
        head = newnode;
    }
    
    public void deleteNode(DoublyListNode node)
    {
        if(head == tail && node == head)
        {
            head = null;
            tail = null;
        }
        else if(node == head)
        {
            head = head.next;
        }
        else if(node == tail)
        {
            tail = node.prev;
            node.prev.next = null;
        }
        else
        {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }
    
}

class DoublyListNode
{
    int key;
    int value;
    DoublyListNode prev;
    DoublyListNode next;
    
    public DoublyListNode(int key, int value)
    {
        this.key = key;
        this.value = value;
    }
}