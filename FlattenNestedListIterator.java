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

/**
 * TC: O(n)
 * SC: O(n)
 * LeetCode: Y(https://leetcode.com/problems/flatten-nested-list-iterator/)
 * Approach: Convert NestedInteger to Iterator to use Iterator's and Nested Integer's methods
 *           Maintain a variable nextElement that points to next Integer
 *           Populate the stack with nested lists and pop a list only when that entire list is iterated over
 */
public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextElement;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextElement.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()) {
            if(!st.peek().hasNext()) {
                st.pop();
            }
            else if((nextElement = st.peek().next()).isInteger()) {
                return true;
            }
            else {
                st.push(nextElement.getList().iterator());
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
