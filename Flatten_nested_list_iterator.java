import java.util.Iterator;
import java.util.List;
import java.util.Stack;

//Time Complexity : O(h)-> depth of brackets worst case, O(1)->average
//Space Complexity : O(N) -> stack
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> stk;
    NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        stk = new Stack<>();
        stk.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stk.isEmpty()){
            if(!stk.peek().hasNext()){
                stk.pop();
            }else if((nextEl = stk.peek().next()).isInteger()){
                return true;
            }
            else{
                stk.push(nextEl.getList().iterator());
            }
        }
        return false;
    }
}




 