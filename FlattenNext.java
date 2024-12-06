import java.util.List;
import java.util.Stack;

public class FlattenNext{
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }else{
                nextEl = st.peek().next();
                if(nextEl.isInteger()){
                    return true;
                }else{
                    st.push(nextEl.getList().iterator());
                }
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }
}