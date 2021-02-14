/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int lh;
    int rh;
    public boolean isBalanced(TreeNode root) {
        
        if(root==null)
            return true;
       
        
        return isBalanced(root.left) && isBalanced(root.right) && Math.abs(height(root.left) - height(root.right))<=1; 
        
    }
    
    public int height(TreeNode root){
        
        //base
        if(root==null)
            return 0;
        
        //logic 
        return Math.max(height(root.left),height(root.right))+1;
        
        
    }
    
   
    
}