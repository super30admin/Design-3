// Time Complexity : O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
/*
 * 1. Create a stack with an iterator of the nestedList.
 * 2. Add the nested list to the stack.
 * 3. Check the type of the next element in the iterator. If its an integer return true and return the value else
 *  add the list to the stack.
 */

import java.util.Iterator;
import java.util.List;
import java.util.Stack;


class NestedIterator implements Iterator<Integer> {
    NestedInteger currEl;
    Stack<Iterator<NestedInteger>> st;
    List<NestedInteger> nestedList;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.nestedList = nestedList;
        this.st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return currEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }else if((currEl = st.peek().next()).isInteger()){
                return true;
            }else {
                st.push(currEl.getList().iterator());
            }
        }
        return false;
    }
}