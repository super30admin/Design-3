// Did this code successfully run on Leetcode : Yes 
// Any problem you faced while coding this : No


public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextElem;
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());        
    }

    //Time Complexity - O(1)
    //Space Complexity - O(1)
    @Override
    public Integer next() {
        return nextElem.getInteger();
    }

    //Time Complexity - O(n) - Since we need to iterate through the entire nestedlist
    //Space Complexity - O(k) - Where k is the number of nested lists
    @Override
    public boolean hasNext() {
        
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }
            else if((nextElem = st.peek().next()).isInteger())
            {
              return true; 
                
            } else{
                st.push(nextElem.getList().iterator());
            }
        }
        return false;
    }
}
