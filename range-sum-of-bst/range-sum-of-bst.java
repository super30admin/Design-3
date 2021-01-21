        
        //base
        if(root==null)
            return;
        
        //logic
        if(root.val>=low && root.val <=high)
            result1+= root.val;
        
        if(root.val>low)
            dfs(root.left,low,high);
        if(root.val <high)
            dfs(root.right,low,high);
        
        
        
    }
    
    public int rangeSumBST(TreeNode root, int low, int high) {
        int result = 0;
        if(root==null)
            return 0;
        
        Stack<TreeNode> st = new Stack<>();
        
        while(!st.isEmpty() || root!=null) {
            while(root!=null) {
                st.push(root);
                root = root.left;
            }
            root = st.pop();
            if(root.val>=low && root.val<=high)
                result+=root.val;
            
            root = root.right;
        }
        
        return result;
        
    }
    
}
