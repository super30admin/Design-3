/*
 * NestedIterator2.java
 *
 * Approach:
 * - Lazy (on-demand) flattening using a stack of iterators. The constructor pushes the
 *   top-level list's iterator. hasNext() advances the stack until it finds and caches
 *   the next integer (in `nextElement`) or exhausts all iterators. next() returns the
 *   cached integer.
 * - This avoids eager O(n) preprocessing and can be more memory-efficient when the
 *   consumer doesn't iterate all elements.
 *
 * Time Complexity:
 * - hasNext()/next(): amortized O(1) per integer returned. Each NestedInteger is visited
 *   a constant number of times overall.
 *
 * Space Complexity:
 * - O(h) for the stack of iterators where h is the maximum nesting depth (worst-case O(n)).
 *
 * LeetCode:
 * - https://leetcode.com/problems/flatten-nested-list-iterator/ (Problem 341)
 */

import java.util.*;

public class NestedIterator2 implements Iterator<Integer> {

    private NestedInteger nextElement;
    private Stack<Iterator<NestedInteger>> stack;

    public NestedIterator2(List<NestedInteger> nestedList) {
       stack = new Stack<>();
       stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextElement.getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()){
            if(!stack.peek().hasNext()){
                stack.pop();
            }else {
                if((nextElement = stack.peek().next()).isInteger()){
                    return true;
                }else{
                    stack.push(nextElement.getList().iterator());
                }
            }
        }
        return false;
    }
}
