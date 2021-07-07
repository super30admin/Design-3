
//Time Complexity: O(1)
//Space Complexity: O(1)


class LRUCache {
    int capacity;
    int count = 0;

    ListNode head;
    ListNode tail;
    
    ConcurrentHashMap<Integer, ListNode> map;
            private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new ConcurrentHashMap<Integer, ListNode>();
        head = new ListNode(-1,-1, null ,null);
        tail = new ListNode(-1,-1, null ,null);
        head.next = tail;
        tail.prev = head;
    }
    
    public synchronized int get(int key) {
        
        if(map.containsKey(key)){
            ListNode node = map.get(key);
            node.moveNode(head);
            return node.val;
        }else{
            return -1;
        }

    }
    
    public synchronized void put(int key, int value) {
        ListNode newNode = new ListNode(key, value, null, null);
        if(map.containsKey(key)){
            ListNode replacedNode = map.get(key);
            replacedNode.val = value;
            replacedNode.moveNode(head);

        }else{
            newNode.addNode(head);
            map.put(key,newNode);
            count++;
            
            if(count > capacity){
                count--;
                int deletedKey = newNode.removeNode(tail);
                map.remove(deletedKey);
            }
        }
    }
    
    class ListNode{
        public ListNode prev;
        public ListNode next;
        public int val;
        public int key;

        public ListNode(int key, int val,ListNode next,ListNode prev){
            this.key = key;
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
        
        public void addNode( ListNode head){
            
            ListNode nextNode = head.next;
            this.next = nextNode;
            nextNode.prev = this;
            this.prev = head;
            head.next = this;
        }
        
        public int removeNode(ListNode tail){
            ListNode prevNode = tail.prev.prev;
            ListNode removeNode = prevNode.next;
            int deletedKey = removeNode.key;
            prevNode.next = tail;
            tail.prev = prevNode;
            return deletedKey;
                
        }
        
        public void moveNode(ListNode head){
            ListNode prevNode = this.prev;
            prevNode.next = this.next;
            this.next.prev = prevNode;
            addNode(head);

        }
    }
}
