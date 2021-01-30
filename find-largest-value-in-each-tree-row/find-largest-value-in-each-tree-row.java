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
    List<Integer> result = new ArrayList<>();
    public List<Integer> largestValues(TreeNode root) {
        if(root ==null){
            return result;
        }
        
        dfs(root,0);
        return result;
    }
    
    private void dfs(TreeNode root,int height){
        if(root==null)
            return;
        
        
        if(result.size() == height){
            result.add(root.val);
        }else{
                result.set(height,Math.max(result.get(height),root.val));
            }
            
            dfs(root.left,height+1);
        dfs(root.right,height+1);    
        }
        
        
    }
​
