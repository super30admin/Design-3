// Time Complexity : avg - O(1), worst case- O(n)
// Space Complexity :avg - O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

/**
 * use controlled recursion, use stack of iterator 
 * if current element on stack top is integer, then access it, if current iterator ends, pop the stack top
 * if current iterator points to list, push its iterator to stack
 * 
 * https://leetcode.com/problems/flatten-nested-list-iterator/
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

	NestedInteger nextEl;

	//use stack to maintan iterator of NestedInteger
	Stack<Iterator<NestedInteger>> st;

	public NestedIterator(List<NestedInteger> nestedList) {
		st = new Stack<>();
		//push iterator to nestedList on stack
		st.push(nestedList.iterator());
	}

	@Override
	public Integer next() {
		// hasNext() is always called before next() as per question instructions and hasNext() else if is making sure that nextEl will always have first element as integer
		return nextEl.getInteger();
	}

	@Override
	public boolean hasNext() {

		while(!st.isEmpty()) {
			//if current iterator runs out, pop that entry from stack and move to next entry 
			if(!st.peek().hasNext()){
				st.pop();

				/*
              if stack top next element is integer, return true
              native iterator next() method moves pointer immediately so back that up in nextEl
				 */

			} else if((nextEl = st.peek().next()).isInteger()) {
				return true;
			} else {
				//if stack top next element is a list, push its iterator to stack top
				//remember stack top next element is backed up in the nextEl in previous else if
				st.push(nextEl.getList().iterator());
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