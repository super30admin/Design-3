class Solution {
    public int[] nextGreaterElements(int[] nums) {
      
        if(nums == null || nums.length == 0)
            return nums;
        
        int [] result = new int[nums.length];
        Stack<Integer> st = new Stack<>();
        Arrays.fill(result,-1);
        for(int i = 0;i< nums.length*2;i++){
            while(!st.isEmpty() && nums[i%nums.length] > nums[st.peek()]){  
                result[st.pop()] = nums[i%nums.length];
            }
            if(i<nums.length)
                st.push(i);
        }
        
        return result;
        
    }
}
