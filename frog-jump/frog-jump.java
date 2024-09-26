class Solution {
    public boolean canCross(int[] stones) {
        
        if(stones.length == 0 || stones ==null)
            return false;
        
        if(stones.length == 1)
            return true;
        
        
        int [] k = {-1,0,1};
        HashMap<Integer,HashSet<Integer>> map = new HashMap<>();
        map.put(stones[0],new HashSet<Integer>());
        map.get(stones[0]).add(0);
        for(int i = 0;i<stones.length;i++){
            
            if(map.containsKey(stones[i])){
                
                for(Integer val :map.get(stones[i])){
                    for(int kVal :k){
                       int jump = val + kVal;
                        if(jump>0){
                            int dst = jump + stones[i];
                            
                            if(dst == stones[stones.length -1])
                                return true;
                            
                            if(!map.containsKey(dst))
                                map.put(dst,new HashSet<Integer>());
                            
                            map.get(dst).add(jump);
                            
                        }
                        
                    }   
                    
                }
                
            }else{
                
                return false;
            }
            
            
            
        }
        
       return map.containsKey(stones[stones.length-1]);
        
    }
}
