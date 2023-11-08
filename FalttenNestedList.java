// Time Complexity : O(d) where d is the max depth of nested list.
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


public class FalttenNestedList
{
    public class NestedIterator implements Iterator<Integer>
    {
        Stack<Iterator<NestedInteger>> st;
        NestedInteger nextElement;

        public NestedIterator(List<NestedInteger> nestedList) {
            st = new Stack();
            st.push(nestedList.iterator());
        }

        @Override
        public Integer next() {
            return nextElement.getInteger();
        }

        @Override
        public boolean hasNext() {
            while(!st.isEmpty())
            {
                if(!st.peek().hasNext())
                    st.pop();
                else if((nextElement = st.peek().next()).isInteger())
                    return true;
                else
                    st.push(nextElement.getList().iterator());
            }
            return false;
        }
    }
}


