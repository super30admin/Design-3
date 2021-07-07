import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Time complexity is O(N)
// Space complexity is O(N)
// This solution is submitted on leetcode

public class BigN77FlattenIterator {
	// This is the interface that allows for creating nested lists.
	// You should not implement it, or speculate about its implementation
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
		// Return null if this NestedInteger holds a single integer
		public List<NestedInteger> getList();
	}

	public class NestedIterator implements Iterator<Integer> {
		// define all data structures here
		Queue<Integer> q;

		public NestedIterator(List<NestedInteger> nestedList) {
			this.q = new LinkedList<>();
			filling(nestedList);
		}

		@Override
		public Integer next() {
			return q.poll();
		}

		@Override
		public boolean hasNext() {
			return !q.isEmpty();
		}

		public void filling(List<NestedInteger> nestedList) {
			for (NestedInteger element : nestedList) {
				if (element.isInteger()) {
					q.add(element.getInteger());
				} else {
					filling(element.getList());
				}
			}
		}
	}

}