class LRUCache {

    HashMap<Integer,ListNode> map;
    ListNode head;
    ListNode tail;
    int capacity;

    public LRUCache(int capacity) {

        this.capacity = capacity;
        map = new HashMap<>();
        head = new ListNode(-1,-1);
        tail = new ListNode(-1,-1);
        head.next = tail;
        tail.prev = head;

    }

    public int get(int key) {

        if(!map.containsKey(key)) return -1;
        else
        {
            ListNode curr = map.get(key);
            remove(curr);
            addtohead(curr);
            return curr.val;
        }

    }

    public void put(int key, int value) {

        if(map.containsKey(key))
        {
            ListNode curr = map.get(key);
            curr.val = value;
            remove(curr);
            addtohead(curr);
        }
        else
        {
            if(map.size() == capacity)
            {
                ListNode rmnode = tail.prev;
                remove(rmnode);
                map.remove(rmnode.key);
            }

                ListNode newnode = new ListNode(key,value);
                map.put(key,newnode);
                addtohead(newnode);

        }

    }

    public void addtohead(ListNode node)
    {
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }

    public void remove(ListNode node)
    {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}

class ListNode
{
    int key,val;
    ListNode prev,next;

    public ListNode(int key , int val)
    {
        this.key = key;
        this.val = val;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
