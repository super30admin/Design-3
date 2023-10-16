// Time Complexity : O(1) for add and delete operations and O(1) for get and put operations
// Space Complexity : O(C) where c is the capacity/limit of LRU cache
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

public class Problem2 {
    class ListNode{
        ListNode prev;
        ListNode next;
        int key;
        int val;

        public ListNode(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    class LRUCache {
        int capacity;
        HashMap<Integer, ListNode> map;
        ListNode head;
        ListNode tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            this.head = new ListNode(-1, -1);
            this.tail = new ListNode(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if(!map.containsKey(key)){
                return -1;
            }

            ListNode nodeToDelete = map.get(key);
            delete(nodeToDelete);
            add(nodeToDelete);

            return nodeToDelete.val;
        }

        public void put(int key, int value) {
            if(map.containsKey(key)){
                ListNode node = map.get(key);
                delete(node);
            }
            ListNode node = new ListNode(key, value);
            map.put(key, node);
            add(node);


            if(map.size() > capacity){
                ListNode deleteNode = head.next;
                delete(deleteNode);
                map.remove(deleteNode.key);
            }

        }

        public void add(ListNode node){
            ListNode prevNode = tail.prev;
            prevNode.next = node;
            node.prev = prevNode;
            node.next = tail;
            tail.prev = node;
        }

        public void delete(ListNode node){
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }
    }
}
