import java.util.Iterator;

public class FlattenNestedListIterator {
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
            return nextEle.getInteger();
        }

        @Override
        public boolean hasNext() {

            while (!stack.isEmpty()) {
                //check if the next pointer is not null
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
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

