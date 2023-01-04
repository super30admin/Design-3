
// Time Complexity : Worst- O(h) ,h is max depth i.e number of nested lists. ,
// Avg - O(1)
// Space Complexity : O(h), h is max depth i.e number of nested lists.
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
/*
 * We use native iterator. create a iterator on list , we add the next list to
 * the top of the stack.
 * If the next eleemnt is integer we return true, else if there is not next
 * element , pop from the stack
 * Else add the nested list as a iterator in the stack.
 */

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 * // @return true if this NestedInteger holds a single integer, rather than a
 * nested list.
 * public boolean isInteger();
 *
 * // @return the single integer that this NestedInteger holds, if it holds a
 * single integer
 * // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 *
 * // @return the nested list that this NestedInteger holds, if it holds a
 * nested list
 * // Return empty list if this NestedInteger holds a single integer
 * public List<NestedInteger> getList();
 * }
 */

public class NestedIterator implements Iterator<Integer> {

    private NestedInteger nextEl;
    private Stack<Iterator<NestedInteger>> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.stack = new Stack<>();
        this.stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() { // O(1)
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() { // O(h)

        while (!stack.isEmpty()) {
            if (!stack.peek().hasNext()) {
                stack.pop();
            } else if ((nextEl = stack.peek().next()).isInteger()) {
                return true;
            } else {
                stack.push(nextEl.getList().iterator());
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