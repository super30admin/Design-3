//Time Complexity: amortized O(1)
//Space Complexity: O(1)
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class FlattenNestedListIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    private NestedInteger nextElement;
    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextElement.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            } else if((nextElement = st.peek().next()).isInteger()) {
                return true;
            } else{
                st.push(nextElement.getList().iterator());
            }
        }
        return false;
    }
}
