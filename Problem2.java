public class Problem2 implements Iterator<Integer> {
    private Stack<Iterator<NestedInteger>> st;
    private NestedInteger nextEl;
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
        while(!st.isEmpty()){
            if(!st.peek().hasNext())
                st.pop();
            else if((nextEl = st.peek().next()).isInteger())
                return true; 
            else 
                st.push(nextEl.getList().iterator());

        }
        return false;
    }
}