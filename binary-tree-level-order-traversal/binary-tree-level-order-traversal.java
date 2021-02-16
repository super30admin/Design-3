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
    
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        
        if(root==null)
            return result;
        
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        
        while(!que.isEmpty()){
            List<Integer> val = new ArrayList<>();
            int size = que.size();
            TreeNode curr = null;
            for(int i = 0 ;i<size;i++){
                
                 curr = que.poll();   
                 val.add(curr.val); 
                 if(curr.left!=null)
                que.add(curr.left);
            
            if(curr.right!=null)
                que.add(curr.right);
                
            }
           
           
            
             result.add(val);
            
        }
        
        
        return result;
        
    }
}