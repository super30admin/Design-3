//TC: O(1) - put, O(1) - get
//SC: O(Capacity) for hashMap
//Executed on leetcode
class LRUCache {
    
    class Node
    {
        int key, value;
        Node prev, next;
        public Node(int key, int value)
        {
            this.key = key;
            this.value = value;
            prev = null;
            next = null;
        }
    }
    Node head, tail;
    int size , capacity;			//Adding every key to doubly linked list. one side being head and other side being tail.
    HashMap<Integer, Node> hashMap;	//Head node will be treated as last visit and tail node will be treated as recent.
    public LRUCache(int capacity) {	//Using hashMap to store key and respective node.
        hashMap = new HashMap<>();	//On get, if the key is available in hashMap getting node from hashMap and moving the node to tail
									//As now get element will be the most recent value.
        this.capacity = capacity;	//On put node will be added at the tail and key and node value will be pushed to hashMap
        size = 0;					//If size of the values in list are more than capacity, removing the last recent node that is at head
        head = new Node(0,0);
        tail = new Node(0,0);
        
        head.next = tail;
        tail.prev = head;
    }
    
    public void add(Node node)
    {
        Node temp = tail.prev;
        temp.next = node;
        node.next = tail;
        tail.prev = node;
        node.prev = temp;
    }
    
    public void remove(Node node)
    {
        Node before = node.prev;
        Node after = node.next;
        before.next = after;
        after.prev = before;
    }
    
    public void update(Node node)
    {        
        remove(node);
        add(node);
    }
    
    public int get(int key) {
        Node curr = hashMap.get(key);
        
        if(curr==null)
            return -1;
        
        update(curr);
        
        
        return curr.value;
    }
    
    public void put(int key, int value) {
        Node curr = hashMap.get(key);
        if(curr==null)
        {
            curr = new Node(key,value);
            hashMap.put(key,curr);
            size++;
            
            add(curr);
        }
        else
        {
            update(curr);
            curr.value = value;
        }
        if(size>capacity)
        {                       
            hashMap.remove(head.next.key);
            remove(head.next); 
            size--;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */