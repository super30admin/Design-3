// Time Complexity - O(n) 
// Space Complexity - O(n) where n is maximum number of nestings inside main nestedInteger
// Passed all testcases successfully on Leetcode

// Approach:
// Maintain a stack to maintain all iterators of nestedLists in order, also a cursor which is set inside hasNext() always.
// Directly use cursor's value to get next int as next() is always called after hasNext()
// Stack all iterators in order in hasNext() function

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
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger el;
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() { // O(1) time and space
        return el.getInteger();
    }

    @Override
    public boolean hasNext() { // O(n) time and space (mentioned above at the top)
        while(!st.isEmpty()) {
            if(!st.peek().hasNext()) {
                st.pop();
            } else {
                el = st.peek().next();
                if(el.isInteger()) return true;
                else {
                    st.push(el.getList().iterator());
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