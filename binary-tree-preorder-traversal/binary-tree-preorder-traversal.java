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
    
    List<Integer> result = new ArrayList<>();
    
    public List<Integer> preorderTraversal(TreeNode root) {
        
        if(null == root)
            return result;
        
        
        helper(root);
        return result;
        
    }
    
    private void helper(TreeNode root){
        //base
        if(root == null){
            return;        
                
        }        
        //logic 
        result.add(root.val);
        helper(root.left);
        helper(root.right);
        
    }
    
    
}