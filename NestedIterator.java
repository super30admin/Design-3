
//TC->O(1)
//SC->max depth of nesting in nested list


import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> stk;
    NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        stk=new Stack<>();
        stk.push(nestedList.iterator());//put iterator on list and then push it in stk because we are storing iterators on stk

    }

    @Override
    public Integer next() {

        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {

        while(!stk.isEmpty())
        {
            if(!stk.peek().hasNext())
            {
                stk.pop();//since no integers remaining in top list, we can remove that list
            }
            else if((nextEl=stk.peek().next()).isInteger())
            {
                return true;
            }
            else //it is list
            {
                //since nextel is of type NestedInteger, get the list first
                stk.push(nextEl.getList().iterator());
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