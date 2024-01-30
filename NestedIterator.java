/*
* Approach:
*  1. Use stack which holds iterator on the nestedlist.
    keep using the internal iterators using hasNext() and next()
        on top element of stack.
* 
*  2. if internal iterator reaches the end, pop that from stack.
    if internal iterator gives the element, store that and
        check for isInteger() if true, return true from hasNext() function.
        else, getList() and place internal iterator and push it to stack.
* 
*  3. In next(), element will be ready to use, return element.getInteger()
* 
* 
* Did this code successfully run on Leetcode : YES
* 
* Any problem you faced while coding this : NO
* 
* Time Complexity: O(D)
    D - depth of nesting
* 
* Space Complexity: O(D)
    D - depth of nesting (in stack)
* 
*/

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a
    // nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a
    // single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested
    // list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator> st;

    NestedInteger element;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();

        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return element.getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!st.isEmpty()) {
            Iterator it = st.peek();

            if (!it.hasNext()) {
                st.pop();
            } else {
                element = (NestedInteger) it.next();
                if (element.isInteger()) {
                    return true;
                } else {
                    st.push(element.getList().iterator());
                }
            }
        }

        return false;
    }
}