package Design3;


import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st = new Stack<>();
    NestedInteger nextElem = null;
    public NestedIterator(List<NestedInteger> nestedList) {
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextElem.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            } else if((nextElem = st.peek().next()).isInteger()) {
                return true;
            } else {
                st.push(nextElem.getList().iterator());
            }
        }
        return false;
    }
}


interface NestedInteger {


    public boolean isInteger();


    public Integer getInteger();


    public List<NestedInteger> getList();
}
