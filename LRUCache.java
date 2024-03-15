// Time Complexity : O(1)
// Space Complexity :O(capacity)
// Did this code successfully run on Leetcode : yes
// Three line explanation of solution in plain english : We can do it with just LinkedList, but search is linear. So we optimize by
// using hashmap to store key and their references. Leading to O(1)



class LRUCache {
    class ListNode{
        int val;
        int key;
        ListNode next;
        ListNode prev;

        public ListNode(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    ListNode head; // MRU
    ListNode tail; // LRU
    int capacity;
    Map<Integer,ListNode> map;

    public LRUCache(int capacity) {
        this.head = new ListNode(-1, -1);
        this.tail = new ListNode(-1, -1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        map = new HashMap<>();
    }

    private void removeNode(ListNode node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    private void addNode(ListNode node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    public int get(int key) {
        
        if(!map.containsKey(key)) return -1;

        ListNode node = map.get(key);
        removeNode(node);
        addNode(node); // make it MRU

        return node.val;
    }
    
    public void put(int key, int value) {
        ListNode node;
        if(map.containsKey(key)){
            node = map.get(key);
            node.val = value;
            removeNode(node);
        }
        else{ // Fresh Node
            node = new ListNode(key, value);
            map.put(key, node);
            if(capacity < map.size()){ // cache is full
            // remove LRU
                map.remove(tail.prev.key);
                removeNode(tail.prev); 
                
            }
        }
        addNode(node); // make it MRU
        // System.out.println(map);
    }
    
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */