// Time Complexity : Worst- O(h), h is max depth i.e number of nested lists and Avg - O(1)
// Space Complexity : O(h), h is max depth i.e number of nested lists.
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

public class FlattenNestedListIterator {
    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     * <p>
     * // @return true if this NestedInteger holds a single integer, rather than a nested list.
     * public boolean isInteger();
     * <p>
     * // @return the single integer that this NestedInteger holds, if it holds a single integer
     * // Return null if this NestedInteger holds a nested list
     * public Integer getInteger();
     * <p>
     * // @return the nested list that this NestedInteger holds, if it holds a nested list
     * // Return empty list if this NestedInteger holds a single integer
     * public List<NestedInteger> getList();
     * }
     */
    public class NestedIterator implements Iterator<Integer> {
        Stack<Iterator<NestedInteger>> stack;
        NestedInteger nextEle;

        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new Stack<>();
            stack.push(nestedList.iterator());
        }

        @Override
        public Integer next() {
            return nextEle.getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                if (!stack.peek().hasNext()) {
                    stack.pop();
                } else if ((nextEle = stack.peek().next()).isInteger()) {
                    return true;
                } else {
                    stack.push(nextEle.getList().iterator());
                }
            }
            return false;
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */