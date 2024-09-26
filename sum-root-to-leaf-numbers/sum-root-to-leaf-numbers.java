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
    class Pair{
        TreeNode root;
        int value;
            
        public Pair(TreeNode root,int value){
            this.value = value;
            this.root = root;
            
        }    
        public TreeNode getKey(){
            return this.root;
            
            
        }
        
        public int getValue(){
            
            return this.value;
        }
        
    }
    
    public int sumNumbers(TreeNode root) {
    
        Stack<Pair> st = new Stack<>();
        int sum = 0;
        int currSum = 0;
        while(root!=null || !st.isEmpty()){
            
            while(root!=null){
                
                st.push(new Pair(root,currSum=currSum*10+root.val));
                root = root.left;
                
            }
            Pair popd = st.pop();
            currSum = popd.getValue();
            root = popd.getKey();
            if(root.left==null && root.right == null)
                sum+=currSum;
            
            root = root.right;
        }
        return sum;
        
        
    }
//     int sum = 0;
//     public int sumNumbers(TreeNode root) {
        
        
        
        
//     }
    
    
}