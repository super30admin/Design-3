// Time - O(1) for next, O(N) for hasNext for N consecutive nested lists
// Space - O(N), for storing the N nested list

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
        
        while(!st.isEmpty()) {
            if(!st.peek().hasNext()) {
                st.pop();
            }
            else if ((nextEl = st.peek().next()).isInteger()) {
                return true;
            }
            else {
                st.push(nextEl.getList().iterator());
            }
        }
        return false;
    }
}
