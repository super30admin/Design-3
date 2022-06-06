// Time Complexity : O(N!) at root  we have to make N  decisions and decisions reduces by one as we go level by level
// Space Complexity : O(H), recursion stack
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
public class BeautifulArrangement {
    class Solution {
        private int count;
        public int countArrangement(int n) {
            count = 0;

            //main logic
            dfs(n, 1, new int[n+1]);

            return count;
        }

        private  void  dfs(int n, int idx, int[] arr) {
            //base
            if(idx > n) {
                count+=1;
                return;
            }

            //logic
            for(int i=1; i<=n; i++) {
                if(arr[i] == 0 && (idx % i == 0 || i % idx == 0)) {
                    arr[i] = 1;
                    dfs(n,idx+1, arr);
                    arr[i] = 0;

                }
            }
        }


    }
}
