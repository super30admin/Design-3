class Solution {
    //time complexity:O(2n)
    //space complexity:O(n)
    public int minDominoRotations1(int[] A, int[] B) {
        
        //get max count
        HashMap<Integer,Integer> map = new HashMap<>();
        int maxFreq = 0; int max=-1;
        for(int i = 0 ;i<A.length;i++){
            
            map.put(A[i],map.getOrDefault(A[i],0)+1);
            maxFreq = Math.max(maxFreq,map.get(A[i]));
            if(maxFreq>=A.length){
                max = A[i];
                break;   
            }
            map.put(B[i],map.getOrDefault(B[i],0)+1);
            maxFreq = Math.max(maxFreq,map.get(B[i]));
            if(maxFreq>=A.length){
                max = B[i];
                break;   
            }
            
        }
        if(max == -1)
            return -1;
        int aRot = 0;
        int bRot = 0;
        for(int i = 0;i<A.length;i++){
            if(A[i]!=max && B[i]!=max )return -1;
            
            if(A[i]!=max)aRot++;
            if(B[i]!=max)bRot++;
            
        }
        return Math.min(aRot,bRot);
        
        
        
        
    }
    public int minDominoRotations(int[] A, int[] B) {
​
        int result = checkMin(A, B, A[0]);
        if (result != -1)
            return result;
​
        return checkMin(A, B, B[0]);
​
    }
​
    private int checkMin(int[] a, int[] b, int target) {
        // TODO Auto-generated method stub
​
        int aRot = 0, bRot = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != target && b[i] != target)
                return -1;
            else if (a[i] != target)
                aRot++;
            else if (b[i] != target)
                bRot++;
​
        }
​
        return Math.min(aRot, bRot);
    }
    
    
}
