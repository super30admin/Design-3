
//Time complexity: O(nlogk) where n is the total elements and k is the number of lists
//Space complexity: O(k) for the heap


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */


class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue(new pqComp());
        
        //get heap ready
        //first el of k lists to heap
        for(ListNode list : lists){
            if(list != null)
                minHeap.add(list);
        }
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        
        while(!minHeap.isEmpty()){
            ListNode top = minHeap.remove();
            if(top.next != null){
                minHeap.add(top.next);
            }
            curr.next = top;
            curr=top;
        }
        
        return dummy.next;
        
    }
    
    public class pqComp implements Comparator<ListNode> {
        public int compare(ListNode l1, ListNode l2){
            if(l1.val > l2.val){
                return 1;
            }
            else if(l1.val < l2.val){
                return -1;
            }
            else{
                return 0;
            }
        }
    }
}