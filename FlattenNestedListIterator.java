//TC: O(1) 
//SC: O(1) 

public class NestedIterator implements Iterator<Integer> {
    private Stack<Iterator<NestedInteger>> st;
    NestedInteger nextel;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextel.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }else if((nextel = st.peek().next()).isInteger()){
                return true;
            }else{
                st.push(nextel.getList().iterator());
            }
        }
        return false;
    }
}
