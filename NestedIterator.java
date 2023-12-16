import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
//Time Complexity: O(1) for initialization, amortized O(1) for next() and hasNext() operations.
//Space Complexity: O(D) for nested structures, where D is the maximum nesting depth. In the worst case (flat list), it can be O(N)
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation */
interface NestedInteger {

      // @return true if this NestedInteger holds a single integer, rather than a nested list.
      public boolean isInteger();

      // @return the single integer that this NestedInteger holds, if it holds a single integer
      // Return null if this NestedInteger holds a nested list
      public Integer getInteger();

      // @return the nested list that this NestedInteger holds, if it holds a nested list
      // Return empty list if this NestedInteger holds a single integer
      public List<NestedInteger> getList();
  }
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }else if((nextEl = st.peek().next()).isInteger()){
                return true;
            }else{
                st.push(nextEl.getList().iterator());
            }
        }
        return false;
    }
}

 //************************this is wrong approach*******************************
class NestedIterator2 implements Iterator<Integer> {
    List<Integer> li;
    int i;
    public NestedIterator2(List<NestedInteger> nestedList) {
        this.li = new ArrayList<>();
        this.i = 0;
        helper(nestedList);
    }
    private void helper(List<NestedInteger> nestedList){
        for(NestedInteger el:nestedList){
            if(el.isInteger()){
                li.add(el.getInteger());
            }else{
                helper(el.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return li.get(i++);
    }

    @Override
    public boolean hasNext() {
        return i < li.size();
    }
}



/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */