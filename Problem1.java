//Flatten Nested List Iterator


//tc - O(1) 
//sc - O(n) - stack space n - total integers

import java.util.*;
class Problem1 implements NestedInteger{
    public static void main(String[] args) {
        
        
    }
    
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextElement ;
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
        
    }
    

    @Override
    public Integer next() {
        return nextElement.getInteger();
        
        
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            // Iterator<NestedInteger> curr = st.peek();
            if(!st.peek().hasNext()){
                st.pop();
            }
            else if((nextElement = st.peek().next()).isInteger()){
                return true;
            }
            else{
                st.push(nextElement.getList().iterator());   //1,1
            }
            
        }
        return false;
        
    }
    
}