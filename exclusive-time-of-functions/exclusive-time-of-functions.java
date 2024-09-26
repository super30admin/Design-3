class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        
        if(logs == null || logs.size() == 0 )
            return new int [0];
        
        int prev = 0;
        int [] result = new int[n];
        Stack<Integer> st = new Stack<>();
        for(String str : logs){
            
            String [] arrSplit = str.split(":");
            int curr = Integer.parseInt(arrSplit[2]);
            
            if(arrSplit[1].equals("start")){
                if(!st.isEmpty()){
                    result[st.peek()] += curr -prev;
                    prev = curr;
​
                }
                st.push(Integer.parseInt(arrSplit[0]));
                
            }else{
                //end case
                result[st.pop()] += curr -prev +1;
                prev = curr+1;
                
                
            }
            
            
        }
        
        return result;
        
        
        
    }
}
