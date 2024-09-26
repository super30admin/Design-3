class Solution {
    public String longestPalindrome(String s) {
        if(s==null || s.length()==0)
            return s;
        
        int n = s.length();
        boolean [][] dp = new boolean[n][n];
        int max = 0;
        int start = 0;
        int end = 0;
        for(int i = 0; i<n;i++){
            for(int j = 0;j<=i;j++){
                if(s.charAt(i) == s.charAt(j) && (i-j<2 || dp[i-1][j+1])){
                    dp[i][j] = true;
                    if(max<i-j+1){
                        max = i-j+1;   
                        start = j;
                        end = i;
                    }
                    
                    
                }
                
            }
            
        }
        
        return s.substring(start,end+1);
        
        
    }
}
