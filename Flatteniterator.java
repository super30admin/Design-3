//Time complexity:O(n)

public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextInt;
    public NestedIterator(List<NestedInteger> nestedList) {
        st=new Stack();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextInt.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }
            else if((nextInt=st.peek().next()).isInteger()){
                return true;
            }
            else{
                st.push(nextInt.getList().iterator());
            }
        }
        return false;
    }
}