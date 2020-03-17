
// Problem 1: Flatten Nested List Iterator (https://leetcode.com/problems/flatten-nested-list-iterator/)

public class NestedIterator implements Iterator<Integer> {
    
    Stack<ListIterator<NestedInteger>> st;

    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.listIterator());
    }

    @Override
    public Integer next(){
        return st.peek().next().getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }else{
                NestedInteger x = st.peek().next();
                
                if(x.isInteger()){
                    st.peek().previous();
                    return true; 
                }
                st.push(x.getList().listIterator());
            }
        }
        
        return false;
    }
}
