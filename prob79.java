//NOT RUNNING ON LEETCODE


public class NestedIterator implements Iterator<Integer> {
Stack<Iterator<NestedIterator>> st;
    NestedInteger nexts;
    public NestedIterator(List<NestedInteger> nestedList) {
      st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nexts.getInteger();
        
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }
            else if((nexts = st.peek().next()).isInteger()){
                return true;
            }
            else{
                st.push(nexts.getList().iterator());
            }
        }
        return false;
    }
}
