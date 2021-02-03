Time Complexity: O(1) L is the list
Space Complexity: o(n) Number of nodes
class LRUCache {
    class ListNode{
        ListNode next;
        ListNode prev;
        int key;
        int value;
        public ListNode(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    int cap;
    int capacity;
    ListNode head;
    ListNode tail;
    HashMap<Integer, ListNode> map;
    public LRUCache(int capacity) {
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        map = new HashMap<>();
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }
    private void addToHead(ListNode node)
    {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    private void removeNode(ListNode node)
    {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    public int get(int key) {
        if(map.containsKey(key))
        {
            ListNode val = map.get(key);
            removeNode(val);
            addToHead(val);
            return val.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key))
        {
            ListNode val = map.get(key);
            val.value = value;
            removeNode(val);
            addToHead(val);
        }
        else
        {
            ListNode nw = new ListNode(key,value);
            if(capacity == map.size())
            {
               ListNode end = tail.prev;
               map.remove(end.key);
               removeNode(end);
            }
            map.put(key, nw);
            addToHead(nw);
        }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */