import java.util.Iterator;
import java.util.List;
import java.util.Stack;

// Time Complexity : O(1) 
// Space Complexity : O(D) ==> one List size at a time
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : yes


// Your code here along with comments explaining your approach



 interface NestedInteger {
    
         // @return true if this NestedInteger holds a single integer, rather than a nested list.
         public boolean isInteger();
    
         // @return the single integer that this NestedInteger holds, if it holds a single integer
         // Return null if this NestedInteger holds a nested list
         public Integer getInteger();
    
         // @return the nested list that this NestedInteger holds, if it holds a nested list
         // Return empty list if this NestedInteger holds a single integer
         public List<NestedInteger> getList();
     }

public class flattenListIterator implements Iterator<Integer> {
    
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;

    public flattenListIterator(List<NestedInteger> nestedList) {
        st=new Stack<>();
        st.push(nestedList.iterator());
        
    }

    @Override
    public Integer next() {
        
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            //case 1
            
            if(!st.peek().hasNext()){
                st.pop();
                
            }else  if((nextEl=st.peek().next()).isInteger()){ //case 2
                    return true;
                
            }else{ //case 3
                
                st.push(nextEl.getList().iterator());
            }
            
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */