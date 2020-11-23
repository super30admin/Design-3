// Time Complexity : O(N) where N is the no of elements in the NestedInteger list
// Space Complexity : O(N) for stack
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


/*Approach
 * We are using the using the stack data structure. Push the Iterator of the NestedInteger list into the
 * stack. For every hasNext call, We traverse next element of the iterator in the stack, if we find the integer we
 * return the integer else if we find the list we add it to the stack.
 * */

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class FlattenNestedListIterator {


    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public class NestedIterator implements Iterator<Integer> {

        Stack<Iterator<NestedInteger>> stack;
        NestedInteger nextElement;
        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new Stack<>();
            stack.push(nestedList.iterator());
        }

        @Override
        public Integer next() {
            return nextElement.getInteger();
        }

        @Override
        public boolean hasNext() {

            while (!stack.isEmpty()){
                if(!stack.peek().hasNext()){
                    stack.pop();
                } else if ((nextElement = stack.peek().next()).isInteger()){
                    return true;
                } else {
                    stack.push(nextElement.getList().iterator());
                }
            }
            return false;
        }
    }
}
