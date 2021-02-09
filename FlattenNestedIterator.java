// Time Complexity :O(1)
// Space Complexity : Not sure
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
public class FlattenNestedIterator {
    public class NestedIterator implements Iterator<Integer> {
        Stack<Iterator<NestedInteger>> st;
        NestedInteger nextEl;
        public NestedIterator(List<NestedInteger> nestedList) {
            st = new Stack<>();
            st.push(nestedList.iterator());
        }
    
        @Override
        public Integer next() {
            return nextEl.getInteger();
        }
    
        @Override
        public boolean hasNext() {
            
            while(!st.isEmpty())
            {
                if(!st.peek().hasNext())
                {
                    st.pop();
                }else if((nextEl = st.peek().next()).isInteger())
                {
                    return true;
                }else{
                    
                    st.push(nextEl.getList().iterator());
                    
                }
                          
            }
         return false;
        }
    }
    
}
