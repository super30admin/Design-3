package com.ds.rani.design;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Given a nested list of integers, implement an iterator to flatten it.
 * <p>
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,1,2,1,1].
 * Example 2:
 * <p>
 * Input: [1,[4,[6]]]
 * Output: [1,4,6]
 * Explanation: By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,4,6].
 */

//approach: Maintaining stack of listiterator,whenever the next element is integer return that element
//if next element is again list,then push that list on top of stack by converting it into listiterator, continiue this process

//Time complexity:o(1) for hasNext() and next() function
//space complexity:o(n) in worst case where n i s number of elemnts in list
public class NestedIterator implements Iterator<Integer> {

    Stack<ListIterator<NestedInteger>> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
//convert input list to list iterator and push it into stack
        stack.push( nestedList.listIterator() );

    }

    @Override
    public Integer next() {
        hasNext();
        return stack.peek().next().getInteger();
    }

    @Override
    public boolean hasNext() {

        while (!stack.isEmpty()) {
            //whatever listiterator is available in my stack top, check whether it has next elemnt or not, if not then remove top listiterator from stack
            if (!stack.peek().hasNext())
                stack.pop();
            else {
                //whatever listiterator is available on my top of the stack, take its next element and check whether that next is a integer or list
                NestedInteger val = stack.peek().next();
                if (val.isInteger()) {
                    //here stack.peek() retuens listiterator available at top of the stack
                    return stack.peek().previous() == val;

                } else {//if its a list
                    stack.push( val.getList().listIterator() );
                }

            }
        }
        return false;
    }
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
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}