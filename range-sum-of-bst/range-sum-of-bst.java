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
    int result;
    public int rangeSumBST(TreeNode root, int low, int high) {
     
        if(root == null)
            return 0;
        
        dfs(root,low,high);
        return result;
        
    }
    private void dfs(TreeNode root, int low, int high){
        
        //base
        if(root==null)
            return;
        
        
        //logic
        if(root.val>=low && root.val <=high)
            result+= root.val;
        
        dfs(root.left,low,high);
        dfs(root.right,low,high);
        
        
        
    }
    
    
}
