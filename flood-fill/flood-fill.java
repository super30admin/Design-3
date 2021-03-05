class Solution {
    int color;
    int m;int n;
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        
        if(image == null || image.length ==0 || image[sr][sc] == newColor){
            return image;
        }
        color = image[sr][sc];
        m = image.length;
        n = image[0].length;
        dfs(image,sr,sc, newColor);
        return image;
    }
    
    
    private void dfs(int[][] image, int i,int j, int newColor){
        
        //base
        if(i<0 || i>=m || j<0 || j>=n || image[i][j]!=color)
            return;
        
        //logic
        int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
        image[i][j] = newColor;
        for(int[] dir : dirs){
            int r = dir[0] +i;
            int c = dir[1] + j;
            
            dfs(image,r,c,newColor);
        }
        
    }
}