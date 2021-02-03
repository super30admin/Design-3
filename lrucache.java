import java.util.HashMap;

// Time Complexity :O(1)
// Space Complexity :O(c), where c is capacity
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :getting started

// Your code here along with comments explaining your approach
public class lrucache {

    class LRUCache {

        final Node head = new Node();
        final Node tail = new Node();
        HashMap<Integer, Node> hm;
        int cacheCapacity;

        public LRUCache(int capacity) {
            hm = new HashMap<>(capacity);
            this.cacheCapacity = capacity;
            head.next = tail;
            tail.prev = head;

        }

        public int get(int key) {
            int result =-1;
            Node node = hm.get(key);
            if(node != null){
                result = node.val;
                remove(node);
                add(node);
            }
            return result;
        }

        //add or override
        public void put(int key, int value) {
            Node node = hm.get(key);
            if(node != null){
                node.val = value;
                remove(node);
                add(node);
            }else{
                if(hm.size()==cacheCapacity){
                    hm.remove(tail.prev.key);
                    remove(tail.prev);
                }
                Node newNode = new Node();
                newNode.key = key;
                newNode.val = value;

                hm.put(key, newNode);
                add(newNode);
            }
        }

        //add nodes to front of double linked list
        public void add(Node node){
            Node headNext = head.next;
            node.next = headNext;
            headNext.prev = node;
            head.next = node;
            node.prev = head;
        }

        //remove node from back of double linked list
        public void remove(Node node){
            Node nextNode = node.next;
            Node prevNode = node.prev;

            nextNode.prev = prevNode;
            prevNode.next = nextNode;
        }
    }

    class Node{
        int key;
        int val;
        Node prev;
        Node next;
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


}
