/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
          ListNode dummy = new ListNode(-1);    
           PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>((a,b)->a.val - b.val);
           for(ListNode head:lists){
               
               if(head!=null){
                    pq.add(head);
               }
           }
            ListNode result = dummy;
            
            while(!pq.isEmpty()){
                
                ListNode min = pq.poll();
                dummy.next = min;
                dummy = dummy.next;
                
                if(min.next!=null)
                    pq.add(min.next);
            }
            
            return result.next;
    }
}
