// Problem 2: https://leetcode.com/problems/flatten-nested-list-iterator/

// Time Complexity : O(1) - For both operations
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Nothing

public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEle;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEle.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }else if((nextEle = st.peek().next()).isInteger()){
                return true;
            }else{
                st.push(nextEle.getList().iterator());
            }
        }
        return false;
    }
}
