import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
//Leetcode - 341
//Time Complexity - O(D) - worstCase - max depth of the nestedIterator, O(1) - avg case
//Space Complexity - O(D)
public class FlattenNestedIterator implements Iterator<Integer> {
    private NestedInteger nextEl;
    private Stack<Iterator<NestedInteger>> st;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() { //O(1)
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()) { //O(D)
            if(!st.peek().hasNext()) {
                st.pop();
            } else if((nextEl = st.peek().next()).isInteger()) return true;
            else {
                st.push(nextEl.getList().iterator());
            }
        }
        return false;
    }


    /*private List<Integer> li;
    private int i;
    public FlattenNestedIterator(List<NestedInteger> nestedList) {
        this.li = new ArrayList<>();
        flatten(nestedList);
    }
    private void flatten(List<NestedInteger> nestedList) {
        for(NestedInteger el : nestedList) {
            if(el.isInteger()) {
                li.add(el.getInteger());
            } else {
                flatten(el.getList());
            }
        }
    }

    @Override
    public Integer next() {
        Integer result = li.get(i);
        i++;
        return result;
    }

    @Override
    public boolean hasNext() {
        return i < li.size();
    }*/
}
