class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        
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
        
        
        
        
