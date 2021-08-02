/*Time Complexity : 
get operation: O(1)
put operation: O(1)

Space Complexity : 
get operation: O(capacity)
put operation: O(capacity)

Did this code successfully run on Leetcode : Yes
*/

class LRUCache {
    class ListNode {
        int key, val;
        ListNode prev, next;
        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    Map<Integer, ListNode> map;
    int capacity;
    ListNode head, tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    public void removeNode(ListNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev; 
    }
    public void addToHead(ListNode node) {
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        else {
            ListNode node = map.get(key);
            removeNode(node);
            addToHead(node);
            return node.val;
        }
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            ListNode node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }
        else{
            if(map.size() == capacity) {
                ListNode rmNode = tail.prev;
                removeNode(rmNode);
                map.remove(rmNode.key);
            }
            ListNode newNode = new ListNode(key, value);
            map.put(key, newNode);
            addToHead(newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */