
//Time Complexity : O(N)
//Space Complexity: O(N)
import java.util.*;

public class NestedIterator implements Iterator<Integer> {
	Queue<Integer> q;

	public NestedIterator(List<NestedInteger> nestedList) {
		q = new LinkedList<>();
		flatten_List(nestedList);
	}

	@Override
	public Integer next() {
		return q.poll();
	}

	@Override
	public boolean hasNext() {
		return !q.isEmpty();
	}

	private void flatten_List(List<NestedInteger> nestedList) {
		for (NestedInteger el : nestedList) {
			if (el.isInteger()) {
				q.add(el.getInteger());
			} else {
				flatten_List(el.getList());
			}
		}
	}
}