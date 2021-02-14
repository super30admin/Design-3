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
    public boolean isPalindrome(ListNode head) {
        if(head == null)
            return true;
        
        ListNode slow = head;
        ListNode fast = head;
        ListNode temp = head;
        
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            
        }
        ListNode reversed = reverse(slow.next);
        while(reversed!=null){
            
            if(reversed.val!=temp.val){
                return false;
            }
            reversed = reversed.next;
            temp = temp.next;
            
        }
        return true;
        
    }
    public ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode temp = head;
        ListNode curr = head;
        
        while(curr!=null){
            
            curr = curr.next;
            temp.next = prev;
            prev = temp;
            temp = curr;
            
        }
        return prev;
        
    }
    
    
    
}