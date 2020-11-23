package Nov23;

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
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

 /*
    Time Complexity: O(N) because we are iterating through all n elements of the given input exactly once one after another.
    
    Space Complexity: O(N) because in worst case, we can have an input with all N elements in N individual nested lists and iterators for each of these nested lists would need to be placed in the stack.
    
     Did this code successfully run on Leetcode : Yes
    
     Any problem you faced while coding this : No
    
     Approach: Stack used to hold iterator for nested lists in the input. Process one nested list completely and pop it from the stack once hasNext() for this nested list iterator returns false. Till hasNext() keeps returning true, check if next element is an integer or a nested list. If integer, return true and using next() function, this integer value can be added to the result. If nested list, add the iterator for this nested list to the top of the stack.
     
     
    */
public class FlattenNestedListIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextElement;
    
    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
        // initialise the stack and push the iterator of 'nestedList' fn.param to the stack.
        st = new Stack<>();
        st.push(nestedList.iterator());
    }

    // this fn.is called only when hasNext fn.returns true. 
    // it returns int value of the next element from the given input. 
    @Override
    public Integer next() {
        return nextElement.getInteger();
    }

    // note that iterator have the property that if hasNext() is called and a true ie returned, calling next() immediately after it doesnt fetch the element due to which hasNext() returned true but one after it. This is because iterators move one position ahead even on accessing hasNext() and not just next().
    @Override
    public boolean hasNext() {
        while (!st.isEmpty()) {
            // check the iterator element at top of stack and see if hasNext returns true for the same.
            // If not, remove the topmost stack entry since hasNext() returning false implies that all integer values of the topmost entry of the stack have been fetched already.
            if (!st.peek().hasNext()) {
                st.pop();
            } 
            // check the iterator element at top of stack and see if hasNext returns true for the same.
            // If yes, the next element can be an integer or a nested list.
            // Check for both the scenarios:
            //      if integer, return true. So that, after this function call ends, next() can be called to get the actual integer value in the output variable.
            //      if nested list, push the nested list iterator to the top of the stack for being processed.
            else {
                nextElement = st.peek().next();
                if (nextElement.isInteger()) {
                    return true;
                } else {
                    st.push(nextElement.getList().iterator());
                }
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
