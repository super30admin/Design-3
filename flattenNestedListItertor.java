// Time Complexity : O(h), where h is max length of my nested list
// Space Complexity : O(h)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No

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

// Your code here along with comments explaining your approach
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> s;
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        s = new Stack<>();
        s.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        // Since hasNext() will always be called before next(), we will always have Integer in nextEl variable
        // therefore we directly return nextEl.getInteger() from this function
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!s.isEmpty()) {
            if (!s.peek().hasNext()) {
                // Since the iterator has come to an end on the current element, we pop it from the stack
                s.pop();
            }
            else if((nextEl = s.peek().next()).isInteger()) {
                return true;
            }
            else {
                // we come to this block when we do not have an integer on top of the stack,
                // we then push the array onto the stack with an iterator on it
                s.push(nextEl.getList().iterator());
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