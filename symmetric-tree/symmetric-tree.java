/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        
        if(root==null)
            return true;
        
       return dfs(root,root);
        
        
    }
    private boolean dfs(TreeNode rootLeft,TreeNode rootRight){
        
        //base
        if(rootLeft == null && rootRight == null)
            return true;
        
        
        //logic
        if(rootLeft!=null && rootRight!=null && rootLeft.val == rootRight.val){
            
            return dfs(rootLeft.left,rootRight.right) && dfs(rootLeft.right,rootRight.left);
        }
        
        
       return false;
        
        
    }
    
    
    
}
