/**LC-146
 * 
 * Time Complexity : O(1)
 * Space Complexity : O(capacity)  capacity of the LRUCache
 * Did this code successfully run on Leetcode : Yes
 * Any problem you faced while coding this : Yes
 * 
 * Did not add key attribute in ListNode. This gave me error in line 82, where I needed the key at which I needed to remove/
 * If key is not storedn in the ListNode and I only store value, then retrieving key from value would require me to use entryset and that would increase the time complexity.
 * So it is better to add key also in the ListNode. 
 
 */
class LRUCache {

    HashMap<Integer, ListNode> map = new HashMap<>();
    ListNode head = new ListNode(0,0);
    ListNode tail  = new ListNode(0,0);
    int capacity = 0;
    class ListNode{
        
        int val;
        int key;
        ListNode prev;
        ListNode next;
        public ListNode(int key, int val){
            this.val = val;
            this.key = key;
        }
    }
    
    public LRUCache(int capacity) {
        
        HashMap<Integer, ListNode> map = new HashMap<>();
        head.next = tail;
        tail.prev = head;
        tail.next = null;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        
        if(map.containsKey(key)){
            
            ListNode curr = map.get(key);
             //removing the node from the current position
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            
            
            //placing at head position
            curr.next = head.next;
            curr.prev = head;
            curr.next.prev = curr;
            head.next = curr;
            return curr.val;
        }
        
        return -1;
        
    }
    
    public void put(int key, int value) {
        
        if(map.containsKey(key)){
            ListNode curr = map.get(key);
            
            //removing the node from the current position
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            curr.val = value;
            curr.key = key;
            //placing at head position
            curr.next = head.next;
            curr.prev = head;
            curr.next.prev = curr;
            head.next = curr;  
        }
        else{
            ListNode newNode = new ListNode(key, value);
            if(map.size() >= capacity){
            
                ListNode nodeToBeRemoved = tail.prev;  //node to be removed
                int k = nodeToBeRemoved.key;
                if(map.containsKey(k)){
                  
                    map.remove(k);
                }
                nodeToBeRemoved.prev.next = tail;
                tail.prev = nodeToBeRemoved.prev; 
            }
            
            //adding new node in the hashmap and in the list
            map.put(key, newNode);
            newNode.next = head.next;   //placing the node at the head position
            head.next.prev = newNode;
            newNode.prev = head;
            head.next = newNode;
            
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */