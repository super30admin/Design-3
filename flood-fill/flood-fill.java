class Solution {
    int color;
    int m;int n;
    public int[][] floodFillDFS(int[][] image, int sr, int sc, int newColor) {
        
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
    
    //BFS
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        
        
        if(image == null || image.length == 0 || image[sr][sc]==newColor)
            return image;
        
        
        m = image.length;
        n = image[0].length;
        int orgColor = image[sr][sc];
        Queue<Integer> qu = new LinkedList<>();
        qu.add(sr);
        qu.add(sc);        
       int [][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};
        image[sr][sc] = newColor;
        while(!qu.isEmpty()){
            int currRow = qu.poll();
            int currCol = qu.poll();
                for(int[] dir : dirs){
                    int r = dir[0] + currRow;
                    int c = dir[1] + currCol;
                    if(r>=0 && r<m && c>=0 && c<n && image[r][c]==orgColor){
                        image[r][c]=newColor;
                        qu.add(r);
                        qu.add(c);
                    }
                }
            
        }
        
        
        return image;
        
    }
}