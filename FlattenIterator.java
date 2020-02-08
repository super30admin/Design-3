import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FlattenIterator {

	// This is the interface that allows for creating nested lists.
	// You should not implement it, or speculate about its implementation
	public interface NestedInteger {
		public boolean isInteger();
		public Integer getInteger();
		public List<NestedInteger> getList();
	}

	class NestedIterator implements Iterator<Integer> {
		Queue<Integer> q;

		public NestedIterator(List<NestedInteger> nestedList) {
			this.q = new LinkedList<>();
			dfs(nestedList);
		}

		private void dfs(List<NestedInteger> nestedList) {
			for (NestedInteger ele : nestedList) {
				if (ele.isInteger()) {
					q.add(ele.getInteger());
				} else {
					dfs(ele.getList());
				}
			}
		}

		@Override
		public Integer next() {
			return q.poll();
		}

		@Override
		public boolean hasNext() {
			return !q.isEmpty();
		}
	}

	/**
	 * Your NestedIterator object will be instantiated and called as such:
	 * NestedIterator i = new NestedIterator(nestedList); while (i.hasNext()) v[f()]
	 * = i.next();
	 */
}
