// Time Complexity : O(N) where N is the capacity (worst case, we need to iterate over LL to remove lru)
// Space Complexity : O(N) hashmap containing capacity number of nodes
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Your code here along with comments explaining your approach:
/*
Use DLL + HashMap for this design
GET method- if result is present then return value and also, remove the node and add it to front, else return -1
PUT method- if key is present, then update value and move node to front, update hashMap.
else check capacity, if under capacity, add to front
else if over capacity then remove the tail.prev node and add new node to front. Also remove from hashMap
*/
public class LRUCache {
    class ListNode{
        int value;
        int key;
        ListNode next;
        ListNode prev;
    }

    HashMap<Integer, ListNode> hm;
    int capacity;
    ListNode head;
    ListNode tail;
    public LRUCache(int capacity) {
        hm= new HashMap<>();
        this.capacity=capacity;
        head= new ListNode();
        tail=new ListNode();
        head.next=tail;
        tail.prev=head;
    }

    public int get(int key) {
        if(hm.containsKey(key)){
            ListNode node= hm.get(key);
            removeNode(node);
            addNode(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(hm.containsKey(key)){
            ListNode node= hm.get(key);
            node.value=value;
            hm.put(key,node);
            removeNode(node);
            addNode(node);
        }else{
            ListNode node= new ListNode();
            node.key=key;
            node.value=value;
            if(hm.size()==capacity){
                //make it head
                ListNode removeNode=tail.prev;
                removeNode(removeNode);
                hm.remove(removeNode.key);

                hm.put(key,node);
                addNode(node);
            }else{
                addNode(node);
                hm.put(key,node);
            }
        }
    }

    private void addNode(ListNode node){
        node.next=head.next;
        head.next.prev=node;
        head.next=node;
        node.prev=head;
    }

    private void removeNode(ListNode node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
        node.prev=null;
        node.next=null;
    }
}
