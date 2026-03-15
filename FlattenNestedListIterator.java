// Time Complexity : O(1) for all operations
// Space Complexity : O(d) where d is max depth of the nested list
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
/*
To abide with the iterator concept, we use a stack of nested integer iterator type and push the input list
iterator initially.We also maintain a nextElement variable to keep of track of next element present or not
which is nothing but hasNext() functionality. Now, we have a helper method to iteratively go through the stack
and peek into the entry and determine if it has next elements or not. If not, pop the stack and set the next element
to null.If not, check the type if its list or integer. If integer, we can directly return using getInteger()
interface method, if not, recurse further by adding to the stack until integer type is found.
 */
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
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEle;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator());
        advance();
    }

    private void advance() {
        while(!st.isEmpty()) {
            if(!st.peek().hasNext()) {
                st.pop();
                nextEle = null;
            }
            else {
                nextEle = st.peek().next();
                if(nextEle.isInteger())
                    break;
                else
                    st.push(nextEle.getList().iterator());
            }
        }
    }

    @Override
    public Integer next() {
        Integer temp = nextEle.getInteger();
        advance();
        return temp;
    }

    @Override
    public boolean hasNext() {
        return nextEle != null;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */