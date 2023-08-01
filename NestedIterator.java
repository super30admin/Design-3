package Design_3;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * We use a stack to store the current list being processed. When hasNext() is
 * called, if the iterator.next() at top of stack is an integer we store its
 * value a variable and give it out when next() is called. Whenever we encounter
 * an innerlist we push that list's iterator onto the stack and process the
 * elements elements from the top of the stack in a similar way.
 * 
 * Time Complexity : next() - O(1) hasNext() - O(n), where n is the size of
 * given list (If every next element inside forms an inner list), as we have
 * iterate through all elements and put them on the stack.
 * 
 * Space Complexity : O(n), where n is the size of given list. (If every next
 * element inside forms an inner list)
 * 
 * Did this code successfully run on Leetcode : yes
 * 
 * Any problem you faced while coding this : No
 */
public class NestedIterator implements Iterator<Integer> {

    Iterator<NestedInteger> parentIterator;
    NestedInteger next;
    Stack<Iterator<NestedInteger>> stk;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.parentIterator=nestedList.iterator();
        stk=new Stack<>();
        stk.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return next.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stk.isEmpty()){
            if(!stk.peek().hasNext()){
                stk.pop();
            }
            else if((next=stk.peek().next()).isInteger()){
                return true;
            }
            else{
                stk.push(next.getList().iterator());
            }
        }

        return false;
    }
}