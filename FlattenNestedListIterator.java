// TC : next-O(d), hashNext-O(1)
// SC : O(d) // d=depth

package S30_Codes.Design_3;
import javafx.util.Pair;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class FlattenNestedListIterator {
}

 // This is the interface that allows for creating nested lists.
 // You should not implement it, or speculate about its implementation
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

 class NestedIterator implements Iterator<Integer> {
    Stack<Pair<List<NestedInteger>, Integer>> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(new Pair(nestedList,0));
        explore();
    }

    @Override
    public Integer next() {
        Pair<List<NestedInteger>, Integer> pair = stack.pop();
        List<NestedInteger> nestedList = pair.getKey();
        Integer idx = pair.getValue();

        stack.push(new Pair(nestedList,idx+1));

        explore();
        return nestedList.get(idx).getInteger();
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void explore(){
        while(!stack.isEmpty()){
            Pair<List<NestedInteger>, Integer> pair = stack.peek();
            List<NestedInteger> nestedList = pair.getKey();
            Integer idx = pair.getValue();

            if(idx >= nestedList.size())
                stack.pop();

            else if(nestedList.get(idx).isInteger())
                return;

            else{
                stack.pop();
                stack.push(new Pair(nestedList,idx+1));
                stack.push(new Pair(nestedList.get(idx).getList(),0));
            }
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */