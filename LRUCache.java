// Time Complexity : O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


/* 
 * as we can see int the question, we are required to use a key and value pair , so we should use a map.
 * we need maintain a data structure to arrange the keys  in the order of their usage. 
 * we cant use a queue because while it requires us to travers till we find the key incase of update activity. 
 * 1. Create a doubly linked list and a hashmap. 
 * 2. The doubly linked list will be used to maintain the order of the elements in the cache.
 * 3. The hashmap will be used to store the key and the node in the doubly linked list.
 * 4. The head of the doubly linked list will be the most recently used element and the tail will be the least recently used element.
 * 5. When a get operation is performed, the node is removed from its current position in the doubly linked list and added to the head of the doubly linked list.
 * 6. When a put operation is performed, 
 *  if the key is already present in the hashmap, value is updated and the node is removed from its current position in the doubly linked list and added to the head of the doubly linked list.
 *  if the key is not present in the hashmap, a new node is created and added to the head of the doubly linked list.
 * 7. If the capacity of the cache is reached, the node at the tail of the doubly linked list is removed and the key is removed from the hashmap.
 */

import java.util.HashMap;

class LRUCache {
    class ListNode {
        int key, value;
        ListNode prev, next;        
        public ListNode(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    HashMap<Integer, ListNode> map;
    private ListNode head, tail;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new ListNode(-1, -1);
        this.tail = new ListNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    private void addToHead(ListNode node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    private void removeNode(ListNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        ListNode node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            ListNode currNode = map.get(key);
            currNode.value = value;
            removeNode(currNode);
            addToHead(currNode);
        } else {
            if (capacity == map.size()) {
                ListNode prev = tail.prev;
                removeNode(prev);
                int _key = prev.key;
                map.remove(_key);
            }

            ListNode node = new ListNode(key, value);
            addToHead(node);
            map.put(key, node); // Add the new node to the map
        }
    }

}