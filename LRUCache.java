// Time Complexity : get: O(1), put O(1)
// Space Complexity : O(N) where N is size of cache
// Did this code successfully run on Leetcode : Yes



class LRUCache {
    HashMap<Integer, ListNode> map;
    ListNode head; ListNode tail;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new ListNode(-1,-1);
        this.tail = new ListNode(-1,-1);
        head.next = tail;
        tail.prev = head;
        this.map = new HashMap();
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        ListNode node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            ListNode node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }
        else{
            if(capacity == map.size()){
                //remove node which is least recently used
                ListNode tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
        
            ListNode newNode = new ListNode(key, value);
            addToHead(newNode);
            map.put(key,newNode);
        }
    }
    private void removeNode(ListNode node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    private void addToHead(ListNode node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    class ListNode{
        int key;
        int val;
        ListNode prev;
        ListNode next;

        public ListNode(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
}
