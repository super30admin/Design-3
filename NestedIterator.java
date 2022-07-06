public class NestedIterator {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.add(nestedList.iterator());
    }

    @Override
    // TC is O(1)
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    // TC is O(1)
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }
            else if((nextEl = st.peek().next()).isInteger()){
                    return true;
                }else{
                    st.push(nextEl.getList().iterator());
            }
        }
        return false;
    }
}
