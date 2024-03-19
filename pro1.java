// Time Complexity : O(N) is all are nested in worst case(Like a skewed tree)
// Space Complexity : O(H) height of nestings
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// using controlled recursion with a physical stack, we are flatenning the nested iterator
// NestedInteger is an interface that is provided

import java.util.*;
public class NestedIterator implements Iterator<Integer> {
    NestedInteger nextEl;
    Stack<Iterator<NestedInteger>> st;
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }else if((nextEl = st.peek().next()).isInteger()){
                return true;
            }else{
                st.push(nextEl.getList().iterator());
            }
        }
        return false;
    }

    
}
