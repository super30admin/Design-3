public class NestedListIterator {
  Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack();
        st.push(nestedList.iterator());
    }

    @Override
    /*
    Time Complexity: O(1)
    Were you able to solve this on leetcode? Yes
    */
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    /*
    Time Complexity: O(depth), Average case: O(1)
    */
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            } else if((nextEl = st.peek().next()).isInteger()){
                System.out.println(nextEl.getInteger());
                return true;
            } else {
                st.push(nextEl.getList().iterator());
            }
        }
        
        return false;
    }
}
