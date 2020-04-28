// 146.
//time - O(1) for both put() and get()
//space - O(n) where n is number of key value pairs

class ListNode {
    int key;
    int value;
    ListNode prev;
    ListNode next;
    
    public ListNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache {
    
    ListNode head;
    ListNode tail;
    HashMap<Integer, ListNode> support;
    int capacity;

    public LRUCache(int capacity) {
        this.head = new ListNode(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.tail = new ListNode(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.support = new HashMap<>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(support.containsKey(key))
        {
            ListNode current = support.get(key); //get the node
            deleteNode(current);
            addToHead(current); //remove from that position and add to head
            return current.value;
        }
        return -1; //key error
    }
    
    public void put(int key, int value) {
        if(!support.containsKey(key)) //new key to be inserted
        {
            ListNode current = new ListNode(key, value); //new node for the given k-v pair
            if(support.size() < capacity) //no overflow
            {
                addToHead(current); //add to head
            }
            else
            {
                support.remove(tail.prev.key); //update map
                deleteNode(tail.prev); //delete the last node
                addToHead(current); //add to head
            }
            support.put(key, current); //map update
        }
        else //update exisiting key
        {
            ListNode current = support.get(key);
            current.value = value; //update the val of that node
            //alternatively delete that node and add new node of new value
            deleteNode(current); //remove from its position and add to head
            addToHead(current);
        }
        return;
    }
    
    private void deleteNode(ListNode temp) {
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        return;
    }
    
    private void addToHead(ListNode temp) {
        temp.next = head.next;
        temp.prev = head;
        head.next = temp;
        temp.next.prev = temp;
        return;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
