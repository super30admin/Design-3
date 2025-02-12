// TC: O(max depth of nested list)
// SC: O(max depth of nested list)


import java.util.Iterator;
import java.util.List;
import java.util.Stack;

interface NestedInteger {
    public boolean isInteger();

    public Integer getInteger();

    public List<NestedInteger> getList();
}

public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> stack = new Stack<>();
    NestedInteger next;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return next.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()){
            Iterator<NestedInteger> ni = stack.peek();

            if(!ni.hasNext()){
                stack.pop();
            } else {
                NestedInteger temp = ni.next();
                if(temp.isInteger()){
                    next = temp;
                    return true;
                } else {
                    stack.push(temp.getList().iterator());
                }
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */