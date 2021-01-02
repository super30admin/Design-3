class Solution {
    public int missingNumber(int[] nums) {     
       if (nums.length == 0 || nums == null) {
            return -1;
        }
        Arrays.sort(nums); //O(nlogn)
        int low =0;
        int high = nums.length -1;
        while(low<=high){   //[0,1,2,3,4,5,6,7,9]    // O(logn)
            int mid = low + (high-low)/2; // 8 + (9-8)/2 = 8
            if(nums[mid] - mid!=0){ //9-8 == 1
                high = mid -1; 
            }else{
                low = mid+1;
            }
        }
        
        return low; // 8
        //total time complexity ==> (O(n* 2logn))
    }
}
