//TC: O(1) for both functions
//SC: O(depth of list) stack space

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
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl; // we will store the next ele here bcoz we will lose the reference of it and
                          // move to next ele by calling next()

    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator()); // pushing nestedList as iterator in stack
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!st.isEmpty()) {
            if (!st.peek().hasNext()) { // using hasNext() function of nested ietrator to make sure at the top of the
                                        // stack there is ele giving next integer
                st.pop();
            } else if ((nextEl = st.peek().next()).isInteger()) { // st.peek().next() gives -> [1,2] and check if it is
                                                                  // integer. [1,2] is not integer , so go to else part
                return true;
            } else { // if it is not integer , get the list of it and push it to stack by making it
                     // as iterator
                st.push(nextEl.getList().iterator());
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