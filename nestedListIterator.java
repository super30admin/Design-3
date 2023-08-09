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

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

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

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */