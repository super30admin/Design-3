// Time Complexity : O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None

public class LRUCache {class LRUCache {

    class ListNode{
        int key;
        int val;
        ListNode prev;
        ListNode next;

        public ListNode(int key, int val){
            this.key = key;
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }

    private ListNode head;
    private ListNode tail;

    Map<Integer, ListNode> map;
    int maxSize;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.head = new ListNode(-1, -1);
        this.tail = new ListNode(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.maxSize = capacity;
    }

    public int get(int key) {
        if(map.containsKey(key))
        {
            ListNode curNode = map.get(key);
            //remove from its place
            removeNode(curNode);
            //push it to the front;
            addToHead(curNode);

            return curNode.val;
        }
        else
            return -1;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            //update val
            ListNode curNode = map.get(key);
            curNode.val = value;

            //remove from its place
            removeNode(curNode);
            //push it to the front;
            addToHead(curNode);
        }
        else{
            if(map.size() != maxSize){
                ListNode newNode = new ListNode(key, value);
                map.put(key, newNode);
                addToHead(newNode);
            }
            else{
                //remove last node
                ListNode lastNode = tail.prev;
                removeNode(lastNode);
                map.remove(lastNode.key);

                //add new node to head
                ListNode newNode = new ListNode(key, value);
                map.put(key, newNode);
                addToHead(newNode);
            }
        }
    }

    private void addToHead(ListNode node){
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }

    private void removeNode(ListNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
}
