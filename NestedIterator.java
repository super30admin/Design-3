// Time Complexity : O(N + L) // L number of nested lists and N number of elements in a list
// Space Complexity :O(N + L) // stack size
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :no

/*
 * 1. Maintain a stack of iterators.
 * 2. use hasnext method to have a controlled iterator.
 * 3. if next iterator has a integer then return true.
 * 4. if not pop elment and add next list iterator to the stack.
 */

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class NestedIterator implements Iterator<Integer> {
	private Stack<Iterator<NestedInteger>> stack;
	private NestedInteger nxtele;

	public NestedIterator(List<NestedInteger> nestedList) {
		stack = new Stack<>();
		stack.push(nestedList.iterator());
	}

	@Override
	public Integer next() {
		return nxtele.getInteger();
	}

	@Override
	public boolean hasNext() {
		while (!stack.isEmpty()) {
			if (!stack.peek().hasNext()) {
				stack.pop();
			} else if ((nxtele = stack.peek().next()).isInteger()) {
				return true;
			} else {
				stack.push(nxtele.getList().iterator());
			}
		}
		return false;
	}

}

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {

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
