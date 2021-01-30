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
    public boolean isPalindrome(ListNode head) {
        if(null == head)
            return true;
        
        ListNode temp = head;
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
       
        
       ListNode reversed =  reverse(slow.next);
        ListNode temp1 = reversed;
        while(temp!=null && temp1!=null){
            if(temp.val!=temp1.val)
                return false;
            temp = temp.next;
            temp1 = temp1.next;
        }
        
        return true;
        
    }
    private ListNode reverse(ListNode head){
        ListNode temp = head;
        ListNode prev = null;
        ListNode curr = temp;
        while(curr!=null){
            curr = curr.next;
            temp.next = prev;
            prev = temp;
            temp = curr;
           
            
        }
        return prev;
    }
    
}
