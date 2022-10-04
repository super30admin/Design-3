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
    Stack<Iterator<NestedInteger>>st;
    NestedInteger nextEle;

    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
        
    }

    @Override
    //tc- o(1)
    public Integer next() {
        return nextEle.getInteger();
    }

    @Override
    //tc - worst o(max depth)
    //tc - avg - o(1)
    public boolean hasNext() {
        while(!st.isEmpty())
        {
            if(!st.peek().hasNext())
            {
                st.pop();
            }
            else if ((nextEle = st.peek().next()).isInteger())
            {
                return true;
            }
            else{
            //check if the top of the stack has an iterator which has integer
            st.push(nextEle.getList().iterator());
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