class Solution {
    public int evalRPN(String[] tokens) {
        
        if(tokens == null || tokens.length==0){
            return 0;
        }
        
        Stack<String> st = new Stack<>();
        
        int result = 0;
        for(String str :tokens){
            if(str.equals("+") ){
                
                String v1= st.pop();
                String v2= st.pop(); 
                 System.out.println(v1);
                 System.out.println(v2);
                
                 System.out.println("-----------------");
                st.push(""+(Integer.parseInt(v1)+Integer.parseInt(v2)));
                        
            }  
            
            else if(str.equals("-") ){
                
                String v1= st.pop();
                String v2= st.pop();   
                 System.out.println(v1);
                 System.out.println(v2);
                st.push(""+(Integer.parseInt(v2)- Integer.parseInt(v1)));
            }
            
            else if(str.equals("*") ){
                
                String v1= st.pop();
                String v2= st.pop();  
                    System.out.println(v1);
                 System.out.println(v2);
                 int val = Integer.parseInt(v2)*Integer.parseInt(v1);
               
                st.push(""+(val));
            }
           else if(str.equals("/") ){
​
                    String v1= st.pop();
                    String v2= st.pop();   
     System.out.println(v1);
                 System.out.println(v2);
                    st.push(""+(Integer.parseInt(v2)/Integer.parseInt(v1)));
            }
            else{
                //int val = Integer.parseInt(str);
                st.push(str);
            }
            
            
        }
        
        return Integer.parseInt(st.pop());
    }
}
