class Solution {
    public boolean isMatch(String s, String p) {
    
​
​
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
​
            if (p.charAt(i - 1) == '*') {
​
                dp[0][i] = dp[0][i - 2];
            }
​
        }
        System.out.println(dp.length);
        System.out.println(dp[0].length);
        for (int i = 1; i < dp.length; i++) {
​
            for (int j = 1; j < dp[0].length; j++) {
​
                if (p.charAt(j - 1) != '*') {
                    if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                        // normal character so take it from diagonal
                        dp[i][j] = dp[i - 1][j - 1];
​
                    }
                } else {
                    // if * is there then check zero case as well as 1 case
                    // zero case
                    dp[i][j] = dp[i][j - 2]; //checking two steps back
​
                    // one case
                    //check the character of p with 2 steps back with s character
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j-2) == '.') {
                        if (dp[i - 1][j])
                            dp[i][j] = true;
​
                    }
                }
​
            }
​
        }
​
        return dp[s.length()][p.length()];
        
    }
}
