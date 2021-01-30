class Solution {
    public int superEggDrop1(int K, int N) {
        
        int [] [] dp = new int[K+1][N+1];
        
        for(int i = 0 ;i<N+1;i++){
            dp[1][i] = i; 
            
        }
        for(int i = 2;i<K+1;i++){
            for(int j = 1;j<N+1;j++){
                dp[i][j] = Integer.MAX_VALUE; 
                for(int l = 1;l<=j;l++){
                        dp[i][j] = Math.min(dp[i][j],1+Math.max(dp[i-1][l-1],dp[i][j-l]));                   
                }
                  
            }
            
            
        }
        
        
        return dp[K][N];
        
    }
     public int superEggDrop(int K, int N) {
           int [] [] dp = new int[N+1][K+1];
         
         int attempt = 0;
         while(dp[attempt][K] < N){
             attempt++;
              for(int j = 1;j<=K;j++ ){
                
                dp[attempt][j] = 1 + dp[attempt-1][j-1] + dp[attempt-1][j];
                
            } 
             
         }
        
         return attempt;
         
     }
}
