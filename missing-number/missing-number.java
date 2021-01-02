class Solution {
    public int missingNumber(int[] nums) {     
       if (nums.length == 0 || nums == null) {
            return -1;
        }
        Arrays.sort(nums);
        int low =0;
        int high = nums.length -1;
        while(low<=high){   //[0,1,2,3,4,5,6,7,9]
            int mid = low + (high-low)/2;
            if(nums[mid] - mid!=0){
                high = mid -1;
            }else{
                low = mid+1;
            }
        }
        
        return low;
        
    }
}
