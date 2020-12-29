class Solution {
    public List<String> removeInvalidParentheses(String s) {
        
        List<String> result = new ArrayList<>();
        
        if(s==null){
            return result;
        }
        Queue<String> qu = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        set.add(s);
        qu.add(s);
        boolean flag = false;
        
        while(!qu.isEmpty() && !flag){
            int size = qu.size();
            for(int i = 0 ;i< size ;i++){
                String str = qu.poll();
            
                if(isValid(str)){
                
                    flag = true;
                    result.add(str);
                }
                
                if(!flag){
                    for(int j = 0 ;j< str.length();j++){
                        if(Character.isLetter(str.charAt(j))){     
                            continue;
                        }
                        
                        String baby = str.substring(0,j) + str.substring(j+1);
                        if(!set.contains(baby)){
                             
                             qu.add(baby);  set.add(baby);
                            
                            
                        }
                        
                    }
                    
                }
                
            }
            
        }
        
        return result;
    }
    
    
    private boolean isValid(String str){
        
        int count=0;
        for(int i = 0 ;i< str.length();i++){
