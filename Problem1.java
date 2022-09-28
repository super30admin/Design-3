// Time Complexity : O(1) next Function, O(1) for hasNext()
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : no

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


public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> stack;

    NestedInteger nextEle;

    //initializing and pushing the list
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return next.getInteger();
    }

    @Override
    public boolean hasNext() {

        while (!stack.isEmpty())
        //check if the next pointer is not null
            //
            if (!stack.peek().hasNext()) {
                stack.pop();
            }
        //otherwise get the next element to check if it is an integer
            else if ((nextEle = stack.peek().next()).isInteger()) {
                return true;
            }

            //if not integer, will push to the stack
            else {
                stack.push(nextEle.getList().iterator());
            }
        }
        return false;
    }