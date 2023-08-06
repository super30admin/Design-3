import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    //Time complexity: O(1)
    //Space Complexity: O(1)

    class ListNode{
        int key;
        int val;
        ListNode next;
        ListNode prev;
        ListNode(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    ListNode head;
    ListNode tail;
    int capacity;
    Map<Integer, ListNode> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = tail = null;
        map = new HashMap<>();
    }

    public int get(int key) {

        if(!map.containsKey(key)){
            return -1;
        }
        ListNode curr = map.get(key);
        if(curr == tail){
            return curr.val;
        }
        else if(curr == head){
            head = head.next;
            head.prev.next = null;
            head.prev = null;
        }
        else{
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            curr.prev = null;
            curr.next = null;
        }
        tail.next = curr;
        curr.prev = tail;
        tail = curr;

        return curr.val;

    }

    public void put(int key, int value) {
        if(get(key) != -1){
            tail.val = value;
            return;
        }

        ListNode curr = new ListNode(key, value);
        map.put(key, curr);
        if(head == null){
            head = curr;
            tail = curr;

        }else{
            tail.next = curr;
            curr.prev = tail;
            tail = curr;
        }


        if(map.size() > capacity){
            map.remove(head.key);
            head = head.next;
            head.prev.next = null;
            head.prev = null;
        }
    }
}
