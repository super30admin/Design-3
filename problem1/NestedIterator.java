//Time Complexity : CONSTRUCTOR: O(n + l), NEXT: O(1), HAS NEXT: O(1); n -> Number of integers, l -> Number of lists
// Space Complexity : O(n + d), n -> Size of List of integers,d -> Size of recursive stack 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
package problem1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * // This is the interface that allows for creating nested lists. // You should
 * not implement it, or speculate about its implementation public interface
 * NestedInteger {
 *
 * // @return true if this NestedInteger holds a single integer, rather than a
 * nested list. public boolean isInteger();
 *
 * // @return the single integer that this NestedInteger holds, if it holds a
 * single integer // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 *
 * // @return the nested list that this NestedInteger holds, if it holds a
 * nested list // Return empty list if this NestedInteger holds a single integer
 * public List<NestedInteger> getList(); }
 */

public class NestedIterator implements Iterator<Integer> {
	List<Integer> list;
	int curPos;

	public NestedIterator(List<NestedInteger> nestedList) {
		list = new ArrayList<Integer>();
		flattenList(nestedList);
		curPos = 0;
	}

	private void flattenList(List<NestedInteger> nestedList) {
		for (NestedInteger integer : nestedList) {
			if (integer.isInteger()) {
				list.add(integer.getInteger());
			} else {
				flattenList(integer.getList());
			}
		}
	}

	@Override
	public Integer next() {
		return list.get(curPos++);
	}

	@Override
	public boolean hasNext() {
		return curPos < list.size();
	}

	public static void main(String[] args) {
		NestedIterator obj = new NestedIterator();

	}

}
