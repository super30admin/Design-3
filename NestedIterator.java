package S30.Design_3;

import java.util.Iterator;
import java.util.Stack;

/*
Time Complexity : O(n)
Space Complexity : O(n) // if everything is a nested list
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : None
*/

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
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> stack;
    NestedInteger nextEle;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.stack = new Stack<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEle.getInteger();
    }

    @Override
    public boolean hasNext() {

        while(!stack.isEmpty()){

            if(!stack.peek().hasNext()){
                stack.pop();
            }else if((nextEle = stack.peek().next()).isInteger()){ // stack might become empty after previous pop, hence the else if
                return true;
            }else{
                stack.push(nextEle.getList().iterator());
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
