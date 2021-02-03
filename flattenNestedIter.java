public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger next;
    public NestedIterator(List<NestedInteger> nestedList) {
        st=new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
       return next.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }
            else if((next=st.peek().next()).isInteger()){
                return true;
            }
            else{
                st.push(next.getList().iterator());
            }
        }
        return false;
    }
}